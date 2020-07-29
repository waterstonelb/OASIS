package com.example.demo.service;


import com.example.demo.dao.*;
import com.example.demo.po.*;
import com.example.demo.service.serviceinterface.SearchService;
import com.example.demo.vo.*;
import com.example.demo.vo.paper.DocumentVO;
import com.example.demo.vo.search.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {


    private DocumentDao documentDao;

    @Autowired
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    private AuthorDao authorDao;

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    @Override
    public ResponseVO<SearchVO> searchByAuthor(SearchByAuthorInpVO searchByAuthorInpVO) {
        try {
            PageRequest pageRequest = PageRequest.of(
                    searchByAuthorInpVO.getPage(),
                    searchByAuthorInpVO.getSize(),
                    Sort.by(Sort.Direction.DESC,
                            searchByAuthorInpVO.getSortby() == 0 ? "publicationYear" : "citationCount"));

            Page<Document> queryRes = documentDao.findByAuthor(
                    searchByAuthorInpVO.getAuthor(),
                    searchByAuthorInpVO.getStartTime() == null ? 0 : searchByAuthorInpVO.getStartTime(),
                    searchByAuthorInpVO.getEndTime() == null ? 9999 : searchByAuthorInpVO.getEndTime(),
                    pageRequest);


            List<DocumentVO> resVO = new ArrayList<>();

            for (Document document : queryRes.getContent())
                resVO.add(new DocumentVO(document, authorDao.findByDocumentId(document.getId())));

            log.info("查询成功");
            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));


        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO<SearchVO> searchByInstitution(SearchByInstitutionInpVO searchByInstitutionInpVO) {
        try {
            PageRequest pageRequest = PageRequest.of(
                    searchByInstitutionInpVO.getPage(),
                    searchByInstitutionInpVO.getSize(),
                    Sort.by(Sort.Direction.DESC,
                            searchByInstitutionInpVO.getSortby() == 0 ? "publicationYear" : "citationCount"));

            Page<Document> queryRes = documentDao.findByInstitution(
                    searchByInstitutionInpVO.getInstitution(),
                    searchByInstitutionInpVO.getStartTime() == null ? 0 : searchByInstitutionInpVO.getStartTime(),
                    searchByInstitutionInpVO.getEndTime() == null ? 9999 : searchByInstitutionInpVO.getEndTime(),
                    pageRequest);


            List<DocumentVO> resVO = new ArrayList<>();

            for (Document document : queryRes.getContent())
                resVO.add(new DocumentVO(document, authorDao.findByDocumentId(document.getId())));

            log.info("查询成功");
            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));


        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("Error");
        }
    }

    @Override
    public ResponseVO<SearchVO> searchByConference(SearchByConferenceInpVO searchByConferenceInpVO) {
        try {

            PageRequest pageRequest = PageRequest.of(
                    searchByConferenceInpVO.getPage(),
                    searchByConferenceInpVO.getSize(),
                    Sort.by(Sort.Direction.DESC,
                            searchByConferenceInpVO.getSortby() == 0 ? "publicationYear" : "citationCount"));


            Page<Document> queryRes = documentDao.findByPublication(
                    searchByConferenceInpVO.getConference(),
                    searchByConferenceInpVO.getStartTime() == null ? 0 : searchByConferenceInpVO.getStartTime(),
                    searchByConferenceInpVO.getEndTime() == null ? 9999 : searchByConferenceInpVO.getEndTime(),
                    pageRequest);


            List<DocumentVO> resVO = new ArrayList<>();

            for (Document document : queryRes.getContent())
                resVO.add(new DocumentVO(document, authorDao.findByDocumentId(document.getId())));

            log.info("查询成功");
            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));


        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("Error");
        }
    }


    @Override
    public ResponseVO<SearchVO> searchByStudyKeyword(SearchByKeywordInpVO searchByKeywordInpVO) {
        try {

            PageRequest pageRequest = PageRequest.of(
                    searchByKeywordInpVO.getPage(),
                    searchByKeywordInpVO.getSize(),
                    Sort.by(Sort.Direction.DESC,
                            searchByKeywordInpVO.getSortby() == 0 ? "publicationYear" : "citationCount"));

            Page<Document> queryRes = documentDao.findByKeywords(
                    searchByKeywordInpVO.getKeyword(),
                    searchByKeywordInpVO.getStartTime() == null ? 0 : searchByKeywordInpVO.getStartTime(),
                    searchByKeywordInpVO.getEndTime() == null ? 9999 : searchByKeywordInpVO.getEndTime(),
                    pageRequest);


            List<DocumentVO> resVO = new ArrayList<>();

            for (Document document : queryRes.getContent())
                resVO.add(new DocumentVO(document, authorDao.findByDocumentId(document.getId())));

            log.info("查询成功");
            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));

        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("Error");
        }
    }

    @Override
    public ResponseVO<SearchVO> searchByTitle(SearchByTitleVO searchByTitleVO) {
        try {
            PageRequest pageRequest = PageRequest.of(
                    searchByTitleVO.getPage(),
                    searchByTitleVO.getSize(),
                    Sort.by(Sort.Direction.DESC,
                            searchByTitleVO.getSortby() == 0 ? "publicationYear" : "citationCount"));


            Page<Document> queryRes = documentDao.findByTitle(
                    searchByTitleVO.getTitle(),
                    searchByTitleVO.getStartTime() == null ? 0 : searchByTitleVO.getStartTime(),
                    searchByTitleVO.getEndTime() == null ? 9999 : searchByTitleVO.getEndTime(),
                    pageRequest);
            List<DocumentVO> resVO = new ArrayList<>();

            for (Document document : queryRes.getContent())
                resVO.add(new DocumentVO(document, authorDao.findByDocumentId(document.getId())));
            log.info("查询成功");
            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("Error in searchByTitle");
        }
    }

    @Override
    public ResponseVO<SearchVO> searchByAffiliation(SearchByAffiliationIdVO searchByAffiliationIdVO) {
        try {
            PageRequest pageRequest = PageRequest.of(
                    searchByAffiliationIdVO.getPage(),
                    searchByAffiliationIdVO.getSize(),
                    Sort.by(Sort.Direction.DESC,
                            searchByAffiliationIdVO.getSortby() == 0 ? "publicationYear" : "citationCount"));

            Page<Document> queryRes = documentDao.findByAffId(
                    searchByAffiliationIdVO.getAffId(),
                    searchByAffiliationIdVO.getStartTime() == null ? 0 : searchByAffiliationIdVO.getStartTime(),
                    searchByAffiliationIdVO.getEndTime() == null ? 9999 : searchByAffiliationIdVO.getEndTime(),
                    pageRequest);


            List<DocumentVO> resVO = new ArrayList<>();

            for (Document document : queryRes.getContent())
                resVO.add(new DocumentVO(document, authorDao.findByDocumentId(document.getId())));

            log.info("查询成功");
            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));


        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("Error");
        }
    }

    @Override
    public ResponseVO<SearchVO> searchByAuthorId(SearchByAuthorIdVO searchByAuthorIdVO) {
        try {
            PageRequest pageRequest = PageRequest.of(
                    searchByAuthorIdVO.getPage(),
                    searchByAuthorIdVO.getSize(),
                    Sort.by(Sort.Direction.DESC,
                            searchByAuthorIdVO.getSortby() == 0 ? "publicationYear" : "citationCount"));
            Page<Document> res = documentDao.getByAuthorId(
                    searchByAuthorIdVO.getAuthorId(),
                    searchByAuthorIdVO.getStartTime() == null ? 0 : searchByAuthorIdVO.getStartTime(),
                    searchByAuthorIdVO.getEndTime() == null ? 9999 : searchByAuthorIdVO.getEndTime(),
                    pageRequest);
            List<DocumentVO> resVO = new ArrayList<>();
            for (Document document : res.getContent())
                resVO.add(new DocumentVO(document, authorDao.findByDocumentId(document.getId())));

            if (!res.isEmpty()) {
                log.info("查询成功");
                return ResponseVO.buildSuccess("查询成功", new SearchVO(res.getTotalElements(), resVO));

            } else {
                log.info(new Date().toString() + "未查询到匹配的论文");
                return ResponseVO.buildFailure("未查询到匹配的论文");
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("作者详情文章查询失败");
        }
    }
}
