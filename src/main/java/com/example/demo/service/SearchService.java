package com.example.demo.service;

import com.example.demo.po.Document;
import com.example.demo.vo.*;

import java.util.List;

public interface SearchService {

    ResponseVO<List<Document>> seaechByAuthor(SearchByAuthorInpVO searchByAuthorInpVO);

    ResponseVO<List<Document>>  searchByInstitution(SearchByInstitutionInpVO searchByInstitutionInpVO);

    ResponseVO<List<Document>>  searchByConference(SearchByConferenceInpVO searchByConferenceInpVO);

    ResponseVO<List<Document>>  searchByStudyKeyword(SearchByKeywordInpVO searchByKeywordInpVO);
}
