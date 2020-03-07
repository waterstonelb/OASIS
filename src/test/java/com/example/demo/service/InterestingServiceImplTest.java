package com.example.demo.service;

import com.example.demo.service.serviceinterface.InterestingService;
import com.example.demo.vo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InterestingServiceImplTest {

    @Autowired
    InterestingServiceImpl interestingService;


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
        ResponseVO<AuthorRecommend> res=interestingService.authorRecommand();
        assertEquals("Jun Sun",res.getData().getName());
    }
}