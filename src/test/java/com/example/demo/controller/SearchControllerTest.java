package com.example.demo.controller;


import com.example.demo.service.serviceinterface.SearchService;
import com.example.demo.vo.*;
import com.example.demo.vo.paper.DocumentVO;
import com.example.demo.vo.search.*;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
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


public class SearchControllerTest {

    private SearchService searchService;

    private MockMvc mockMvc;

    @Before
    public void initialize() {
        searchService = mock(SearchService.class);
        SearchController searchController = new SearchController(searchService);
        mockMvc = standaloneSetup(searchController).build();
    }

    @Test
    public void searchByAuTest() throws Exception{
        SearchByAuthorInpVO searchByAuthorInpVO =
                SearchByAuthorInpVO.builder()
                .author("Penix")
                .size(2)
                .page(0).build();
        List<DocumentVO> list=new ArrayList<>();
        list.add(DocumentVO.builder().build());
        when(searchService.searchByAuthor(searchByAuthorInpVO))
                .thenReturn(ResponseVO.buildSuccess(new SearchVO(10,list)));

        Map<String,String> map=new HashMap<>();
        map.put("author","Penix");
        map.put("size","2");
        map.put("page","0");
        String content= JSONObject.toJSONString(map);

        mockMvc.perform(post("/search/document/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(searchService).searchByAuthor(searchByAuthorInpVO);
    }

    @Test
    public void searchByInsTest() throws Exception{
        SearchByInstitutionInpVO searchByInstitutionInpVO =
                SearchByInstitutionInpVO.builder()
                .institution("Google")
                .size(2)
                .page(0).build();

        List<DocumentVO> list=new ArrayList<>();
        list.add(DocumentVO.builder().build());
        when(searchService.searchByInstitution(searchByInstitutionInpVO))
                .thenReturn(ResponseVO.buildSuccess(new SearchVO(10,list)));

        Map<String,String> map=new HashMap<>();
        map.put("institution","Google");
        map.put("size","2");
        map.put("page","0");
        String content= JSONObject.toJSONString(map);

        mockMvc.perform(post("/search/document/institution")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(searchService).searchByInstitution(searchByInstitutionInpVO);
    }

    @Test
    public void searchByConferTest() throws Exception{
        SearchByConferenceInpVO searchByConferenceInpVO =
                SearchByConferenceInpVO.builder()
                .conference("ACM")
                .size(2)
                .page(0).build();

        List<DocumentVO> list=new ArrayList<>();
        list.add(DocumentVO.builder().build());
        when(searchService.searchByConference(searchByConferenceInpVO))
                .thenReturn(ResponseVO.buildSuccess(new SearchVO(10,list)));

        Map<String,String> map=new HashMap<>();
        map.put("conference","ACM");
        map.put("size","2");
        map.put("page","0");
        String content= JSONObject.toJSONString(map);

        mockMvc.perform(post("/search/document/conference")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(searchService).searchByConference(searchByConferenceInpVO);
    }

    @Test
    public void searchBykwTest() throws Exception{
        SearchByKeywordInpVO searchByKeywordInpVO =
                SearchByKeywordInpVO.builder()
                        .keyword("Solid modeling")
                        .size(2)
                        .page(0).build();
        List<DocumentVO> list=new ArrayList<>();
        list.add(DocumentVO.builder().build());
        when(searchService.searchByStudyKeyword(searchByKeywordInpVO))
                .thenReturn(ResponseVO.buildSuccess(new SearchVO(10,list)));

        Map<String,String> map=new HashMap<>();
        map.put("keyword","Solid modeling");
        map.put("size","2");
        map.put("page","0");
        String content= JSONObject.toJSONString(map);

        mockMvc.perform(post("/search/document/keyword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(searchService).searchByStudyKeyword(searchByKeywordInpVO);
    }

    @Test
    public void searchByAffiliationId() throws Exception {
        SearchByAffiliationIdVO s= SearchByAffiliationIdVO.builder()
                .affId(0)
                .page(0)
                .size(2)
                .build();
        List<DocumentVO> list=new ArrayList<>();
        list.add(DocumentVO.builder().build());
        when(searchService.searchByAffiliation(s))
                .thenReturn(ResponseVO.buildSuccess(new SearchVO(10,list)));

        Map<String,String> map=new HashMap<>();
        map.put("affId","0");
        map.put("size","2");
        map.put("page","0");
        String content= JSONObject.toJSONString(map);
        mockMvc.perform(post("/search/document/affiliation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(searchService).searchByAffiliation(s);
    }

    @Test
    public void searchByAuthorId() throws Exception {
        SearchByAuthorIdVO s= SearchByAuthorIdVO.builder()
                .authorId(0)
                .page(0)
                .size(2)
                .build();
        List<DocumentVO> list=new ArrayList<>();
        list.add(DocumentVO.builder().build());
        when(searchService.searchByAuthorId(s))
                .thenReturn(ResponseVO.buildSuccess(new SearchVO(10,list)));
        Map<String,String> map=new HashMap<>();
        map.put("authorId","0");
        map.put("size","2");
        map.put("page","0");
        String content= JSONObject.toJSONString(map);
        mockMvc.perform(post("/search/document/authorid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(searchService).searchByAuthorId(s);
    }
}
