package com.example.demo.controller;


import com.example.demo.po.Document;
import com.example.demo.service.SearchService;
import com.example.demo.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }


    @ApiOperation(value = "根据作者查询")
    @RequestMapping(value = "/docsearch/author", method = RequestMethod.POST)
    public ResponseVO<List<Document>>
    searchByAuthor(@RequestBody SearchByAuthorInpVO searchByAuthorInpVO){

        return searchService.seaechByAuthor(searchByAuthorInpVO);
    }

    @ApiOperation(value = "根据机构查询")
    @RequestMapping(value = "/docsearch/institution", method = RequestMethod.POST)
    public ResponseVO<List<Document>>
    searchByInstitution(@RequestBody SearchByInstitutionInpVO searchByInstitutionInpVO){

        return searchService.searchByInstitution(searchByInstitutionInpVO);
    }

    @ApiOperation(value = "根据会议查询")
    @RequestMapping(value = "/docsearch/conference", method = RequestMethod.POST)
    public ResponseVO<List<Document>>
    searchByConference(@RequestBody SearchByConferenceInpVO searchByConferenceInpVO){

        return searchService.searchByConference(searchByConferenceInpVO);
    }

    @ApiOperation(value = "根据论文关键字查询")
    @RequestMapping(value = "/docsearch/keyword", method = RequestMethod.POST)
    public ResponseVO<List<Document>>
    searchByStudyKeyword(@RequestBody SearchByKeywordInpVO searchByKeywordInpVO){

        return searchService.searchByStudyKeyword(searchByKeywordInpVO);
    }
}
