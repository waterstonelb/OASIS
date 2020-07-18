package com.example.demo.controller;


import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.field.FieldInpVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @GetMapping("/fields")
    public ResponseVO<List<String>> getFields(){
        return null;
    }

    @PostMapping("/conferenceType")
    public ResponseVO<List<String>> getConferencesByFields(@RequestBody FieldInpVO fieldInpVO){
        return null;
    }
}
