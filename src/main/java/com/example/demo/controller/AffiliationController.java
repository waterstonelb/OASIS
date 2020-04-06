package com.example.demo.controller;

import com.example.demo.service.serviceinterface.AffiliationService;
import com.example.demo.vo.affiliation.AffPieChartVO;
import com.example.demo.vo.affiliation.AffTableVO;
import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseVO<AffiliationVO> getAffiliationInfo(@RequestParam int affId){
        return affiliationService.getAffiliationInfo(affId);
    }

    @ApiOperation("查询机构饼状图信息")
    @GetMapping("/piechart")
    public ResponseVO<List<AffPieChartVO>> getAffiliationPieChart(@RequestParam int affId){
        return affiliationService.getAffPieChart(affId);
    }

    @ApiOperation("查询机构paper数量统计图")
    @GetMapping("/table/paper")
    public ResponseVO<AffTableVO> getAffiliationPaperTable(@RequestParam int affId){
        return affiliationService.getPaperTable(affId);
    }
    @ApiOperation("查询机构citation数量统计图")
    @GetMapping("/table/citation")
    public ResponseVO<AffTableVO> getAffiliationCitationTable(@RequestParam int affId){
        return affiliationService.getCitationTable(affId);
    }
    @ApiOperation("查询机构Hindex统计图")
    @GetMapping("/table/hindex")
    public ResponseVO<AffTableVO> getAffiliationHindexTable(@RequestParam int affId){
        return affiliationService.getHindexTable(affId);
    }
}
