package com.example.demo.service;


import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.dao.RefDao;
import com.example.demo.po.Author;
import com.example.demo.po.Document;
import com.example.demo.vo.PaperInfoVO;
import com.example.demo.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperInfoServiceImpl implements PaperInfoService{

    private DocumentDao documentDao;
    private AuthorDao authorDao;
    private RefDao refDao;

    @Autowired
    public PaperInfoServiceImpl(DocumentDao documentDao, AuthorDao authorDao, RefDao refDao) {
        this.authorDao = authorDao;
        this.documentDao = documentDao;
        this.refDao = refDao;
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
            int refereneceCount=refDao.countByDocId(documentId);
            return ResponseVO.buildSuccess("查询成功",
                    new PaperInfoVO(document,authors,10));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("查询失败");
        }
    }
}