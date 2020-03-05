package com.example.demo.service;

import com.example.demo.dao.DocumentDao;
import com.example.demo.vo.ComSearchInpVO;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class ComSearchServiceTest {
    @Test
    void comSearchCocument() {

        DocumentDao documentDao=mock(DocumentDao.class);
//        List<Document> list=new ArrayList<>();
//        list.add(Document.builder().build());
//        Page<Document> p=new PageImpl<>(list);
        ComSearchServiceImpl comSearchService=new ComSearchServiceImpl(documentDao);

        PageRequest pr=PageRequest.of(0,2);

        assertEquals("组合查询失败", comSearchService.comSearchDocument(ComSearchInpVO.builder()
                .authors("Ali")
                .institution("Dallas")
                .conference("34th")
                .keyword("")
                .page(0)
                .size(2)
                .build()).getMessage());

    }
}