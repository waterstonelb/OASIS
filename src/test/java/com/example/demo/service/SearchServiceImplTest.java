package com.example.demo.service;

import com.example.demo.service.serviceinterface.SearchService;
import com.example.demo.vo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceImplTest {

    @Autowired
    SearchServiceImpl searchService;

    @Test
    public void searchByAuTest(){
        ResponseVO<SearchVO> res = searchService.searchByAuthor(
                SearchByAuthorInpVO.builder()
                        .author("Ali")
                        .size(2)
                        .page(0).build()
        );
        assert res.getData().getDocuments().size() > 0;
    }

    @Test
    public void searchByInsTest(){
        ResponseVO<SearchVO> res = searchService.searchByInstitution(
                SearchByInstitutionInpVO.builder()
                        .institution("Dallas")
                        .size(2)
                        .page(0).build()
        );
        assert res.getData().getDocuments().size() > 0;
    }

    @Test
    public void searchByConTest(){
        ResponseVO<SearchVO> res = searchService.searchByConference(
                SearchByConferenceInpVO.builder()
                        .conference("34th")
                        .size(2)
                        .page(0).build()
        );

        assert res.getData().getDocuments().size() > 0;
    }

    @Test
    public void searchByKwTest(){

        ResponseVO<SearchVO> res = searchService.searchByStudyKeyword(
                SearchByKeywordInpVO.builder()
                        .keyword("Solid modeling")
                        .size(2)
                        .page(0).build()
        );
        assert res.getData().getDocuments().size() > 0;
    }
}