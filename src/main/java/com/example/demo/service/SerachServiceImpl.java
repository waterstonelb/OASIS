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
        this.documentDao = authorDao;
    }


    @Override
    public ResponseVO<SearchVO>  seaechByAuthor(SearchByAuthorInpVO searchByAuthorInpVO) {
        try{
            String author = searchByAuthorInpVO.getAuthor();
            int page = searchByAuthorInpVO.getPage();
            int size = searchByAuthorInpVO.getSize();
            String sortBy = searchByAuthorInpVO.getSortby() == 0 ? "publicationYear" : "citationCount";

            PageRequest pageRequest = PageRequest.of(page, size,
                    Sort.by(Sort.Direction.DESC, sortBy));

            Page<Document> queryRes = documentDao.findByAuthor(author, pageRequest);
            long total = queryRes.getTotalElements();
            List<Document> res = queryRes.getContent();

            List<DocumentVO> vores = new ArrayList<>();

            for (Document document : res)
                vores.add(new DocumentVO(document, authorDao.))

            return ResponseVO.buildSuccess(new SearchVO(total, res));


        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO<SearchVO>  searchByInstitution(SearchByInstitutionInpVO searchByInstitutionInpVO) {
        try{
            String institution = searchByInstitutionInpVO.getInstitution();
            int page = searchByInstitutionInpVO.getPage();
            int size = searchByInstitutionInpVO.getSize();
            String sortBy = searchByInstitutionInpVO.getSortby() == 0 ? "publicationYear" : "citationCount";

            PageRequest pageRequest = PageRequest.of(page, size,
                    Sort.by(Sort.Direction.DESC, sortBy));
            Page<Document> queryRes = documentDao.findByInstitution(institution, pageRequest);
            long total = queryRes.getTotalElements();
            List<Document> res = queryRes.getContent();

            return ResponseVO.buildSuccess(new SearchVO(total, res));

        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }

    @Override
    public ResponseVO<SearchVO>  searchByConference(SearchByConferenceInpVO searchByConferenceInpVO) {
        try{
            String conference = searchByConferenceInpVO.getConference();
            int page = searchByConferenceInpVO.getPage();
            int size = searchByConferenceInpVO.getSize();
            String sortBy = searchByConferenceInpVO.getSortby() == 0 ? "publicationYear" : "citationCount";
            PageRequest pageRequest = PageRequest.of(page, size,
                    Sort.by(Sort.Direction.DESC, sortBy));
            Page<Document> queryRes = documentDao.findByPublicationContaining(conference, pageRequest);
            long total = queryRes.getTotalElements();
            List<Document> res = queryRes.getContent();

            return ResponseVO.buildSuccess(new SearchVO(total, res));


        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }


    @Override
    public ResponseVO<SearchVO>  searchByStudyKeyword(SearchByKeywordInpVO searchByKeywordInpVO) {
        try{
            String keyword = searchByKeywordInpVO.getKeyword();
            int page = searchByKeywordInpVO.getPage();
            int size = searchByKeywordInpVO.getSize();
            String sortBy = searchByKeywordInpVO.getSortby() == 0 ? "publicationYear" : "citationCount";
            PageRequest pageRequest = PageRequest.of(page, size,
                    Sort.by(Sort.Direction.DESC, sortBy));
            Page<Document> queryRes = documentDao.findByKeywordsContaining(keyword, pageRequest);
            long total = queryRes.getTotalElements();
            List<Document> res = queryRes.getContent();

            return ResponseVO.buildSuccess(new SearchVO(total, res));

        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }
}
