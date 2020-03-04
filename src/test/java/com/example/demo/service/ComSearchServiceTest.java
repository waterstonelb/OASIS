//package com.example.demo.service;
//
//import com.example.demo.dao.DocumentDao;
//import com.example.demo.po.Document;
//import com.example.demo.vo.ComSearchInpVO;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//public class ComSearchServiceTest {
//    @Test
//    void comSearchCocument() {
//
//        DocumentDao documentDao=mock(DocumentDao.class);
//        List<Document> list=new ArrayList<>();
//        Page<Document> p=new PageImpl<>(list);
//        ComSearchServiceImpl comSearchService=new ComSearchServiceImpl(documentDao);
//
//        when(documentDao.find("Ali","Dallas","34th","", isA(Pageable.class))).thenReturn(p);
//        assertEquals("组合查询成功", comSearchService.comSearchDocument(ComSearchInpVO.builder()
//                .authors("Ali")
//                .institution("Dallas")
//                .conference("34th")
//                .keyword("")
//                .build()).getMessage());
//
//    }
//}