package com.example.demo.controller;

import com.example.demo.service.PaperInfoService;
import com.example.demo.vo.PaperInfoVO;
import com.example.demo.vo.ResponseVO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paper")
public class PaperInfoController {

    PaperInfoService paperInfoService;

    @Autowired
    public PaperInfoController(PaperInfoService paperInfoService) {
        this.paperInfoService = paperInfoService;
    }

    @ApiOperation(value = "查询具体文章信息")
    @GetMapping("/info")
    public ResponseVO<PaperInfoVO> getPaperInfo(@RequestParam int documentId){
        return paperInfoService.getPaperInfo(documentId);
    }


}
