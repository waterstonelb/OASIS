package com.example.demo.service;

import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.TopAuthorVO;
import com.example.demo.vo.TopCiteDocVO;
import com.example.demo.vo.TopInstitutionVO;


import java.util.List;

public interface InterestingService {

    ResponseVO<List<TopAuthorVO>> getTopAuthor();


    ResponseVO<List<TopInstitutionVO>> getTopInstitution();


    ResponseVO<List<TopCiteDocVO>> getTopCiteDoc();

}
