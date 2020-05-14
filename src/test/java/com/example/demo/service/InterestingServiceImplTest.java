package com.example.demo.service;

import com.example.demo.vo.*;
import com.example.demo.vo.author.AuthorRecommend;
import com.example.demo.vo.top.TopAuthorVO;
import com.example.demo.vo.top.TopCiteDocVO;
import com.example.demo.vo.top.TopInstitutionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
        assertNotNull(res.getData().getName());
    }
}