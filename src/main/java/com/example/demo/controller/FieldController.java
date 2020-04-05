package com.example.demo.controller;

import com.example.demo.service.serviceinterface.FieldService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.field.FieldPieVO;
import com.example.demo.vo.field.FieldPromptVO;
import com.example.demo.vo.field.FieldWcVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fields")
public class FieldController {

    private FieldService fieldService;
    @Autowired
    public FieldController(FieldService fieldService){
        this.fieldService = fieldService;
    }

    @ApiOperation("研究领域云图")
    @GetMapping("/wordcloud")
    public ResponseVO<List<FieldWcVO>> getFieldWc(){
        return fieldService.getFieldWc();
    }

    @ApiOperation("研究领域饼图")
    @GetMapping("/pie")
    public ResponseVO<List<FieldPieVO>> getFieldPie(@RequestParam String field){
        return fieldService.getFieldPie(field);
    }

    @ApiOperation("研究领域查询实时提醒")
    @GetMapping("/search")
    public ResponseVO<List<FieldPromptVO>> getFieldPrompt(@RequestParam String field){
        return fieldService.getFieldPrompt(field);
    }

}
