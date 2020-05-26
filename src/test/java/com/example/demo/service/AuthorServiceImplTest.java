package com.example.demo.service;

import com.example.demo.vo.author.AuthorMapVO;
import com.example.demo.vo.author.AuthorVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthorServiceImplTest {

    @Autowired
    AuthorServiceImpl authorService;

    @Test
    public void getAuthorInfo() {
        ResponseVO<AuthorVO> res=authorService.getAuthorInfo(5);
        assertFalse(res.getData().getKeywords().isEmpty());
    }

    @Test
    public void getAuthorMap() {
        ResponseVO<AuthorMapVO> res=authorService.getAuthorMap(5);
        assertFalse(res.getData().getNodes().isEmpty());
    }
}