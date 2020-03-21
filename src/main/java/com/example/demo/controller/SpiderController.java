package com.example.demo.controller;

import com.example.demo.datasource.Data;
import com.example.demo.service.serviceinterface.DataLoadService;
import com.example.demo.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SpiderController {

    private DataLoadService dataLoadService;

    @Autowired
    public SpiderController(DataLoadService dataLoadService){
        this.dataLoadService = dataLoadService;
    }

    @PostMapping("/spider/data")
    public ResponseVO<Object> persist(@RequestBody Data data){
        dataLoadService.loadSingle(data);
        return ResponseVO.buildSuccess(null);
    }
}
