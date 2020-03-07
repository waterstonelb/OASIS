package com.example.demo.controller;


import com.example.demo.service.serviceinterface.InterestingService;
import com.example.demo.vo.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/interesting/top10")
public class InterestingController {

    private static final int TOPNUM = 10;

    private InterestingService interestingService;

    @Autowired
    public InterestingController(InterestingService interestingService){
        this.interestingService = interestingService;
    }

    @ApiOperation(value = "返回发表文章数量前10的作者")
    @GetMapping("/author")
    public ResponseVO<List<TopAuthorVO>> getTopAuthor(){
        log.info("查询文章数量前10的作者");
        return interestingService.getTopAuthor();
    }

    @ApiOperation(value = "返回发表文章数量前10的机构")
    @GetMapping("/institution")
    public ResponseVO<List<TopInstitutionVO>> getTopInstitution(){
        log.info("查询发表文章数量前10的机构");
        return interestingService.getTopInstitution();
    }

    @ApiOperation(value = "返回被引用数前10的文章")
    @GetMapping("/paper")
    public ResponseVO<List<TopCiteDocVO>> getTopCiteDoc(){
        log.info("查询被引用数前10的文章");
        return interestingService.getTopCiteDoc();
    }

    @ApiOperation("作者推荐")
    @GetMapping("/authorrecommend")
    public ResponseVO<AuthorRecommend> authorRecommend(){
        log.info("作者推荐");
        return interestingService.authorRecommand();
    }
}
