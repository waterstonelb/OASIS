package com.example.demo.service;


import com.example.demo.service.serviceinterface.FigureService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.figure.AffiliationFigureVO;
import com.example.demo.vo.figure.AuthorFigureVO;
import com.example.demo.vo.figure.FieldFigureVO;
import com.example.demo.vo.figure.IdLink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FigureServiceImplTest {
    @Autowired
    FigureService figureService;

    @Test
    public void getAuthorFigureTest(){
        ResponseVO<AuthorFigureVO> res = figureService.getAuthorFigure();
        assertFalse(res.getData().getLinks().isEmpty());
        assertFalse(res.getData().getNodes().isEmpty());
    }

    @Test
    public void getAffiliationFigureTest(){
        ResponseVO<AffiliationFigureVO> res = figureService.getAffiliationFigure();
        assertFalse(res.getData().getLinks().isEmpty());
        assertFalse(res.getData().getNodes().isEmpty());
    }

    @Test
    public void IdLinkTest(){
        IdLink idLink1 = new IdLink(2, 9);
        IdLink idLink2 = new IdLink(2, 9);
        assertEquals( idLink1,idLink2);
        assertEquals( idLink1.hashCode(),idLink2.hashCode());
    }

    @Test
    public void getFieldFigureTest(){
        ResponseVO<FieldFigureVO> res = figureService.getFieldFigure();
        System.out.println(res.getData().getLinks().size());
        System.out.println(res.getData().getNodes().size());
        assertFalse(res.getData().getLinks().isEmpty());
        assertFalse(res.getData().getNodes().isEmpty());
    }
}
