package com.example.demo.controller;

import com.example.demo.service.serviceinterface.FigureService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.figure.AffiliationFigureVO;
import com.example.demo.vo.figure.AuthorFigureVO;
import com.example.demo.vo.figure.FieldFigureVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/figure")
public class FigureController {

    private FigureService figureService;

    @Autowired
    public FigureController(FigureService figureService){
        this.figureService = figureService;
    }



    @ApiOperation("查看作者关系大图")
    @GetMapping("/authors")
    public ResponseVO<AuthorFigureVO> getAuthorFigure() {
        log.info("查看作者关系大图");
        return figureService.getAuthorFigure();
    }


    @ApiOperation("查看机构关系大图")
    @GetMapping("/affiliations")
    public ResponseVO<AffiliationFigureVO> getAffiliationFigure() {
        log.info("查看机构关系大图");
        return figureService.getAffiliationFigure();
    }


    @ApiOperation("查看领域关系大图")
    @GetMapping("/fields")
    public ResponseVO<FieldFigureVO> getFieldFigure() {

        log.info("查看领域关系大图");
        return figureService.getFieldFigure();
    }
}
