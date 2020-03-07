package com.example.demo.service.serviceinterface;

import com.example.demo.vo.*;


import java.util.List;

public interface InterestingService {

    ResponseVO<List<TopAuthorVO>> getTopAuthor();


    ResponseVO<List<TopInstitutionVO>> getTopInstitution();


    ResponseVO<List<TopCiteDocVO>> getTopCiteDoc();

    ResponseVO<AuthorRecommend> authorRecommand();

}
