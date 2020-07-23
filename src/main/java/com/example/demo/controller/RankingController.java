package com.example.demo.controller;


import com.example.demo.service.serviceinterface.RankingService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.ranking.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ranking")
public class RankingController {


    private RankingService rankingService;


    @Autowired
    public void setRankingService(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/fields")
    public ResponseVO<List<String>> getFields(){
        return rankingService.getAllFields();
    }

    @PostMapping("/affiliationList")
    public ResponseVO<AffRankingResVO> getAffList(@RequestBody AffRankingInpVO affRankingInpVO){
        return rankingService.getAffList(affRankingInpVO);
    }

    @PostMapping("/affiliationDetails")
    public ResponseVO<AffDetailVO> getAffDetail(@RequestBody AffDetailInpVO affDetailInpVO){
        return rankingService.getAffDetail(affDetailInpVO);
    }

    @PostMapping("/authorList")
    public ResponseVO<AuthorRankingResVO> getAuthorList(@RequestBody AuthorRankingInpVO authorRankingInpVO){
        return rankingService.getAuthorList(authorRankingInpVO);
    }

    @PostMapping("/authorDetails")
    public ResponseVO<AuthorDetailVO> getAuthorDetail(@RequestBody AuthorDetailInpVO authorDetailInpVO){
        return rankingService.getAuthorDetail(authorDetailInpVO);
    }

}
