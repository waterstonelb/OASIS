package com.example.demo;

import com.example.demo.controller.TestContorller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTests {
    @Autowired
    TestContorller testContorller;
    @Test
    public void controllerHelloTest(){
        assertEquals("Hello",testContorller.getHello());
    }
}
