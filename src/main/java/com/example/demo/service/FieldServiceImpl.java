package com.example.demo.service;

import com.example.demo.service.serviceinterface.FieldService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.field.FieldPieVO;
import com.example.demo.vo.field.FieldPromptVO;
import com.example.demo.vo.field.FieldWcVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Override
    public ResponseVO<List<FieldWcVO>> getFieldWc() {
        return null;
    }

    @Override
    public ResponseVO<List<FieldPieVO>> getFieldPie(String field) {
        return null;
    }

    @Override
    public ResponseVO<List<FieldPromptVO>> getFieldPrompt(String field) {
        return null;
    }
}
