package com.example.demo.serviceTest;



import com.example.demo.po.Document;
import com.example.demo.service.search.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest

public class SearchTest {

    @Autowired
    SearchService searchService;

    @Test
    public void testAuthor(){
        List<Document> documents = (List<Document>)
                searchService.seaechByAuthor("Y. Wang").getData();

        assert documents.size() >= 1;
    }

    @Test
    public void testInstitution(){
        List<Document> documents = (List<Document>)
                searchService.searchByInstitution("tsinghua university").getData();

        assert documents.size() >= 1;
    }

    @Test
    public void testConference(){
        List<Document> documents = (List<Document>)
                searchService.searchByConference("2019 34th IEEE/ACM International Conference").getData();

        assert documents.size() >= 1;
    }

    @Test
    public void testKeyword(){
        List<Document> documents = (List<Document>)
                searchService.searchByStudyKeyword("pointer analysis").getData();

        assert documents.size() >= 1;
    }
}
