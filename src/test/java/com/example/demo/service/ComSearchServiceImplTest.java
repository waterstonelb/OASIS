package com.example.demo.service;

import com.example.demo.vo.search.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.search.SearchVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ComSearchServiceImplTest {
    @Autowired
    ComSearchServiceImpl comSearchService;
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
                .keywords("Test ERROR")
                .page(1)
                .size(1)
                .sortBy(0)
                .build());
        assertEquals("未查询到匹配的论文",res.getMessage());
    }
    @Test
    public void searchError(){
        ResponseVO<SearchVO> res=comSearchService.comSearchDocument(ComSearchInpVO.builder()
                .authors("Ali")
                .institution("Dallas")
                .conference("34th")
                .keywords("Test ERROR")
                .page(1)
                .size(1)
                .startTime(-1)
                .endTime(-10)
                .sortBy(0)
                .build());
        assertEquals("未查询到匹配的论文",res.getMessage());
    }
}