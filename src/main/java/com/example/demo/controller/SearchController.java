package com.example.demo.controller;


import com.example.demo.service.search.SearchService;
import com.example.demo.vo.ResponseVO;
import com.mysql.cj.admin.ServerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }


    @RequestMapping(value = "/search/author", method = RequestMethod.GET)
    public ResponseVO searchByAuthor(@RequestParam("author") String author){

        return searchService.seaechByAuthor(author);
    }

    @RequestMapping(value = "/search/institution", method = RequestMethod.GET)
    public ResponseVO searchByInstitution(@RequestParam("institotion") String institution){

        return searchService.searchByInstitution(institution);
    }

    @RequestMapping(value = "/search/conference", method = RequestMethod.GET)
    public ResponseVO searchByConference(@RequestParam("conference") String conference){

        return searchService.searchByConference(conference);
    }

    @RequestMapping(value = "/search/keyword", method = RequestMethod.GET)
    public ResponseVO searchByStudyKeyword(@RequestParam("keyword") String keyword){

        return searchService.searchByStudyKeyword(keyword);
    }
}
