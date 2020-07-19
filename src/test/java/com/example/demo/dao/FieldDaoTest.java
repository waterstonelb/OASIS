package com.example.demo.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class FieldDaoTest {

    @Autowired
    FieldDao fieldDao;

    @Test
    public void getAllFieldsTest(){
        List<String> res = fieldDao.getAllFields();
        assert res.size() > 0;

    }

    @Test
    public void getConferencesByFieldsTest(){
        List<String> res = fieldDao.getConferencesByFields(Arrays.asList("A", "B"));
        assert res.size() > 0;
    }
}
