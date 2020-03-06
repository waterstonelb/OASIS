package com.example.demo.controller;


import com.example.demo.po.Author;
import com.example.demo.service.InterestingService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.TopAuthorVO;
import com.example.demo.vo.TopCiteDocVO;
import com.example.demo.vo.TopInstitutionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return interestingService.getTopAuthor();
    }

    @ApiOperation(value = "返回发表文章数量前10的机构")
    @GetMapping("/institution")
    public ResponseVO<List<TopInstitutionVO>> getTopInstitution(){
        return interestingService.getTopInstitution();
    }

    @ApiOperation(value = "返回被引用数前10的文章")
    @GetMapping("/paper")
    public ResponseVO<List<TopCiteDocVO>> getTopCiteDoc(){
        return interestingService.getTopCiteDoc();
    }

    @ApiOperation("作者推荐")
    @GetMapping("/authorrecommend")
    public ResponseVO<Author> authorRecommend(){
        return interestingService.authorRecommand();
    }
}
