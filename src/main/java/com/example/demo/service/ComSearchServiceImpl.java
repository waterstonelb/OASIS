package com.example.demo.service;

import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Document;
import com.example.demo.vo.ComSearchInpVO;
import com.example.demo.vo.DocumentVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ComSearchServiceImpl implements ComSearchService {
    private DocumentDao documentDao;
    private AuthorDao authorDao;

    @Autowired
    public ComSearchServiceImpl(DocumentDao documentDao, AuthorDao authorDao) {
        this.authorDao = authorDao;
        this.documentDao = documentDao;
    }



    @Override
    public ResponseVO<SearchVO> comSearchDocument(ComSearchInpVO comSearchInpVO) {
        try {
            PageRequest pageRequest=PageRequest.of(
                    comSearchInpVO.getPage(),
                    comSearchInpVO.getSize(),
                    Sort.by(Sort.Direction.DESC,
                            comSearchInpVO.getSortBy()==0?"publicationYear" : "citationCount"));
            Page<Document> res = documentDao.comFind(
                    comSearchInpVO.getAuthors(),
                    comSearchInpVO.getInstitution(),
                    comSearchInpVO.getConference(),
                    comSearchInpVO.getKeyword(),pageRequest);


            List<DocumentVO> resVO = new ArrayList<>();

            for (Document document : res.getContent())
                resVO.add(new DocumentVO(document, authorDao.findByDocumentId(document.getId())));

            if(!res.isEmpty()) {
                return ResponseVO.buildSuccess("组合查询成功", new SearchVO(res.getTotalElements(), resVO));
            }
            else
                return ResponseVO.buildFailure("未查询到匹配的论文");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("组合查询失败");
        }


    }
}
