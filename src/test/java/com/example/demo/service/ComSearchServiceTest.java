package com.example.demo.service;

import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Document;
import com.example.demo.vo.ComSearchInpVO;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComSearchServiceTest {
    @Test
    void comSearchCocument() {
        DocumentDao documentDao=mock(DocumentDao.class);
        ComSearchServiceImpl comSearchService=new ComSearchServiceImpl(documentDao);
        List<Document> testRes=new ArrayList<>();
        Document document=new Document();
        document.setAuthors("JPnix");
        testRes.add(document);
        when(documentDao.find("Penix", "Google", "ACM", "")).thenReturn(testRes);
        assertEquals("组合查询成功", comSearchService.comSearchCocument(ComSearchInpVO.builder()
                .authors("Penix")
                .institution("Google")
                .conference("ACM")
                .keyword("")
                .build()).getMessage());
    }
}