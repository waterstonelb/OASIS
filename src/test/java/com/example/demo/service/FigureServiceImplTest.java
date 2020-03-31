package com.example.demo.service;


import com.example.demo.service.serviceinterface.FigureService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.figure.AffiliationFigureVO;
import com.example.demo.vo.figure.AuthorFigureVO;
import com.example.demo.vo.figure.FieldFigureVO;
import com.example.demo.vo.figure.IdLink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Id;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FigureServiceImplTest {
    @Autowired
    FigureService figureService;

    @Test
    public void getAuthorFigureTest(){
        ResponseVO<AuthorFigureVO> res = figureService.getAuthorFigure();
        assert res.getData().getLinks().size() > 0;
        assert res.getData().getNodes().size() > 0;
    }

    @Test
    public void getAffiliationFigureTest(){
        ResponseVO<AffiliationFigureVO> res = figureService.getAffiliationFigure();
        assert res.getData().getLinks().size() > 0;
        assert res.getData().getNodes().size() > 0;
    }

    @Test
    public void IdLinkTest(){
        IdLink idLink1 = new IdLink(2, 9);
        IdLink idLink2 = new IdLink(2, 9);
        assert idLink1.equals(idLink2);
        assert idLink1.hashCode() == idLink2.hashCode();
    }

    @Test
    public void getFieldFigureTest(){
        ResponseVO<FieldFigureVO> res = figureService.getFieldFigure();
        System.out.println(res.getData().getLinks().size());
        System.out.println(res.getData().getNodes().size());
        assert res.getData().getLinks().size() > 0;
        assert res.getData().getNodes().size() > 0;
    }
}
