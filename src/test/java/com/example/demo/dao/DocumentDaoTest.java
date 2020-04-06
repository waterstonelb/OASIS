package com.example.demo.dao;

import com.example.demo.po.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentDaoTest {

    @Autowired
    DocumentDao documentDao;


    @Test
    public void findByAuthorTest(){
        Page<Document> res=documentDao.findByAuthor("Ali",0, 9999, PageRequest.of(0,10));
        assertFalse(res.getContent().isEmpty());
    }

    @Test
    public void findByInsTest(){
        Page<Document> res = documentDao.findByInstitution("Nanjing University",
                0, 9999,
                PageRequest.of(0, 5));
        assertFalse(res.getContent().isEmpty());
    }

    @Test
    public void findByPubTest(){
        Page<Document> res = documentDao.findByPublication(
                "2019 34th IEEE/ACM International Conference"
        ,0, 9999, PageRequest.of(0, 10));
        assertFalse(res.getContent().isEmpty());
    }

    @Test
    public void findByKwTest(){
        Page<Document> res = documentDao.findByKeywords(
                "Object oriented"
                ,0, 9999
                ,PageRequest.of(0, 5)
        );
        assertFalse(res.getContent().isEmpty());
    }


    @Test
    public void comFindTest(){
        Page<Document> res=documentDao.comFind(
                "Ali","Dallas","34th",null, 0, 9999,
                PageRequest.of(0,10, Sort.Direction.DESC,"citationCount"));
        assertFalse(res.getContent().isEmpty());
    }


    @Test
    public void findByAuthorIdTest(){
        List<Document> res=documentDao.findByAuthorId(614);
        assertEquals(3,res.size());
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