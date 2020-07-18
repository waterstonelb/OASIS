package com.example.demo.controller;

import com.example.demo.service.serviceinterface.MergeService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.merge.MergeAffFormVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/merge")
public class MergeController {

    private MergeService mergeService;
    @Autowired
    public void setMergeService(MergeService mergeService){
        this.mergeService = mergeService;
    }

    @GetMapping("/getAffiliations")
    public ResponseVO<List<String>> getAffiliations(){
        log.info("获取所有机构名称");
        return mergeService.getAffiliations();
    }

    @PostMapping("/mergeAffiliations")
    public ResponseVO<List<String>> mergeAffiliations(@RequestBody MergeAffFormVO mergeAffFormVO) {
        log.info("合并机构");
        return mergeService.mergeAffiliations(mergeAffFormVO);
    }
}
