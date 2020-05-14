package com.example.demo.service;

import com.example.demo.vo.*;
import com.example.demo.vo.search.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceImplTest {

    @Autowired
    SearchServiceImpl searchService;

    @Test
    public void searchByAuTest(){
        ResponseVO<SearchVO> res = searchService.searchByAuthor(
                SearchByAuthorInpVO.builder()
                        .author("Sinisa Krajnovic")
                        .size(2)
                        .page(0).build()
        );
        assertFalse(res.getData().getDocuments().isEmpty());
    }

    @Test
    public void searchByInsTest(){
        ResponseVO<SearchVO> res = searchService.searchByInstitution(
                SearchByInstitutionInpVO.builder()
                        .institution("Sweden")
                        .size(2)
                        .page(0).build()
        );
        assertFalse(res.getData().getDocuments().isEmpty());
    }

    @Test
    public void searchByConTest(){
        ResponseVO<SearchVO> res = searchService.searchByConference(
                SearchByConferenceInpVO.builder()
                        .conference("14th")
                        .size(2)
                        .page(0).build()
        );

        assertFalse(res.getData().getDocuments().isEmpty());
    }

    @Test
    public void searchByKwTest(){

        ResponseVO<SearchVO> res = searchService.searchByStudyKeyword(
                SearchByKeywordInpVO.builder()
                        .keyword("Virtualization")
                        .size(2)
                        .page(0).build()
        );
        assertFalse(res.getData().getDocuments().isEmpty());
    }

    @Test
    public void searchByAffiliation() {
        ResponseVO<SearchVO> res = searchService.searchByAffiliation(
                SearchByAffiliationIdVO
                        .builder()
                        .affId(4)
                        .size(2)
                        .page(0)
                        .build());
        assertFalse(res.getData().getDocuments().isEmpty());
    }

    @Test
    public void searchByAuthorId() {
        ResponseVO<SearchVO> res=searchService.searchByAuthorId(
                SearchByAuthorIdVO.builder()
                        .authorId(5)
                        .size(2)
                        .page(0)
                        .build()
        );
        assertFalse(res.getData().getDocuments().isEmpty());
    }
}
