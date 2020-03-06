package com.example.demo.service;


import com.example.demo.dao.*;
import com.example.demo.po.*;
import com.example.demo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SerachServiceImpl implements SearchService{


    private DocumentDao documentDao;
    @Autowired
    public void setDocumentDao(DocumentDao documentDao){
        this.documentDao = documentDao;
    }

    private AuthorDao authorDao;
    @Autowired
    public void setAuthorDao(AuthorDao authorDao){
        this.authorDao = authorDao;
    }


    @Override
    public ResponseVO<SearchVO>  seaechByAuthor(SearchByAuthorInpVO searchByAuthorInpVO) {
        try{
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

            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));


        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO<SearchVO>  searchByInstitution(SearchByInstitutionInpVO searchByInstitutionInpVO) {
        try{
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

            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));



        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }

    @Override
    public ResponseVO<SearchVO>  searchByConference(SearchByConferenceInpVO searchByConferenceInpVO) {
        try{

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

            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));


        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }


    @Override
    public ResponseVO<SearchVO>  searchByStudyKeyword(SearchByKeywordInpVO searchByKeywordInpVO) {
        try{

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

            return ResponseVO.buildSuccess(new SearchVO(queryRes.getTotalElements(), resVO));

        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }
}
