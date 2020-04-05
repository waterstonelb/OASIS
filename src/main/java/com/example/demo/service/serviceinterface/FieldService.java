package com.example.demo.service.serviceinterface;

import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.field.FieldPieVO;
import com.example.demo.vo.field.FieldPromptVO;
import com.example.demo.vo.field.FieldWcVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FieldService {

    ResponseVO<List<FieldWcVO>> getFieldWc();

    ResponseVO<List<FieldPieVO>> getFieldPie(String field);

    ResponseVO<List<FieldPromptVO>> getFieldPrompt(String field);
}
