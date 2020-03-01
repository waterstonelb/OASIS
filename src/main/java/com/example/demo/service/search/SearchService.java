package com.example.demo.service.search;

import com.example.demo.po.Document;
import com.example.demo.vo.ResponseVO;

import java.util.List;

public interface SearchService {

    ResponseVO seaechByAuthor(String author);

    ResponseVO searchByInstitution(String institution);

    ResponseVO searchByConference(String conference);

    ResponseVO searchByStudyKeyword(String keyword);
}
