package com.example.demo.service.serviceinterface;

import com.example.demo.vo.*;
import com.example.demo.vo.author.AuthorRecommend;
import com.example.demo.vo.top.TopAuthorVO;
import com.example.demo.vo.top.TopCiteDocVO;
import com.example.demo.vo.top.TopInstitutionVO;


import java.util.List;

public interface InterestingService {

    ResponseVO<List<TopAuthorVO>> getTopAuthor();


    ResponseVO<List<TopInstitutionVO>> getTopInstitution();


    ResponseVO<List<TopCiteDocVO>> getTopCiteDoc();

    ResponseVO<AuthorRecommend> authorRecommand();

}
