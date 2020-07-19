package com.example.demo.controller;


import com.example.demo.service.serviceinterface.RankingService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.field.FieldInpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/conferenceType")
    public ResponseVO<List<String>> getConferencesByFields(@RequestBody FieldInpVO fieldInpVO){
        return rankingService.getConferencesByFields(fieldInpVO.getFields());
    }
}
