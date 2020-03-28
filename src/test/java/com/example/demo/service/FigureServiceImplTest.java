package com.example.demo.service;


import com.example.demo.service.serviceinterface.FigureService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.figure.AuthorFigureVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
