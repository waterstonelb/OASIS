package com.example.demo.controller;


import com.example.demo.po.Document;
import com.example.demo.service.ComSearchService;
import com.example.demo.vo.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.SearchVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comsearch")
public class ComSearchController {

    private ComSearchService comSearchService;
    @Autowired
    public void setComSearchService(ComSearchService comSearchService){
        this.comSearchService=comSearchService;
    }

    @ApiOperation(value = "组合查询（返回论文）")
    @PostMapping("/document")
    public ResponseVO<SearchVO> getComDocument(@RequestBody ComSearchInpVO comSearchInpVO){
        return comSearchService.comSearchDocument(comSearchInpVO);
    }


}
