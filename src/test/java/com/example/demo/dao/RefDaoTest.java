package com.example.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RefDaoTest {

    @Autowired
    RefDao refDao;

    @Test
    public void countByDocId() {

        assertTrue(refDao.countByDocId(2)>0);
    }
}