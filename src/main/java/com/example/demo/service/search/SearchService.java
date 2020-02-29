package com.example.demo.service.search;

import com.example.demo.vo.ResponseVO;

public interface SearchService {

    public ResponseVO seaechByAuthor(String author);

    public ResponseVO searchByInstitution(String institution);

    public ResponseVO searchByConference(String conference);

    public ResponseVO searchByStudyKeyword(String keyword);
}
