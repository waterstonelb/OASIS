package com.example.demo.controller;


import com.example.demo.po.Document;
import com.example.demo.service.SearchService;
import com.example.demo.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }


    @ApiOperation(value = "根据作者查询")
    @RequestMapping(value = "/search/author", method = RequestMethod.GET)
    public ResponseVO<List<Document>>  searchByAuthor(@RequestParam("author") String author){

        return searchService.seaechByAuthor(author);
    }

    @ApiOperation(value = "根据机构查询")
    @RequestMapping(value = "/search/institution", method = RequestMethod.GET)
    public ResponseVO<List<Document>>  searchByInstitution(@RequestParam("institotion") String institution){

        return searchService.searchByInstitution(institution);
    }

    @ApiOperation(value = "根据会议查询")
    @RequestMapping(value = "/search/conference", method = RequestMethod.GET)
    public ResponseVO<List<Document>>  searchByConference(@RequestParam("conference") String conference){

        return searchService.searchByConference(conference);
    }

    @ApiOperation(value = "根据论文关键字查询")
    @RequestMapping(value = "/search/keyword", method = RequestMethod.GET)
    public ResponseVO<List<Document>>  searchByStudyKeyword(@RequestParam("keyword") String keyword){

        return searchService.searchByStudyKeyword(keyword);
    }
}
