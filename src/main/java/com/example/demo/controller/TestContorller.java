package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.HelloService;
import com.example.demo.service.TestService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "User获取测试接口",notes = "first blood", response = User.class)
    @RequestMapping(path = "/user",method = RequestMethod.GET)
    public User getUser(){
        return testService.HelloTest("mzl");
    }

    @ApiOperation(value = "Hello测试",notes = "second blood")
    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public String getHello(){
        return helloService.HelloTest();
    }
}
