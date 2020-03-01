package com.example.demo.service;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DemoApplication.class})
class ComSearchServiceTest {
    @Autowired
    ComSearchService comSearchService;

    @Test
    void comSearchCocument() {
        //System.out.println(comSearchService.comSearchCocument("Penix", "Google", "ACM", "").getMessage());
        assertEquals("组合查询成功", comSearchService.comSearchCocument("Penix", "Google", "ACM", "").getMessage());
    }
}