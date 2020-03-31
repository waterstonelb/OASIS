package com.example.demo.controller;


import com.example.demo.service.serviceinterface.FigureService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.figure.AffiliationFigureVO;
import com.example.demo.vo.figure.AuthorFigureVO;
import com.example.demo.vo.figure.FieldFigureVO;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class FigureControllerTest {


    @Test
    public void getAuthorFigureTest() throws Exception{
        FigureService figureService = mock(FigureService.class);
        FigureController figureController = new FigureController(figureService);
        MockMvc mockMvc = standaloneSetup(figureController).build();
        AuthorFigureVO authorFigureVO = AuthorFigureVO.builder()
                .links(new ArrayList<>()).nodes(new ArrayList<>()).build();
        when(figureService.getAuthorFigure()).thenReturn(ResponseVO.buildSuccess(authorFigureVO));

        mockMvc.perform(
                get("/figure/authors"))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

        verify(figureService).getAuthorFigure();

    }

    @Test
    public void getAffiliationFigureTest() throws Exception{
        FigureService figureService = mock(FigureService.class);
        FigureController figureController = new FigureController(figureService);
        MockMvc mockMvc = standaloneSetup(figureController).build();
        AffiliationFigureVO affiliationFigureVO = AffiliationFigureVO.builder()
                .links(new ArrayList<>()).nodes(new ArrayList<>()).build();
        when(figureService.getAffiliationFigure()).thenReturn(ResponseVO.buildSuccess(affiliationFigureVO));

        mockMvc.perform(
                get("/figure/affiliations"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(figureService).getAffiliationFigure();
    }

    @Test
    public void getFieldFigureTest() throws Exception{
        FigureService figureService = mock(FigureService.class);
        FigureController figureController = new FigureController(figureService);
        MockMvc mockMvc = standaloneSetup(figureController).build();
        FieldFigureVO fieldFigureVO = FieldFigureVO.builder()
                .links(new ArrayList<>()).nodes(new ArrayList<>()).build();
        when(figureService.getFieldFigure()).thenReturn(ResponseVO.buildSuccess(fieldFigureVO));
        mockMvc.perform(
                get("/figure/fields"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(figureService).getFieldFigure();
    }
}
