package com.example.demo.dao;

import com.example.demo.po.Document;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DocumentDaoTest {

    @Autowired
    DocumentDao documentDao;

    PageRequest pr;

    @Before
    public void setUp(){
        pr=PageRequest.of(0,2);
    }


    @Test
    void findSuccess() {

        assertEquals(1, documentDao.findFirstById(1).getId());
    }

    @Test
    void pageFindTest(){
        Page<Document> res=documentDao.find("Ali","Dallas","34th","",pr);
        System.out.println(res.getContent().size());
//        assertEquals(2,res.getTotalElements());
    }
    @Test
    void normalQueryPage(){
        //PageRequest pr=PageRequest.of(0,2);
        Page<Document> res=documentDao.findByDid(2,pr);
        System.out.println(res.getContent().get(0).getTitle());
    }

    @Test
    void normalPageFind(){
        Page<Document> res=documentDao.findAll(pr);
        System.out.println(res.getTotalElements());

    }
}