package com.example.demo.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DocumentDaoTest {

    @Autowired
    DocumentDao documentDao;



    @Test
    void findSuccess() {
        assertEquals(2,documentDao.find("Ali","Dallas","34th","").size());
    }
    @Test
    void findFail(){
        assertEquals(0,documentDao.find("Ali","Dallas","34th","null").size());
    }
}