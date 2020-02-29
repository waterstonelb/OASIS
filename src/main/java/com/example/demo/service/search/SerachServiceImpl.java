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
        List<Document> res = documentDao.findByAuthorsContaining("penix");
        return ResponseVO.buildSuccess(res);
    }

    @Override
    public ResponseVO searchByInstitution(String institution) {
        return null;
    }

    @Override
    public ResponseVO searchByConference(String conference) {
        return null;
    }

    @Override
    public ResponseVO searchByStudyKeyword(String keyword) {
        return null;
    }
}
