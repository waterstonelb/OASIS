package com.example.demo.controller;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContorller {
    @Autowired
    private TestService testService;


    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public String getHello(){
        return testService.HelloTest();
    }
}
