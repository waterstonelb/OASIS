package com.example.demo.service;

import com.example.demo.dao.AffiliationDao;
import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.service.serviceinterface.RankingService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.ranking.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RankingServiceImpl implements RankingService {


    private static final String[] FIELDS = {
            "Aerospace",
            "Bioengineering",
            "Communication, Networking and Broadcast Technologies",
            "Components, Circuits, Devices and Systems",
            "Computing and Processing",
            "Engineered Materials, Dielectrics and Plasmas",
            "Fields, Waves and Electromagnetics",
            "Geoscience",
            "Nuclear Engineering",
            "Photonics and Electrooptics",
            "Power, Energy and Industry Applications",
            "Robotics and Control Systems",
            "Signal Processing and Analysis",
            "Transportation"
    };

    private static final String[] SEARCH_REGEXP = {
            "aerospace",
            "biolog",
            "communication|networking|broadcast",
            "component|circuit|devices",
            "computing|processing",
            "material|dielectrics|plasma",
            "field|wave|electromagnetic",
            "geolog",
            "nuclear",
            "photonic|electrooptic",
            "power|energy|industry",
            "robotic|control system",
            "signal",
            "transportation"

    };

    private final Map<String, String> FIELD_TO_REG;

    private static final Pageable top50 = PageRequest.of(0, 50);

    private static final int[] RECENT_YEARS = {2010, 2011, 2012, 2013, 2014, 2015,
            2016, 2017, 2018, 2019,2020};

    public RankingServiceImpl(){
        FIELD_TO_REG = new HashMap<>();
        for(int i = 0; i < FIELDS.length; i++)
            FIELD_TO_REG.put(FIELDS[i], SEARCH_REGEXP[i]);
    }

    /*
    hindex --> 参数是某个机构或作者的发表文章的引用数列表
    shkbscore = 引用数/文章数
     */


    private DocumentDao documentDao;
    @Autowired
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    private AffiliationDao affiliationDao;
    @Autowired
    public void setAffiliationDao(AffiliationDao affiliationDao) {
        this.affiliationDao = affiliationDao;
    }

    private AuthorDao authorDao;
    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public ResponseVO<List<String>> getAllFields() {
        try {
            log.info("获取领域成功");
            List<String> res = Arrays.asList(FIELDS);
            return ResponseVO.buildSuccess(res);
        }catch (Exception ex){
            log.error("获取领域失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("获取领域失败");
        }

    }

    @Override
    @Cacheable(value = "affRankingCache", key = "#affRankingInpVO")
    public ResponseVO<AffRankingResVO> getAffList(AffRankingInpVO affRankingInpVO) {
        try {
            int startTime = affRankingInpVO.getStartTime() == null
                    ? 2010 : affRankingInpVO.getStartTime();
            int endTime = affRankingInpVO.getEndTime() == null
                    ? 2020 : affRankingInpVO.getEndTime();
            List<String> fields = affRankingInpVO.getFields();
            String sortBy = affRankingInpVO.getSortBy();
            List<Integer> docIds = new ArrayList<>();
            for(String field : fields)
                docIds.addAll(documentDao
                        .getDocIdsByFieldAndTime(FIELD_TO_REG.get(field), startTime, endTime));
            docIds = docIds.stream().distinct().collect(Collectors.toList());

            List<AffRankingVO> resList = new ArrayList<>();
            switch (sortBy) {
                case "Count":
                    resList = affiliationDao.getAffTopSortByCount(docIds, top50).getContent();
                    break;
                case "Citation":
                    resList = affiliationDao.getAffTopSortByCitation(docIds, top50).getContent();
                    break;
                case "SHKBScore":
                    resList = affiliationDao.getAffTopSortByShkbScore(docIds, top50).getContent();
                    break;
            }
            for(AffRankingVO affRankingVO : resList)
                affRankingVO.setHIndex(countHindex(affiliationDao
                        .getHindexListFilterByField(docIds, affRankingVO.getAffiliationId())));

            AffRankingResVO affRankingResVO = AffRankingResVO.builder()
                    .list(resList).total(50).build();
            log.info("获取机构列表成功");
            return ResponseVO.buildSuccess(affRankingResVO);

        }catch (Exception ex){
            log.error("获取机构列表失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("获取机构列表失败");
        }
    }

    @Override
    @Cacheable(value = "affDetailCache", key = "#affDetailInpVO")
    public ResponseVO<AffDetailVO> getAffDetail(AffDetailInpVO affDetailInpVO) {
        try {
            int affId = affDetailInpVO.getAffiliationId();
            List<Long> barVal = new ArrayList<>();
            List<Integer> barName = new ArrayList<>();
            for(int year : RECENT_YEARS) {
                barName.add(year);
                barVal.add(affiliationDao.getPaperCountByYear(affId, year));
            }
            Bar bar = Bar.builder().name(barName).value(barVal).build();
            List<PieItem> pie = new ArrayList<>();
            for(String field : FIELDS){
                List<Integer> docIds = documentDao.getDocIdsByFieldAndTime(
                        FIELD_TO_REG.get(field), 2010, 2020
                );
                long pieVal = affiliationDao.getPaperCountByField(affId, docIds);
                pie.add(PieItem.builder().name(field).value(pieVal).build());
            }
            log.info("获取机构详情成功");
            return ResponseVO.buildSuccess(AffDetailVO.builder().bar(bar).pie(pie).build());

        }catch (Exception ex){
            log.error("获取机构详情失败");
            return ResponseVO.buildFailure("获取机构详情失败");
        }
    }

    @Override
    @Cacheable(value = "authorRankingCache", key = "#authorRankingInpVO")
    public ResponseVO<AuthorRankingResVO> getAuthorList(AuthorRankingInpVO authorRankingInpVO) {
        try {
            int startTime = authorRankingInpVO.getStartTime() == null
                    ? 2010 : authorRankingInpVO.getStartTime();
            int endTime = authorRankingInpVO.getEndTime() == null
                    ? 2020 : authorRankingInpVO.getEndTime();
            List<String> fields = authorRankingInpVO.getFields();
            String sortBy = authorRankingInpVO.getSortBy();
            List<Integer> docIds = new ArrayList<>();
            for(String field : fields)
                docIds.addAll(documentDao
                        .getDocIdsByFieldAndTime(FIELD_TO_REG.get(field), startTime, endTime));
            docIds = docIds.stream().distinct().collect(Collectors.toList());

            List<AuthorRankingVO> resList = new ArrayList<>();
            switch (sortBy) {
                case "Count":
                    resList = authorDao.getAuTopSortByCount(docIds, top50).getContent();
                    break;
                case "Citation":
                    resList = authorDao.getAuTopSortByCitation(docIds, top50).getContent();
                    break;
                case "SHKBScore":
                    resList = authorDao.getAuTopSortByShkbScore(docIds, top50).getContent();
                    break;
            }
            for(AuthorRankingVO authorRankingVO : resList) {
                authorRankingVO.setHIndex(countHindex(authorDao
                        .getHindexListFilterByField(docIds, authorRankingVO.getAuthorId())));
                int auId = authorRankingVO.getAuthorId();
                List<Long> barVal = new ArrayList<>();
                List<Integer> barName = new ArrayList<>();
                for(int year : RECENT_YEARS) {
                    barName.add(year);
                    barVal.add(authorDao.getPaperCountByYear(auId, year));
                }
                Bar bar = Bar.builder().name(barName).value(barVal).build();
                authorRankingVO.setPublicationTrend(bar);
            }

            AuthorRankingResVO authorRankingResVO = AuthorRankingResVO.builder()
                    .list(resList).total(50).build();
            log.info("获取作者列表成功");
            return ResponseVO.buildSuccess(authorRankingResVO);

        }catch (Exception ex){
            log.error("获取作者列表失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("获取作者列表失败");
        }
    }

    @Override
    @Cacheable(value = "authorDetailCache", key = "#authorDetailInpVO")
    public ResponseVO<AuthorDetailVO> getAuthorDetail(AuthorDetailInpVO authorDetailInpVO) {
        try {
            int auId = authorDetailInpVO.getAuthorId();

            List<PieItem> pie = new ArrayList<>();
            for(String field : FIELDS){
                List<Integer> docIds = documentDao.getDocIdsByFieldAndTime(
                        FIELD_TO_REG.get(field), 2010, 2020
                );
                long pieVal = authorDao.getPaperCountByField(auId, docIds);
                pie.add(PieItem.builder().name(field).value(pieVal).build());
            }
            List<PaperItem> papers = authorDao.getTopPapers(auId,
                    PageRequest.of(0, 5)).getContent();
            log.info("获取作者详情成功");
            return ResponseVO.buildSuccess(AuthorDetailVO.builder()
                    .papers(papers).pie(pie).build());

        }catch (Exception ex){
            log.error("获取作者详情失败");
            return ResponseVO.buildFailure("获取作者详情失败");
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
