package com.example.demo.dao;

import com.example.demo.po.AuthorPublish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorPublishDaoTest {

    @Autowired
    private AuthorPublishDao authorPublishDao;

    @Test
    public void findByAuthorId() {
        List<AuthorPublish> authorPublish = authorPublishDao.findByAuthorId(1);
        assert authorPublish.size() > 0;

    }
}