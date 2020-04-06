package com.example.demo.controller;

import com.example.demo.service.serviceinterface.AffiliationService;
import com.example.demo.vo.affiliation.AffPieChartVO;
import com.example.demo.vo.affiliation.AffTableVO;
import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AffiliationControllerTest {
    MockMvc mockMvc;
    AffiliationService affiliationService;

    @Before
    public void init() {
        affiliationService= mock(AffiliationService.class);
        AffiliationController affiliationController=new AffiliationController(affiliationService);
        mockMvc = standaloneSetup(affiliationController).build();
        ResponseVO<AffiliationVO> res=ResponseVO.buildSuccess("Test Success",
                AffiliationVO.builder().Hindex(10).build());
        ResponseVO<List<AffPieChartVO>> chartRes=ResponseVO.buildSuccess("Test Success",
                new ArrayList<>());
        ResponseVO<AffTableVO> tableRes=ResponseVO.buildSuccess(AffTableVO.builder().build());
        when(affiliationService.getAffiliationInfo(0)).thenReturn(res);
        when(affiliationService.getAffPieChart(0)).thenReturn(chartRes);
        when(affiliationService.getHindexTable(0)).thenReturn(tableRes);
        when(affiliationService.getPaperTable(0)).thenReturn(tableRes);
        when(affiliationService.getCitationTable(0)).thenReturn(tableRes);
    }

    @Test
    public void getAuthorInfo() throws Exception {
        mockMvc.perform(get("/affiliation/info")
                .param("affId", "0"))
                .andExpect(status().isOk());
        verify(affiliationService).getAffiliationInfo(0);
    }

    @Test
    public void getAffiliationPaperTable() throws Exception {
        mockMvc.perform(get("/affiliation/table/paper")
                .param("affId", "0"))
                .andExpect(status().isOk());
        verify(affiliationService).getPaperTable(0);
    }

    @Test
    public void getAffiliationCitationTable() throws Exception {
        mockMvc.perform(get("/affiliation/table/citation")
                .param("affId", "0"))
                .andExpect(status().isOk());
        verify(affiliationService).getCitationTable(0);
    }

    @Test
    public void getAffiliationHindexTable() throws Exception {
        mockMvc.perform(get("/affiliation/table/hindex")
                .param("affId", "0"))
                .andExpect(status().isOk());
        verify(affiliationService).getHindexTable(0);
    }

    @Test
    public void getAffiliationPieChart() throws Exception {
        mockMvc.perform(get("/affiliation/piechart")
                .param("affId", "0"))
                .andExpect(status().isOk());
        verify(affiliationService).getAffPieChart(0);
    }
}