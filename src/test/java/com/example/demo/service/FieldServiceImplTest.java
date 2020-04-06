package com.example.demo.service;

import com.example.demo.service.serviceinterface.FieldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FieldServiceImplTest {
    @Autowired
    FieldService fieldService;

    @Test
    public void getFieldWc() {
        assert fieldService.getFieldWc().getData().size() > 0;
    }

    @Test
    public void getFieldPie() {
        assert fieldService.getFieldPie("software").getData().size() > 0;
    }

    @Test
    public void getFieldPrompt() {
        assert fieldService.getFieldPrompt("so").getData().size() > 0;
    }
}