package com.example.demo.dao;

import com.example.demo.po.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthorDaoTest {
    @Autowired
    private AuthorDao authorDao;


    @Test
    public void findByNameContaining() {
        List<Author> authors = authorDao
                .findByNameContaining("Sinisa Krajnovic");

        assertFalse(authors.isEmpty());
    }

    @Test
    public void findByDocumentId() {
        List<Author> authors = authorDao
                .findByDocumentId(33);
        assertFalse(authors.isEmpty());
    }
    @Test
    public void authorRecommendTEST(){
        assertNotNull(authorDao.authorRecommend());
    }

    @Test
    public void getHindexList() {
        List<Integer> res=authorDao.getHindexList(4);
        assertFalse(res.isEmpty());
    }
}