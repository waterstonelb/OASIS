package com.example.demo.controller;

import com.example.demo.service.serviceinterface.ComSearchService;
import com.example.demo.vo.search.ComSearchInpVO;
import com.example.demo.vo.paper.DocumentVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.search.SearchVO;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class ComSearchControllerTest {

    @Test
    public void getComDocument() throws Exception {
        ComSearchService comSearchService = mock(ComSearchService.class);
        ComSearchController comSearchController = new ComSearchController();
        comSearchController.setComSearchService(comSearchService);
        MockMvc mockMvc = standaloneSetup(comSearchController).build();
        ComSearchInpVO comSearchInpVO = ComSearchInpVO.builder()
                .authors("Penix")
                .institution("Google")
                .conference("ACM")
                .keywords("")
                .size(2)
                .page(0).build();
        List<DocumentVO> list = new ArrayList<>();
        list.add(DocumentVO.builder().build());
        when(comSearchService.comSearchDocument(comSearchInpVO)).thenReturn(ResponseVO.buildSuccess(new SearchVO(10, list)));

        Map<String, String> map = new HashMap<>();
        map.put("authors", "Penix");
        map.put("institution", "Google");
        map.put("conference", "ACM");
        map.put("keywords", "");
        map.put("size", "2");
        map.put("page", "0");
        String content = JSONObject.toJSONString(map);

        mockMvc.perform(post("/comsearch/document")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(comSearchService).comSearchDocument(comSearchInpVO);
    }
}