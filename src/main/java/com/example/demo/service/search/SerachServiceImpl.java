package com.example.demo.service.search;


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
    public ResponseVO seaechByAuthor(String author) {
        try{
            List<Document> res = documentDao.findByAuthorsContaining(author);
            return ResponseVO.buildSuccess(res);
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO searchByInstitution(String institution) {
        try{
            List<Document> res = documentDao.
                    findByAuthorAffiliationsContaining(institution);
            return ResponseVO.buildSuccess(res);
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }

    @Override
    public ResponseVO searchByConference(String conference) {
        try{
            List<Document> res = documentDao.
                    findByPublicationTitleContaining(conference);
            return ResponseVO.buildSuccess(res);
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }


    @Override
    public ResponseVO searchByStudyKeyword(String keyword) {
        try{
            List<Document> res = documentDao.
                    findByAuthorKeywordsContaining(keyword);
            return ResponseVO.buildSuccess(res);
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }
}
