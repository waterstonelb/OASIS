package com.example.demo.service;

import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.AuthorPublishDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Author;
import com.example.demo.po.AuthorDirectInfo;
import com.example.demo.service.serviceinterface.AuthorService;
import com.example.demo.vo.*;
import com.example.demo.vo.author.AuthorMapLink;
import com.example.demo.vo.author.AuthorMapNode;
import com.example.demo.vo.author.AuthorMapVO;
import com.example.demo.vo.author.AuthorVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    final
    AuthorDao authorDao;
    final
    AuthorPublishDao authorPublishDao;
    final
    DocumentDao documentDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, AuthorPublishDao authorPublishDao, DocumentDao documentDao) {
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
                        if (sortKey.containsKey(str))
                            count = sortKey.get(str);
                        sortKey.put(str, count + 1);
                    }
                }
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<>(sortKey.entrySet());
            if (list.size() > 0)
                list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
            List<String> authorKeys = new ArrayList<>();
            for (int i = 0; i < Math.min(list.size(), 5); i++)
                authorKeys.add(list.get(i).getKey());
            log.info("作者信息查询成功");
            return ResponseVO.buildSuccess(new AuthorVO(author, authorKeys, paperCount, citationCount));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("查询错误，请检查您的Id");
        }
    }

    @Override
    public ResponseVO<AuthorMapVO> getAuthorMap(int authorId) {
        try {
            int MAX_SIZE=3;
            List<AuthorDirectInfo> directInfos=authorPublishDao.getAuthorDirect(authorId);
            List<AuthorMapNode> nodes=new ArrayList<>();
            List<AuthorMapLink> links=new ArrayList<>();
            HashMap<Integer,Integer> map=new HashMap<>();
            for (AuthorDirectInfo a:directInfos) {
                if(a.getId()!=authorId) {
                    map.put(a.getId(),1);
                    links.add(AuthorMapLink.builder()
                            .source(Integer.toString(authorId))
                            .target(Integer.toString(a.getId()))
                            .value(a.getCount())
                            .build());
                }else
                    map.put(a.getId(),directInfos.size()-1);
            }
            //二次查询亲戚节点之间的关系
            for (AuthorDirectInfo a:directInfos) {
                if(a.getId()!=authorId){
                    List<AuthorDirectInfo> relation=authorPublishDao.getAuthorDirect(a.getId());
                    for (AuthorDirectInfo b:relation) {
                        if(b.getId()!=a.getId()&&map.containsKey(b.getId())){
                            map.put(b.getId(),map.get(b.getId())+1);
                            boolean flag=true;
                            for (AuthorMapLink t:links) {
                                if((t.getSource().equals(Integer.toString(a.getId()))&&t.getTarget().equals(Integer.toString(b.getId())))
                                        ||(t.getSource().equals(Integer.toString(b.getId()))&&t.getTarget().equals(Integer.toString(a.getId())))){
                                    flag=false;
                                    break;
                                }
                            }
                            if(flag) {
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
            for (AuthorDirectInfo a:directInfos) {
                nodes.add(new AuthorMapNode(a,map.get(a.getId())*MAX_SIZE));
            }
            log.info("作者关系图建立成功");
            return ResponseVO.buildSuccess(new AuthorMapVO(nodes,links));
        }catch (Exception e){
            log.error("AuthorMap服务失败");
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("AuthorMap服务失败");
        }
    }
}
