package com.example.demo.service;

import com.example.demo.vo.paper.PaperInfoVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaperInfoServiceImplTest {

    @Autowired
    PaperInfoServiceImpl paperInfoService;

    @Test
    public void getPaperInfoSuccess() {
        ResponseVO<PaperInfoVO> res=paperInfoService.getPaperInfo(2);
        assertFalse(res.getData().getAuthors().isEmpty());
    }
    @Test
    public void getPaperInfoFail(){
        ResponseVO<PaperInfoVO> res=paperInfoService.getPaperInfo(1000000);
        assertEquals("查询失败",res.getMessage());
    }

}