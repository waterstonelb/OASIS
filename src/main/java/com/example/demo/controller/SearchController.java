package com.example.demo.controller;


import com.example.demo.service.serviceinterface.SearchService;
import com.example.demo.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/search/document")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }


    @ApiOperation(value = "根据作者查询")
    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public ResponseVO<SearchVO>
    searchByAuthor(@RequestBody SearchByAuthorInpVO searchByAuthorInpVO){

        return searchService.searchByAuthor(searchByAuthorInpVO);
    }

    @ApiOperation(value = "根据机构查询")
    @RequestMapping(value = "/institution", method = RequestMethod.POST)
    public ResponseVO<SearchVO>
    searchByInstitution(@RequestBody SearchByInstitutionInpVO searchByInstitutionInpVO){

        return searchService.searchByInstitution(searchByInstitutionInpVO);
    }

    @ApiOperation(value = "根据会议查询")
    @RequestMapping(value = "/conference", method = RequestMethod.POST)
    public ResponseVO<SearchVO>
    searchByConference(@RequestBody SearchByConferenceInpVO searchByConferenceInpVO){

        return searchService.searchByConference(searchByConferenceInpVO);
    }

    @ApiOperation(value = "根据论文关键字查询")
    @RequestMapping(value = "/keyword", method = RequestMethod.POST)
    public ResponseVO<SearchVO>
    searchByStudyKeyword(@RequestBody SearchByKeywordInpVO searchByKeywordInpVO){

        return searchService.searchByStudyKeyword(searchByKeywordInpVO);
    }

    @ApiOperation("查询具体机构的论文")
    @RequestMapping(value = "/affiliation",method = RequestMethod.POST)
    public ResponseVO<SearchVO> searchByAffiliationId(@RequestBody SearchByAffiliationIdVO searchByAffiliationIdVO){
        return searchService.searchByAffiliation(searchByAffiliationIdVO);
    }
}
