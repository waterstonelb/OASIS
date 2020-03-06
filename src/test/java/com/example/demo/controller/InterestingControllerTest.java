package com.example.demo.controller;

import com.example.demo.po.Author;
import com.example.demo.service.InterestingService;
import com.example.demo.vo.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class InterestingControllerTest {

    private InterestingService interestingService;
    private InterestingController interestingController;
    MockMvc mockMvc;

    @Before
    public void initialize(){
        interestingService = mock(InterestingService.class);
        interestingController = new InterestingController(interestingService);
        mockMvc = standaloneSetup(interestingController).build();
    }
    @Test
    public void getTopAuthor() throws Exception{
        ResponseVO<List<TopAuthorVO>> res = ResponseVO.buildSuccess(new ArrayList<>());
        when(interestingService.getTopAuthor()).thenReturn(res);
        String mockres = mockMvc.perform(
                get("/interesting/top10/author"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(interestingService).getTopAuthor();
    }

    @Test
    public void getTopInstitution() throws Exception{
        ResponseVO<List<TopInstitutionVO>> res = ResponseVO.buildSuccess(new ArrayList<>());
        when(interestingService.getTopInstitution()).thenReturn(res);
        String mockres = mockMvc.perform(
                get("/interesting/top10/institution"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(interestingService).getTopInstitution();
    }

    @Test
    public void getTopCiteDoc() throws Exception{
        ResponseVO<List<TopCiteDocVO>> res = ResponseVO.buildSuccess(new ArrayList<>());
        when(interestingService.getTopCiteDoc()).thenReturn(res);
        String mockres = mockMvc.perform(
                get("/interesting/top10/paper"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(interestingService).getTopCiteDoc();
    }
    @Test
    public void auRecTest() throws Exception {
        ResponseVO<AuthorRecommend> res=ResponseVO.buildSuccess(AuthorRecommend.builder().build());
        when(interestingService.authorRecommand()).thenReturn(res);
        mockMvc.perform(get("/interesting/top10/authorrecommend"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(interestingService).authorRecommand();
    }

}