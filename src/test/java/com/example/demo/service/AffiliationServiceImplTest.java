package com.example.demo.service;

import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AffiliationServiceImplTest {
    @Autowired
    AffiliationServiceImpl affiliationService;

    @Test
    public void getAffiliationInfo(){
        ResponseVO<AffiliationVO> res= affiliationService.getAffiliationInfo(1273);
        assert res.getData().getHindex()>0;
    }
    @Test
    public void getAffiliationInfoFail() {
        ResponseVO<AffiliationVO> res= affiliationService.getAffiliationInfo(999999);
        assertEquals("机构详情获取失败",res.getMessage());
    }
}