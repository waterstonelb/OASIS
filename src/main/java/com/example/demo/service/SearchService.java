package com.example.demo.search;

import com.example.demo.po.Document;
import com.example.demo.vo.ResponseVO;

import java.util.List;

public interface SearchService {

    ResponseVO<List<Document>> seaechByAuthor(String author);

    ResponseVO<List<Document>>  searchByInstitution(String institution);

    ResponseVO<List<Document>>  searchByConference(String conference);

    ResponseVO<List<Document>>  searchByStudyKeyword(String keyword);
}
