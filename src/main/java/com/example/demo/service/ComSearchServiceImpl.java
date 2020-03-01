package com.example.demo.service;

import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Document;
import com.example.demo.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComSearchServiceImpl implements ComSearchService {
    private DocumentDao documentDao;

    @Autowired
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    @Override
    public ResponseVO<List<Document>> comSearchCocument(String author, String institution, String conference, String keyword) {
        try {
            List<Document> res = documentDao.find(author, institution, conference, keyword);
            //System.out.println(res.size());
            if(res.size()>=1)
                return ResponseVO.buildSuccess("组合查询成功", res);
            else
                return ResponseVO.buildFailure("未查询到匹配的论文");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("组合查询失败");
        }
    }
}
