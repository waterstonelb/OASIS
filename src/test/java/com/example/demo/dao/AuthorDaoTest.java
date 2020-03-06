package com.example.demo.dao;

import com.example.demo.po.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorDaoTest {
    @Autowired
    private AuthorDao authorDao;

    @Test
    public void findFirstByNameAndAffiliation() {
        Author author = authorDao.
                findFirstByNameAndAffiliation("Jia Liu", "Nanjing University");

        assertEquals("Jia Liu", author.getName());
    }

    @Test
    public void existsByNameAndAffiliation() {
        boolean exists = authorDao
                .existsByNameAndAffiliation("dhfvysdfguydsgefy", "sdyuwgduwfdytfw");
        assertFalse(exists);
    }

    @Test
    public void findByNameContaining() {
        List<Author> authors = authorDao
                .findByNameContaining("Jia Liu");

        assert authors.size() > 0;
    }

    @Test
    public void findByDocumentId() {
        List<Author> authors = authorDao
                .findByDocumentId(33);
        assert authors.size() > 0;
    }
    @Test
    public void authorRecommendTEST(){
        assertEquals("Jun Sun",authorDao.authorRecommend().getName());
    }
}