package com.example.demo.serviceTest;


import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest

public class SearchTest {

    @Autowired
    DocumentDao documentDao;

    @Test
    public void test(){


        assertEquals(2, documentDao.findByAuthorsContaining("j. peni").get(0).getId().intValue());


    }
}
