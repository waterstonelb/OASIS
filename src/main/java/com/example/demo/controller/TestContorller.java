package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.HelloService;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContorller {
    @Autowired
    private TestService testService;
    @Autowired
    private HelloService helloService;

    @RequestMapping(path = "/user",method = RequestMethod.GET)
    public User getUser(){
        return testService.HelloTest("mzl");
    }
    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public String getHello(){
        return helloService.HelloTest();
    }
}
