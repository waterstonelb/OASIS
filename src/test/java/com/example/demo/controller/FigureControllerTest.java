package com.example.demo.controller;


import com.example.demo.service.serviceinterface.FigureService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.figure.AuthorFigureVO;
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
}
