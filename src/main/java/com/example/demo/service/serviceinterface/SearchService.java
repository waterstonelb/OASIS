package com.example.demo.service.serviceinterface;

import com.example.demo.vo.*;
import com.example.demo.vo.search.*;

public interface SearchService {

    ResponseVO<SearchVO> searchByAuthor(SearchByAuthorInpVO searchByAuthorInpVO);

    ResponseVO<SearchVO>  searchByInstitution(SearchByInstitutionInpVO searchByInstitutionInpVO);

    ResponseVO<SearchVO>  searchByConference(SearchByConferenceInpVO searchByConferenceInpVO);

    ResponseVO<SearchVO>  searchByStudyKeyword(SearchByKeywordInpVO searchByKeywordInpVO);

    ResponseVO<SearchVO> searchByTitle(SearchByTitleVO searchByTitleVO);

    /**
     * 用于机构详情页面查询
     * @param searchByAffiliationIdVO 输入
     * @return {@link SearchVO}
     */
    ResponseVO<SearchVO> searchByAffiliation(SearchByAffiliationIdVO searchByAffiliationIdVO);

    /**
     * 用于作业页面详情查询
     * @param searchByAuthorIdVO 输入
     * @return {@link SearchVO}
     */
    ResponseVO<SearchVO> searchByAuthorId(SearchByAuthorIdVO searchByAuthorIdVO);
}
