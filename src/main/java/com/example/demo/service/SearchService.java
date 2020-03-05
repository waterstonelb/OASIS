package com.example.demo.service;

import com.example.demo.po.Document;
import com.example.demo.vo.*;

import java.util.List;

public interface SearchService {

    ResponseVO<SearchVO> seaechByAuthor(SearchByAuthorInpVO searchByAuthorInpVO);

    ResponseVO<SearchVO>  searchByInstitution(SearchByInstitutionInpVO searchByInstitutionInpVO);

    ResponseVO<SearchVO>  searchByConference(SearchByConferenceInpVO searchByConferenceInpVO);

    ResponseVO<SearchVO>  searchByStudyKeyword(SearchByKeywordInpVO searchByKeywordInpVO);
}
