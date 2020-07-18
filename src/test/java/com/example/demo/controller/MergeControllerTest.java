package com.example.demo.controller;

import com.example.demo.service.serviceinterface.AffiliationService;
import com.example.demo.service.serviceinterface.MergeService;
import com.example.demo.vo.affiliation.AffPieChartVO;
import com.example.demo.vo.affiliation.AffTableVO;
import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.merge.MergeAffFormVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
public class MergeControllerTest {
    MockMvc mockMvc;
    MergeService mergeService;
    MergeAffFormVO mergeAffFormVO;

    @Before
    public void init(){
        mergeService = mock(MergeService.class);
        MergeController mergeController = new MergeController();
        mergeController.setMergeService(mergeService);
        mockMvc = standaloneSetup(mergeController).build();
        List<String> affs = Arrays.asList("Nanjing University", "Tsinghua University");
        ResponseVO<List<String>> res = ResponseVO.buildSuccess(affs);
        mergeAffFormVO = MergeAffFormVO.builder()
                .namesBefore(Arrays.asList("NJU", "Nanjing University"))
                .namesAfter("Nanjing University")
                .password("123").build();
        when(mergeService.getAffiliations()).thenReturn(res);
        when(mergeService.mergeAffiliations(mergeAffFormVO)).thenReturn(res);
    }

    @Test
    public void getAffsTest() throws Exception{
        mockMvc.perform(get("/merge/getAffiliations"))
                .andExpect(status().isOk());
        verify(mergeService).getAffiliations();
    }

    @Test
    public void mergeAffsTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/merge/mergeAffiliations")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(mergeAffFormVO)))
                .andExpect(status().isOk());
        verify(mergeService).mergeAffiliations(mergeAffFormVO);

    }
}
