package com.example.demo.service.serviceinterface;

import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.ranking.*;

import java.util.List;

public interface RankingService {

    ResponseVO<List<String>> getAllFields();

    ResponseVO<AffRankingResVO> getAffList(AffRankingInpVO affRankingInpVO);

    ResponseVO<AffDetailVO> getAffDetail(AffDetailInpVO affDetailInpVO);

    ResponseVO<AuthorRankingResVO> getAuthorList(AuthorRankingInpVO authorRankingInpVO);

    ResponseVO<AuthorDetailVO> getAuthorDetail(AuthorDetailInpVO authorDetailInpVO);


}
