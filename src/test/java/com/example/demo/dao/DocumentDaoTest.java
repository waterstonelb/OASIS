package com.example.demo.dao;

import com.example.demo.po.Document;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DocumentDaoTest {

    @Autowired
    DocumentDao documentDao;


    @Test
    void findByAuthorTest(){
        Page<Document> res=documentDao.findByAuthor("Ali", PageRequest.of(0,10));
        System.out.println(res.getTotalElements());
        System.out.println(res.getContent().size());
    }
    @Test
    void comFindTest(){
        Page<Document> res=documentDao.comFind("Ali","Dallas","34th",null,
                PageRequest.of(0,10, Sort.Direction.DESC,""));
        System.out.println(res.getTotalElements());
        System.out.println(res.getContent().size());
    }

    @Test
    public void findByAuTest(){
        PageRequest pageRequest = PageRequest.of(0, 5,
                Sort.by(Sort.Direction.DESC, "publicationYear"));

        Page<Document> res = documentDao.findByInstitution("Nanjing University", pageRequest);

        for (Document d : res.getContent())
            System.out.println(d.getDoi());

    }



}