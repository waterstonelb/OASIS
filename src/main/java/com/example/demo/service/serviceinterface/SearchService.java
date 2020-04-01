package com.example.demo.service.serviceinterface;

import com.example.demo.vo.*;
import com.example.demo.vo.search.*;

public interface SearchService {

    ResponseVO<SearchVO> searchByAuthor(SearchByAuthorInpVO searchByAuthorInpVO);

    ResponseVO<SearchVO>  searchByInstitution(SearchByInstitutionInpVO searchByInstitutionInpVO);

    ResponseVO<SearchVO>  searchByConference(SearchByConferenceInpVO searchByConferenceInpVO);

    ResponseVO<SearchVO>  searchByStudyKeyword(SearchByKeywordInpVO searchByKeywordInpVO);

    /**
     * 用于机构详情页面查询
     * @param searchByAffiliationIdVO 输入
     * @return SearchVO
     */
    ResponseVO<SearchVO> searchByAffiliation(SearchByAffiliationIdVO searchByAffiliationIdVO);
}
