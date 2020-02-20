package com.example.demo;


import com.example.demo.bean.User;
import com.example.demo.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATests {
    @Autowired
    UserRepository userRepository;
    @Test
    public void Test1(){
        User user = User.builder().name("mzl").build();
        System.out.println(user.getName());
        userRepository.save(user);
        User nuser=userRepository.findByName("mzl");
        assertEquals("mzl",nuser.getName());
    }
}
