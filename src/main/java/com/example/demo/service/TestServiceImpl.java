package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    UserRepository userRepository;


    @Override
    public User HelloTest(String name){
        return userRepository.findByName(name);
    }
}
