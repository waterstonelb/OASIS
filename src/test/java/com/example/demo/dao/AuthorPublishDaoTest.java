package com.example.demo.dao;

import com.example.demo.po.AuthorDirectInfo;
import com.example.demo.po.AuthorPublish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorPublishDaoTest {

    @Autowired
    private AuthorPublishDao authorPublishDao;

    @Test
    public void findByAuthorId() {
        List<AuthorPublish> authorPublish = authorPublishDao.findByAuthorId(1);
        assert authorPublish.size() > 0;

    }

    @Test
    public void countByAuthorId() {
    }

    @Test
    public void sumCitationCountByAuthorId() {
    }

    @Test
    public void findAuthorKeyWords() {
        List<String> res = authorPublishDao.findAuthorKeyWords(166);
        assert res.size() > 0;
    }

    @Test
    public void findTopAuthor() {
    }

    @Test
    public void getAuthorDirect() {
        List<AuthorDirectInfo> res=authorPublishDao.getAuthorDirect(5);
        assert res.size()>0;
    }

    @Test
    public void getAuthorRelations() {
        List<Integer> res=authorPublishDao.getAuthorRelations(5);
        assert res.size()>0;
    }
}