package com.example.demo.service;


import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Author;
import com.example.demo.po.Document;
import com.example.demo.service.serviceinterface.PaperInfoService;
import com.example.demo.vo.paper.PaperInfoVO;
import com.example.demo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaperInfoServiceImpl implements PaperInfoService {

    private DocumentDao documentDao;
    private AuthorDao authorDao;

    @Autowired
    public PaperInfoServiceImpl(DocumentDao documentDao, AuthorDao authorDao) {
        this.authorDao = authorDao;
        this.documentDao = documentDao;
    }

    /**
     * @param documentId 论文ID
     * @return PaperInfoVO
     * @author 梁斌
     */
    @Override
    public ResponseVO<PaperInfoVO> getPaperInfo(int documentId) {
        try{
            Document document=documentDao.findById(documentId);
            List<Author> authors=authorDao.findByDocumentId(documentId);
            int refereneceCount = 0;

            log.info("查询成功");
            return ResponseVO.buildSuccess("查询成功",
                    new PaperInfoVO(document,authors,refereneceCount));

        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("查询失败");
        }
    }
}
