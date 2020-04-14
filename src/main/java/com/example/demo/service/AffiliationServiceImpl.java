package com.example.demo.service;

import com.example.demo.dao.AffiliationDao;
import com.example.demo.dao.AffiliationPublishDao;
import com.example.demo.po.Affiliation;
import com.example.demo.po.AffiliationTable;
import com.example.demo.po.HindexEntry;
import com.example.demo.service.serviceinterface.AffiliationService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.affiliation.AffPieChartVO;
import com.example.demo.vo.affiliation.AffTableVO;
import com.example.demo.vo.affiliation.AffiliationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AffiliationServiceImpl implements AffiliationService {
    private AffiliationDao affiliationDao;
    private AffiliationPublishDao affiliationPublishDao;
    private int START_YEAR=2014;
    private int END_YEAR=2019;

    @Autowired
    public AffiliationServiceImpl(AffiliationDao affiliationDao, AffiliationPublishDao affiliationPublishDao) {
        this.affiliationDao = affiliationDao;
        this.affiliationPublishDao = affiliationPublishDao;
    }

    @Override
    public ResponseVO<AffiliationVO> getAffiliationInfo(int affId) {
        try {
            log.info(Thread.currentThread().getName()+"  success");
            log.info("查询机构信息id="+affId);
            Affiliation affiliation=affiliationDao.findFirstById(affId);
            int paperCount=affiliationDao.getPaperCount(affId);
            int citationCount=affiliationDao.getCitationCount(affId);
            int authorCount=affiliationDao.getAuthorCount(affId);
            int shkbScore=citationCount*authorCount/paperCount;
            List<Integer> hlist=affiliationDao.getHindexList(affId);
            int hindex=countHindex(hlist);
            log.info("查询机构信息成功");
            return ResponseVO.buildSuccess(AffiliationVO.builder()
                     .authorCount(authorCount)
                     .citationCount(citationCount)
                     .Hindex(hindex)
                     .name(affiliation.getName())
                     .paperCount(paperCount)
                     .shkbScore(shkbScore)
                     .build());
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("机构详情获取失败");
        }
    }

    @Override
    public ResponseVO<List<AffPieChartVO>> getAffPieChart(int affId) {
        try{
            List<AffPieChartVO> affKeys = new ArrayList<>();
            List<String> keywords=affiliationPublishDao.findAffiliationKeyWords(affId);
            if(keywords.isEmpty()){
                affKeys.add(AffPieChartVO.builder().value(1).name("待更新").build());
                return ResponseVO.buildSuccess(affKeys);
            }
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

            float max_num=1;
            for (int i = 0; i < Math.min(list.size(), 10); i++)
                max_num+=list.get(i).getValue();
            for (int i = 0; i < Math.min(list.size(), 10); i++)
                affKeys.add(AffPieChartVO.builder()
                        .name(list.get(i).getKey())
                        .value(list.get(i).getValue()/max_num)
                        .build());
            return ResponseVO.buildSuccess(affKeys);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("机构饼状图查询失败");
        }
    }

    @Override
    public ResponseVO<AffTableVO> getHindexTable(int affId) {
        try {
            List<HindexEntry> res=affiliationDao.getHindexListWithYear(affId);
            HashMap<Integer,List<Integer>> map=new HashMap<>();
            for(HindexEntry a:res){
                int year=a.getYear();
                int data=a.getData();
                List<Integer> temp;
                if(map.containsKey(year)){
                    temp=map.get(year);
                }else {
                    temp = new ArrayList<>();
                }
                temp.add(data);
                map.put(year,temp);
            }
            List<AffiliationTable> tables=new ArrayList<>();
            for(int year = START_YEAR;year<=END_YEAR;year++){
                if(map.containsKey(year)) {
                    List<Integer> hlist = map.get(year);
                    int hindex = countHindex(hlist);
                    tables.add(new AffiliationTable(year,hindex));
                }else
                    tables.add(new AffiliationTable(year,0));
            }
            return ResponseVO.buildSuccess(new AffTableVO(tables));
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("机构H指数统计失败");
        }
    }

    @Override
    public ResponseVO<AffTableVO> getPaperTable(int affId) {
        try {
            List<AffiliationTable> res= affiliationDao.getPaperCountWithYear(affId);
            HashMap<Integer,Long> map=new HashMap<>();
            for (AffiliationTable a:res) {
                map.put(a.getYear(),a.getData());
            }
            List<AffiliationTable> tables=new ArrayList<>();
            for(int year = START_YEAR;year<=END_YEAR;year++){
                if(map.containsKey(year)) {
                    tables.add(new AffiliationTable(year,map.get(year)));
                }else
                    tables.add(new AffiliationTable(year,0));
            }
            return ResponseVO.buildSuccess(new AffTableVO(tables));
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("机构文章数量统计失败");
        }
    }

    @Override
    public ResponseVO<AffTableVO> getCitationTable(int affId) {
        try {
            List<AffiliationTable> res= affiliationDao.getCitationWithYear(affId);
            HashMap<Integer,Long> map=new HashMap<>();
            for (AffiliationTable a:res) {
                map.put(a.getYear(),a.getData());
            }
            List<AffiliationTable> tables=new ArrayList<>();
            for(int year = START_YEAR;year<=END_YEAR;year++){
                if(map.containsKey(year)) {
                    tables.add(new AffiliationTable(year,map.get(year)));
                }else
                    tables.add(new AffiliationTable(year,0));
            }
            return ResponseVO.buildSuccess(new AffTableVO(tables));
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("机构citation统计失败");
        }
    }

    private int countHindex(List<Integer> hlist){
        hlist.sort(Integer::compareTo);
        int len=hlist.size();
        int hindex=0;
        for (int i=0; i < len; i++) {
            if(hlist.get(i)>=len-i){
                hindex=len-i;
                break;
            }
        }
        return hindex;
    }
}
