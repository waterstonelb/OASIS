package com.example.demo.service;

import com.example.demo.vo.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.SearchVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ComSearchServiceTest {
    @Autowired
    ComSearchService comSearchService;
    @Test
    public void comSearchCocument() {
        ResponseVO<SearchVO> res=comSearchService.comSearchDocument(ComSearchInpVO.builder()
                .authors("Ali")
                .institution("Dallas")
                .conference("34th")
                .page(1)
                .size(1)
                .sortBy(0)
                .build());
        assertEquals(1,res.getData().getDocuments().size());
    }
    @Test
    public void searchNULL(){
        ResponseVO<SearchVO> res=comSearchService.comSearchDocument(ComSearchInpVO.builder()
                .authors("Ali")
                .institution("Dallas")
                .conference("34th")
                .keyword("Test ERROR")
                .page(1)
                .size(1)
                .sortBy(0)
                .build());
        assertEquals("未查询到匹配的论文",res.getMessage());
    }
}