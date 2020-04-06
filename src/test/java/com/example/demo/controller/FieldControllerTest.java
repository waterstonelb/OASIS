package com.example.demo.controller;

import com.example.demo.service.serviceinterface.FieldService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.field.FieldPieVO;
import com.example.demo.vo.field.FieldPromptVO;
import com.example.demo.vo.field.FieldWcVO;
import net.sf.ehcache.hibernate.EhCache;
import net.sf.ehcache.transaction.xa.EhcacheXAException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class FieldControllerTest {
    MockMvc mockMvc;
    FieldService fieldService;

    @Before
    public void init(){
        fieldService = mock(FieldService.class);
        FieldController fieldController = new FieldController(fieldService);
        mockMvc = standaloneSetup(fieldController).build();
        ResponseVO<List<FieldWcVO>> resWc = ResponseVO.buildSuccess(new ArrayList<>());
        ResponseVO<List<FieldPieVO>> resPie = ResponseVO.buildSuccess(new ArrayList<>());
        ResponseVO<List<FieldPromptVO>> resPmt = ResponseVO.buildSuccess(new ArrayList<>());
        when(fieldService.getFieldWc()).thenReturn(resWc);
        when(fieldService.getFieldPie("software")).thenReturn(resPie);
        when(fieldService.getFieldPrompt("so")).thenReturn(resPmt);
    }

    @Test
    public void getFieldWc() throws Exception{
        mockMvc.perform(get("/fields/wordcloud"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void getFieldPie() throws Exception{
        mockMvc.perform(get("/fields/pie")
                .param("field", "software"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void getFieldPrompt() throws Exception {
        mockMvc.perform(get("/fields/search")
                .param("field", "so"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }
}