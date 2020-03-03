package com.example.demo.service;


import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Document;
import com.example.demo.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerachServiceImpl implements SearchService{

    private DocumentDao documentDao;

    @Autowired
    public SerachServiceImpl(DocumentDao documentDao){
        this.documentDao = documentDao;
    }

    @Override
    public ResponseVO<List<Document>>  seaechByAuthor(String author) {
        try{
//            List<Document> res = documentDao.findByAuthorsContaining(author);
//            return ResponseVO.buildSuccess(res);
            return null;
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO<List<Document>>  searchByInstitution(String institution) {
        try{
//            List<Document> res = documentDao.
//                    findByAuthorAffiliationsContaining(institution);
//            return ResponseVO.buildSuccess(res);
            return null;
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }

    @Override
    public ResponseVO<List<Document>>  searchByConference(String conference) {
        try{
//            List<Document> res = documentDao.
//                    findByPublicationTitleContaining(conference);
//            return ResponseVO.buildSuccess(res);
            return null;
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }


    @Override
    public ResponseVO<List<Document>>  searchByStudyKeyword(String keyword) {
        try{
//            List<Document> res = documentDao.
//                    findByAuthorKeywordsContaining(keyword);
//            return ResponseVO.buildSuccess(res);
            return null;
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }
}
