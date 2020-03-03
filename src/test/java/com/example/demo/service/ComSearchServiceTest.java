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
        testRes.add(document);
        when(documentDao.find("Ali","Dallas","34th","")).thenReturn(testRes);
        assertEquals("组合查询成功", comSearchService.comSearchCocument(ComSearchInpVO.builder()
                .authors("Ali")
                .institution("Dallas")
                .conference("34th")
                .keyword("")
                .build()).getMessage());

    }
}