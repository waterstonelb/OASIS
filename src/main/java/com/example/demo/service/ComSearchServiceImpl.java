package com.example.demo.service;

import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Document;
import com.example.demo.vo.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComSearchServiceImpl implements ComSearchService {
    private DocumentDao documentDao;

    @Autowired
    public ComSearchServiceImpl(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }


    @Override
    public ResponseVO<List<Document>> comSearchDocument(ComSearchInpVO comSearchInpVO) {
        try {
            PageRequest pageRequest=PageRequest.of(comSearchInpVO.getPage(),comSearchInpVO.getSize(),
                    Sort.by(Sort.Direction.DESC,"id"));
            Page<Document> res = documentDao.find(
                    comSearchInpVO.getAuthors(),
                    comSearchInpVO.getInstitution(),
                    comSearchInpVO.getConference(),
                    comSearchInpVO.getKeyword(),pageRequest);
            if(res.isEmpty())
                return ResponseVO.buildSuccess("组合查询成功", res.getContent());
            else
                return ResponseVO.buildFailure("未查询到匹配的论文");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("组合查询失败");
        }

    }
}
