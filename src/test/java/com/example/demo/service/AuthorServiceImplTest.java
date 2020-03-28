package com.example.demo.service;

import com.example.demo.vo.author.AuthorMapVO;
import com.example.demo.vo.author.AuthorVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceImplTest {

    @Autowired
    AuthorServiceImpl authorService;

    @Test
    public void getAuthorInfo() {
        ResponseVO<AuthorVO> res=authorService.getAuthorInfo(599);
        assert res.getData().getKeywords().size()>0;
    }

    @Test
    public void getAuthorMap() {
        ResponseVO<AuthorMapVO> res=authorService.getAuthorMap(5);
        assert res.getData().getNodes().size()>0;
    }
}