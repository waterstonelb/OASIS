package com.example.demo.service;

import com.example.demo.dao.DocumentDao;
import com.example.demo.vo.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.SearchVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComSearchServiceTest {
    @Autowired
    ComSearchService comSearchService;
    @Test
    void comSearchCocument() {

       ResponseVO<SearchVO> res= comSearchService.comSearchDocument(ComSearchInpVO.builder().build());

    }
}