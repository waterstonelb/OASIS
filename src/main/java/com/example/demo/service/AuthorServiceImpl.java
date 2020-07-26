package com.example.demo.service;

import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.AuthorPublishDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Author;
import com.example.demo.po.AuthorDirectInfo;
import com.example.demo.po.AuthorRelationHis;
import com.example.demo.po.Document;
import com.example.demo.service.serviceinterface.AuthorService;
import com.example.demo.vo.*;
import com.example.demo.vo.author.AuthorMapLink;
import com.example.demo.vo.author.AuthorMapNode;
import com.example.demo.vo.author.AuthorMapVO;
import com.example.demo.vo.author.AuthorPrd;
import com.example.demo.vo.author.AuthorStudyHis;
import com.example.demo.vo.author.AuthorVO;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

  private static final int YEAR_WEIGHT = 10000;
  private static final int AMOUNT_WEIGHT = 4;
  private static final int TREND_WEIGHT = 6;

  final
  AuthorDao authorDao;
  final
  AuthorPublishDao authorPublishDao;
  final
  DocumentDao documentDao;

  @Autowired
  public AuthorServiceImpl(AuthorDao authorDao, AuthorPublishDao authorPublishDao,
      DocumentDao documentDao) {
    this.authorDao = authorDao;
    this.authorPublishDao = authorPublishDao;
    this.documentDao = documentDao;
  }

  @Override
  public ResponseVO<AuthorVO> getAuthorInfo(int authorId) {
    try {
      Author author = authorDao.findById(authorId);
      int paperCount = authorPublishDao.countByAuthorId(authorId);
      int citationCount = authorPublishDao.sumCitationCountByAuthorId(authorId);
      List<String> keywords = authorPublishDao.findAuthorKeyWords(authorId);
      HashMap<String, Integer> sortKey = new HashMap<>();
      for (String key : keywords) {
        if (!key.isEmpty()) {
          for (String str : key.split(",")) {
            int count = 0;
            if (sortKey.containsKey(str)) {
              count = sortKey.get(str);
            }
            sortKey.put(str, count + 1);
          }
        }
      }
      List<Map.Entry<String, Integer>> list = new ArrayList<>(sortKey.entrySet());
      if (list.size() > 0) {
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
      }
      List<String> authorKeys = new ArrayList<>();
      for (int i = 0; i < Math.min(list.size(), 5); i++) {
        authorKeys.add(list.get(i).getKey());
      }
      List<Integer> hlist = authorDao.getHindexList(authorId);
      int hindex = countHindex(hlist);
      log.info("作者信息查询成功");
      return ResponseVO
          .buildSuccess(new AuthorVO(author, authorKeys, paperCount, hindex, citationCount));
    } catch (Exception e) {
      log.error(e.getLocalizedMessage());
      return ResponseVO.buildFailure("查询错误，请检查您的Id");
    }
  }

  @Override
  @Cacheable(value = "authorRelationCache")
  public ResponseVO<AuthorMapVO> getAuthorMap(int authorId) {
    try {
      int MAX_SIZE = 3;
      List<AuthorDirectInfo> directInfos = authorPublishDao.getAuthorDirect(authorId);
      List<AuthorMapNode> nodes = new ArrayList<>();
      List<AuthorMapLink> links = new ArrayList<>();
      HashMap<Integer, Integer> map = new HashMap<>();
      for (AuthorDirectInfo a : directInfos) {
        if (a.getId() != authorId) {
          map.put(a.getId(), 1);
          links.add(AuthorMapLink.builder()
              .source(Integer.toString(authorId))
              .target(Integer.toString(a.getId()))
              .value(a.getCount())
              .build());
        } else {
          map.put(a.getId(), directInfos.size() - 1);
        }
      }
      //二次查询亲戚节点之间的关系
      for (AuthorDirectInfo a : directInfos) {
        if (a.getId() != authorId) {
          List<AuthorDirectInfo> relation = authorPublishDao.getAuthorDirect(a.getId());
          for (AuthorDirectInfo b : relation) {
            if (b.getId() != a.getId() && map.containsKey(b.getId())) {
              map.put(b.getId(), map.get(b.getId()) + 1);
              boolean flag = true;
              for (AuthorMapLink t : links) {
                if ((t.getSource().equals(Integer.toString(a.getId())) && t.getTarget()
                    .equals(Integer.toString(b.getId())))
                    || (t.getSource().equals(Integer.toString(b.getId())) && t.getTarget()
                    .equals(Integer.toString(a.getId())))) {
                  flag = false;
                  break;
                }
              }
              if (flag) {
                links.add(AuthorMapLink.builder()
                    .source(Integer.toString(a.getId()))
                    .target(Integer.toString(b.getId()))
                    .value(b.getCount())
                    .build());
              }
            }
          }
        }
      }
      for (AuthorDirectInfo a : directInfos) {
        nodes.add(new AuthorMapNode(a, map.get(a.getId()) * MAX_SIZE));
      }
      log.info("作者关系图建立成功");
      return ResponseVO.buildSuccess(new AuthorMapVO(nodes, links));
    } catch (Exception e) {
      log.error("AuthorMap服务失败");
      log.error(e.getLocalizedMessage());
      return ResponseVO.buildFailure("AuthorMap服务失败");
    }
  }

  @Override
  public ResponseVO<List<AuthorStudyHis>> getAuthorStudyHistory(int authorId) {
    try {
      int count = authorPublishDao.countByAuthorId(authorId);
      List<Document> documents = documentDao.findByauthorId(authorId);
      Map<Integer, List<String>> map = new HashMap<>();
      List<AuthorStudyHis> res = new LinkedList<>();
      PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
      documents.forEach(doc -> {
        int time = doc.getPublicationYear();
        List<String> strs = new LinkedList<>(Arrays.asList(doc.getKeywords().split(",")));
        if (map.containsKey(time)) {
          map.get(time).addAll(strs);
        } else {
          queue.add(time);
          map.put(time, strs);
        }
      });
      while (!queue.isEmpty()) {
        int time = queue.poll();
        Map<String, Integer> tempMap = new HashMap<>();
        AtomicInteger max = new AtomicInteger();
        AtomicReference<String> target = new AtomicReference<>("");
        List<String> list = map.get(time);
        list.forEach(key -> {
          int feq = 1;
          if (tempMap.containsKey(key)) {
            feq = tempMap.get(key) + 1;
          }
          tempMap.put(key, feq);
          if (feq > max.get()) {
            max.set(feq);
            target.set(key);
          }
        });
        res.add(AuthorStudyHis.builder().time(time).key(target.get()).build());
      }
      return ResponseVO.buildSuccess(res);
    }catch (Exception e){
      e.printStackTrace();
      log.error("查询作者历年研究方向失败");
      log.error(e.getLocalizedMessage());
      return ResponseVO.buildFailure("查询作者历年研究方向失败");
    }
  }

  /**
   * 预测作者未来会和谁合作
   * 预测步骤
   * 1.获取历年来所有与之合作过的作者，以及每年合作的文章数量，对应关系存在{@link AuthorRelationHis}，
   *   生成映射关系详细逻辑见Dao层SQL语句（已经过优化，查询速度较最初提升100倍）
   * 2.对于每一个合作过的作者，生成两个预测指标，详情见{@link InnerRelation}
   *   (a)amountIndex:历年合作次数乘年份的加权值
   *   (b)trendIndex:上升趋势(每两年之差乘年份加权值)
   * 3.按照两个指标的加权算法生成每位作者的relation因子值，并据此排序
   * 4.返回结果
   *
   * 算法复杂度：
   * 时间复杂度O(n)
   * 空间复杂夫O(n)
   */
  @Override
  public ResponseVO<List<AuthorPrd>> getAuthorPrediction(int authorId) {
    try {
      /*
       * 1.生成原始关系数据
       */
      List<AuthorRelationHis> list = authorPublishDao.getAuthorRelationHis(authorId);
      Map<Integer, InnerRelation> map = new LinkedHashMap<>();
      AtomicInteger maxYear = new AtomicInteger();
      List<AuthorPrd> res = new LinkedList<>();

      /*
       * 2.建立作者独立数据
       */
      list.forEach(o -> {
        maxYear.set(Math.max(maxYear.get(), o.getYear()));
        if (!map.containsKey(o.getId())) {
          map.put(o.getId(),
              InnerRelation.builder().author(o.getName()).relation(new LinkedList<>()).build());
        }
        InnerRelation i = map.get(o.getId());
        i.getRelation().add(new AbstractMap.SimpleEntry<>(o.getYear(), o.getCount()));
      });
      /*
       * 3.生成加权值
       */
      map.forEach((key, value) -> {
        List<Entry<Integer, Long>> r = value.relation;
        r.sort(Entry.comparingByKey());
        /*
         * 3.1生成amountIndex
         */
        double amountIndex = 0.0;
        for (Entry<Integer, Long> i : r) {
          int year = i.getKey();
          Long count = i.getValue();
          amountIndex += (YEAR_WEIGHT >> (maxYear.get() - year)) * count;
        }
        /*
         * 3.2生成trendIndex
         */
        double trendIndex = 0.0;
        int startYear = r.get(0).getKey();
        long startCount = r.get(0).getValue();
        for (int i = 1; i < r.size(); i++) {
          int temp = r.get(i).getKey();
          long tcount = r.get(i).getValue();
          trendIndex += (YEAR_WEIGHT >> (maxYear.get() - temp)) * ((tcount - startCount) / (temp
              - startYear * 1.0));
        }
        double index = amountIndex + AMOUNT_WEIGHT + trendIndex * TREND_WEIGHT;
        res.add(
            AuthorPrd.builder().authorId(key).author(value.author).relation((long) index).build());
      });
      res.sort(Comparator.comparingLong(AuthorPrd::getRelation));
      List<AuthorPrd> finalRes = res;
      if(finalRes.size()>5){
        finalRes = res.subList(0,5);
      }
      for (int i = 0; i < 5; i++) {
        finalRes.get(i).setRank(i+1);
      }
      log.info("预测成功");
      return ResponseVO.buildSuccess(finalRes);
    }catch (Exception e){
      e.printStackTrace();
      log.error(e.getMessage());
      return ResponseVO.buildFailure("预测失败");
    }
  }
  @Data
  @Builder
  static class InnerRelation{
    String author;
    List<Map.Entry<Integer,Long>> relation;
  }

  private int countHindex(List<Integer> hlist) {
    hlist.sort(Integer::compareTo);
    int len = hlist.size();
    int hindex = 0;
    for (int i = 0; i < len; i++) {
      if (hlist.get(i) >= len - i) {
        hindex = len - i;
        break;
      }
    }
    return hindex;
  }
}
