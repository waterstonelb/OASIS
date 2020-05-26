package com.example.demo.service;

import com.example.demo.vo.affiliation.AffPieChartVO;
import com.example.demo.vo.affiliation.AffTableVO;
import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AffiliationServiceImplTest {
    @Autowired
    AffiliationServiceImpl affiliationService;

    @Test
    public void getAffiliationInfo(){
        ResponseVO<AffiliationVO> res= affiliationService.getAffiliationInfo(3);
        assertTrue(res.getData().getHindex()>0);
    }
    @Test
    public void getAffiliationInfoFail() {
        ResponseVO<AffiliationVO> res= affiliationService.getAffiliationInfo(999999);
        assertEquals("机构详情获取失败",res.getMessage());
    }

    @Test
    public void getAffPieChart() {
        ResponseVO<List<AffPieChartVO>> res=affiliationService.getAffPieChart(3);
        for(AffPieChartVO a:res.getData())
            System.out.println(a);
        assertFalse(res.getData().isEmpty());
    }

    @Test
    public void getHindexTable() {
        ResponseVO<AffTableVO> res=affiliationService.getHindexTable(3);
        int len=res.getData().getData().size();
        for (int i = 0; i < len; i++) {
            System.out.println(res.getData().getXdata().get(i)+" "+
                    res.getData().getData().get(i));
        }
        assertFalse(res.getData().getData().isEmpty());
    }

    @Test
    public void getPaperTable() {
        ResponseVO<AffTableVO> res=affiliationService.getPaperTable(3);
        int len=res.getData().getData().size();
        for (int i = 0; i < len; i++) {
            System.out.println(res.getData().getXdata().get(i)+" "+
                    res.getData().getData().get(i));
        }
        assertFalse(res.getData().getData().isEmpty());
    }

    @Test
    public void getCitationTable() {
        ResponseVO<AffTableVO> res=affiliationService.getCitationTable(3);
        int len=res.getData().getData().size();
        for (int i = 0; i < len; i++) {
            System.out.println(res.getData().getXdata().get(i)+" "+
                    res.getData().getData().get(i));
        }
        assertFalse(res.getData().getData().isEmpty());
    }
}