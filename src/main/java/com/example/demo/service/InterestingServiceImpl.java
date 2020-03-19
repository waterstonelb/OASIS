package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.po.Author;
import com.example.demo.po.Document;
import com.example.demo.po.TopAffliation;
import com.example.demo.po.TopAuthor;
import com.example.demo.service.serviceinterface.InterestingService;
import com.example.demo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InterestingServiceImpl implements InterestingService {

    private static final int TOPNUM = 10;

    private AuthorPublishDao authorPublishDao;

    @Autowired
    public void setAuthorPublishDao(AuthorPublishDao authorPublishDao){
        this.authorPublishDao = authorPublishDao;
    }

    private AuthorDao authorDao;

    @Autowired
    public void setAuthorDao(AuthorDao authorDao){
        this.authorDao = authorDao;
    }

    private AffiliationPublishDao affiliationPublishDao;

    @Autowired
    public void setAffiliationPublishDao(AffiliationPublishDao affiliationPublishDao){
        this.affiliationPublishDao = affiliationPublishDao;
    }

    private AffiliationDao affiliationDao;

    @Autowired
    public void setAffiliationDao(AffiliationDao affiliationDao){
        this.affiliationDao = affiliationDao;
    }

    private DocumentDao documentDao;

    @Autowired
    public void setDocumentDao(DocumentDao documentDao){
        this.documentDao = documentDao;
    }

    @Override
    public ResponseVO<List<TopAuthorVO>> getTopAuthor() {
        try {
            List<TopAuthorVO> topAuthorVOS = new ArrayList<>();
            List<TopAuthor> topAuthors = authorPublishDao.findTopAuthor(
                    PageRequest.of(0, TOPNUM)).getContent();


            for (TopAuthor topAuthor : topAuthors){
                topAuthorVOS.add(
                        new TopAuthorVO(
                                topAuthor.getAuthorId(),
                                authorDao.findFirstById(topAuthor.getAuthorId()).getName(),
                                topAuthor.getPaperCount()
                        )
                );
            }
            log.info("查找top10作者成功");
            return ResponseVO.buildSuccess(topAuthorVOS);
        }catch (Exception ex){
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO<List<TopInstitutionVO>> getTopInstitution() {
        try {
            List<TopInstitutionVO> topInstitutionVOS = new ArrayList<>();
            List<TopAffliation> topAffliations =
                    affiliationPublishDao.findTopAffliation(
                            PageRequest.of(0, TOPNUM)
                    ).getContent();

            for (TopAffliation topAffliation : topAffliations){
                topInstitutionVOS.add(
                        new TopInstitutionVO(
                        topAffliation.getAffId(),
                        affiliationDao.findFirstById(topAffliation.getAffId()).getName(),
                        topAffliation.getPaperCount()
                        )
                );
            }
            log.info("查询top10机构成功");
            return ResponseVO.buildSuccess(topInstitutionVOS);

        }catch (Exception ex){
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO<List<TopCiteDocVO>> getTopCiteDoc() {
        try{
            List<TopCiteDocVO> topCiteDocVOS = new ArrayList<>();
            List<Document> topDocs = documentDao.findTopCiteDoc(
                    PageRequest.of(0, TOPNUM));
            for (Document document : topDocs){
                topCiteDocVOS.add(
                        new TopCiteDocVO(
                                document.getId(),
                                document.getTitle(),
                                document.getCitationCount()
                        )
                );
            }
            log.info("查询top10文章成功");
            return ResponseVO.buildSuccess(topCiteDocVOS);
        }catch (Exception ex){
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("Error");
        }

    }

    @Override
    public ResponseVO<AuthorRecommend> authorRecommand() {
        try{
            Author author=authorDao.authorRecommend();
            List<Document> documents=documentDao.findByAuthorId(author.getId());
            int citation=0;
            for(Document d:documents){
                citation+=d.getCitationCount();
            }
            AuthorRecommend authorRecommend=new AuthorRecommend(author,citation,documents);

            log.info("推荐成功");
            return ResponseVO.buildSuccess(authorRecommend);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("推荐失败");
        }
    }
}
