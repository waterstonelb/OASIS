package com.example.demo.controller;

import com.example.demo.service.serviceinterface.AffiliationService;
import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/affiliation")
public class AffiliationController {
    AffiliationService affiliationService;

    @Autowired
    public AffiliationController(AffiliationService affiliationService) {
        this.affiliationService = affiliationService;
    }

    @ApiOperation("机构详细信息查询")
    @GetMapping("/info")
    public ResponseVO<AffiliationVO> getAffiliationInfo(@RequestParam int affId) throws ExecutionException {
        return affiliationService.getAffiliationInfo(affId);
    }
}
