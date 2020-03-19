package com.example.demo.controller;

import com.example.demo.service.serviceinterface.AuthorService;
import com.example.demo.service.serviceinterface.PaperInfoService;
import com.example.demo.vo.AuthorVO;
import com.example.demo.vo.PaperInfoVO;
import com.example.demo.vo.ResponseVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AuthorControllerTest {
    MockMvc mockMvc;
    AuthorService authorService;

    @Before
    public void init() {
        authorService = mock(AuthorService.class);
        AuthorController authorController = new AuthorController();
        authorController.setAuthorService(authorService);
        mockMvc = standaloneSetup(authorController).build();
        ResponseVO<AuthorVO> res=ResponseVO.buildSuccess("Test Success", AuthorVO.builder()
                .name("野人")
                .build());
        when(authorService.getAuthorInfo(0)).thenReturn(res);
    }

    @Test
    public void getAuthorInfo() throws Exception {
        String mockres=mockMvc.perform(get("/author/info")
                .param("authorId", "0"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(authorService).getAuthorInfo(0);
    }
}