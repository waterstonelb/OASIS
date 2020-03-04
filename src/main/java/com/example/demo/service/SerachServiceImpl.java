package com.example.demo.service;


import com.example.demo.dao.*;
import com.example.demo.po.*;
import com.example.demo.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SerachServiceImpl implements SearchService{

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AuthorPublishDao authorPublishDao;

    @Autowired
    private AffiliationDao affiliationDao;

    @Autowired
    private AffiliationPublishDao affiliationPublishDao;


    @Override
    public ResponseVO<List<Document>>  seaechByAuthor(String author) {
        try{

            List<Author> authors = authorDao.findByNameContaining(author);
            if (authors.isEmpty())
                return ResponseVO.buildFailure("未查询到匹配的论文");

            List<Integer> docIds = new ArrayList<>();
            for (Author authorpo : authors){
                List<AuthorPublish> authorPublishes =
                        authorPublishDao.findByAuthorId(authorpo.getId());
                for (AuthorPublish ap : authorPublishes)
                    docIds.add(ap.getDocumentId());
            }

            List<Document> res = new ArrayList<>();
            for (Integer docId : docIds){
                res.add(documentDao.findFirstById(docId));
            }

            return ResponseVO.buildSuccess(res);

        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO<List<Document>>  searchByInstitution(String institution) {
        try{
            List<Affiliation> affiliations
                    = affiliationDao.findByNameContaining(institution);
            if (affiliations.isEmpty())
                return ResponseVO.buildFailure("未查询到匹配的论文");

            List<Integer> docIds = new ArrayList<>();
            for (Affiliation aff : affiliations){
                List<AffiliationPublish> affiliationPublishes =
                        affiliationPublishDao.findByAffId(aff.getId());
                for (AffiliationPublish ap : affiliationPublishes)
                    docIds.add(ap.getDocumentId());
            }

            List<Document> res = new ArrayList<>();
            for (Integer docId : docIds){
                res.add(documentDao.findFirstById(docId));
            }

            return ResponseVO.buildSuccess(res);

        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }

    @Override
    public ResponseVO<List<Document>>  searchByConference(String conference) {
        try{

            List<Document> res = documentDao
                    .findByPublicationContaining(conference);
            if (res.isEmpty())
                return ResponseVO.buildFailure("未查询到匹配的论文");

            return ResponseVO.buildSuccess(res);

        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }


    @Override
    public ResponseVO<List<Document>>  searchByStudyKeyword(String keyword) {
        try{

            List<Document> res = documentDao
                    .findByKeywordsContaining(keyword);

            if (res.isEmpty())
                return ResponseVO.buildFailure("未查询到匹配的论文");

            return ResponseVO.buildSuccess(res);

        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseVO.buildFailure("Error");
        }
    }
}
