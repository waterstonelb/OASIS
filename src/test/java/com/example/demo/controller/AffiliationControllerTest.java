package com.example.demo.controller;

import com.example.demo.service.serviceinterface.AffiliationService;
import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.web.servlet.MockMvc;

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
        when(affiliationService.getAffiliationInfo(0)).thenReturn(res);
    }

    @Test
    public void getAuthorInfo() throws Exception {
        String mockres=mockMvc.perform(get("/affiliation/info")
                .param("affId", "0"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(affiliationService).getAffiliationInfo(0);
    }
}