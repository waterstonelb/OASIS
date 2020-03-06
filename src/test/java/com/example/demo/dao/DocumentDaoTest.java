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

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentDaoTest {

    @Autowired
    DocumentDao documentDao;


    @Test
    public void findByAuthorTest(){
        Page<Document> res=documentDao.findByAuthor("Ali",0, 9999, PageRequest.of(0,10));
        System.out.println(res.getTotalElements());
        System.out.println(res.getContent().size());
    }
    @Test
    public void comFindTest(){
        Page<Document> res=documentDao.comFind(
                "Ali","Dallas","34th",null, 0, 9999,
                PageRequest.of(0,10, Sort.Direction.DESC,"citationCount"));
        System.out.println(res.getTotalElements());
        System.out.println(res.getContent().size());
    }





}