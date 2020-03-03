package com.example.demo.controller;

import com.example.demo.po.Document;
import com.example.demo.service.ComSearchService;
import com.example.demo.vo.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


import static org.junit.jupiter.api.Assertions.*;

class ComSearchControllerTest {

    @Test
    void getComDocument() throws Exception {
        ComSearchService comSearchService=mock(ComSearchService.class);
        ComSearchController comSearchController=new ComSearchController();
        comSearchController.setComSearchService(comSearchService);
        MockMvc mockMvc=standaloneSetup(comSearchController).build();
        ComSearchInpVO comSearchInpVO=ComSearchInpVO.builder()
                .authors("Pnix")
                .institution("Google")
                .conference("ACM")
                .keyword("").build();
        List<Document> list=new ArrayList<>();
        list.add(new Document());
        when(comSearchService.comSearchCocument(comSearchInpVO)).thenReturn(ResponseVO.buildSuccess(list));

        Map<String,String> map=new HashMap<>();
        map.put("authors","Pnix");
        map.put("institution","Google");
        map.put("conference","ACM");
        map.put("keyword","");
        String content= JSONObject.toJSONString(map);

        String res=mockMvc.perform(post("/comsearch/document")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(comSearchService).comSearchCocument(comSearchInpVO);
    }
}