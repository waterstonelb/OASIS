package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class AffiliationDaoTest {

    @Autowired
    private AffiliationDao affiliationDao;

    @Test
    void existsByName() {
        boolean exists = affiliationDao.existsByName("Nanjing University");
        assertTrue(exists);
    }

    @Test
    void findFirstByName() {
        Affiliation res = affiliationDao.findFirstByName("Nanjing University");
        assertEquals("Nanjing University", res.getName());
    }


}