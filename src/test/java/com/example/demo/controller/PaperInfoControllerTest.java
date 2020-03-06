package com.example.demo.controller;

import com.example.demo.service.PaperInfoService;

import com.example.demo.vo.PaperInfoVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class PaperInfoControllerTest {
    PaperInfoService paperInfoService;
    PaperInfoController paperInfoController;
    MockMvc mockMvc;

    @Before
    public void init() {
        paperInfoService = mock(PaperInfoService.class);
        paperInfoController = new PaperInfoController(paperInfoService);
        mockMvc = standaloneSetup(paperInfoController).build();
        ResponseVO<PaperInfoVO> res=ResponseVO.buildSuccess("Test Success",new PaperInfoVO());
        when(paperInfoService.getPaperInfo(2)).thenReturn(res);
    }

    @Test
    public void getPaperInfo() throws Exception {
        String mockres=mockMvc.perform(get("/paper/info")
                .param("documentId", "2"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(paperInfoService).getPaperInfo(2);
    }
}