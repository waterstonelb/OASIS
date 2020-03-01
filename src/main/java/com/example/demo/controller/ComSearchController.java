package com.example.demo.controller;


import com.example.demo.po.Document;
import com.example.demo.service.ComSearchService;
import com.example.demo.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComSearchController {

    private ComSearchService comSearchService;
    @Autowired
    public void setComSearchService(ComSearchService comSearchService){
        this.comSearchService=comSearchService;
    }

    @ApiOperation(value = "组合查询（返回论文）")
    @RequestMapping(value = "/comsearch/document",method = RequestMethod.GET)
    public ResponseVO<List<Document>> getComDocument(
            @RequestParam("author") String author,
            @RequestParam("institution") String institution,
            @RequestParam("conference") String conference,
            @RequestParam("keyword") String keyword){
        return comSearchService.comSearchCocument(author,institution,conference,keyword);
    }


}
