package com.example.demo.controller;


import com.example.demo.service.serviceinterface.ComSearchService;
import com.example.demo.vo.search.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.search.SearchVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        log.info("发起组合查询");
        return comSearchService.comSearchDocument(comSearchInpVO);
    }


}
