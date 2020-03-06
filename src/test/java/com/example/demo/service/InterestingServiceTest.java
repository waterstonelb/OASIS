package com.example.demo.service;

import com.example.demo.po.Author;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.TopAuthorVO;
import com.example.demo.vo.TopCiteDocVO;
import com.example.demo.vo.TopInstitutionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InterestingServiceTest {

    @Autowired
    InterestingService interestingService;


    @Test
    public void getTopAuthorTest() {
        ResponseVO<List<TopAuthorVO>> res = interestingService.getTopAuthor();
        assertEquals(10, res.getData().size());
    }

    @Test
    public void getTopInstitutionTest() {
        ResponseVO<List<TopInstitutionVO>> res = interestingService.getTopInstitution();
        assertEquals(10, res.getData().size());
    }

    @Test
    public void getTopCiteDocTest() {
        ResponseVO<List<TopCiteDocVO>> res = interestingService.getTopCiteDoc();
        assertEquals(10, res.getData().size());
    }
    @Test
    public void authorRecommendTest(){
        ResponseVO<Author> res=interestingService.authorRecommand();
        assertEquals("Jun Sun",res.getData().getName());
    }
}