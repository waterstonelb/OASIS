package com.example.demo.dao;

import com.example.demo.po.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DocumentDaoTest {

    @Autowired
    DocumentDao documentDao;


    @Test
    public void findByAuthorTest(){
        Page<Document> res=documentDao.findByAuthor("Sinisa Krajnovic",0, 9999, PageRequest.of(0,10));
        assertFalse(res.getContent().isEmpty());
    }

    @Test
    public void findByInsTest(){
        Page<Document> res = documentDao
                .findByInstitution("Development Unit (Engineering) for Network Products, Ericsson, Sweden",
                0, 9999,
                PageRequest.of(0, 5));
        assertFalse(res.getContent().isEmpty());
    }

    @Test
    public void findByPubTest(){
        Page<Document> res = documentDao.findByPublication(
                "2017 14th International Conference on Telecommunications (ConTEL)"
        ,0, 9999, PageRequest.of(0, 10));
        assertFalse(res.getContent().isEmpty());
    }

    @Test
    public void findByKwTest(){
        Page<Document> res = documentDao.findByKeywords(
                "Virtualization"
                ,0, 9999
                ,PageRequest.of(0, 5)
        );
        assertFalse(res.getContent().isEmpty());
    }


    @Test
    public void comFindTest(){
        Page<Document> res=documentDao.comFind(
                "Sinisa Krajnovic","Sweden","14th",null, 0, 9999,
                PageRequest.of(0,10, Sort.Direction.DESC,"citationCount"));
        assertFalse(res.getContent().isEmpty());
    }


    @Test
    public void findByAuthorIdTest(){
        List<Document> res=documentDao.findByAuthorId(4);
        assertFalse(res.isEmpty());
    }

    @Test
    public void findByIdTest(){
        Document res = documentDao.findById(6);
        assertEquals(6, res.getId().intValue());
    }

    @Test
    public void getAllKeyWordsTest(){
        List<String> res = documentDao.findAllKeywords();
        assertFalse(res.isEmpty());
    }


}