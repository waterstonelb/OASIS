package com.example.demo.service.serviceinterface;

import com.example.demo.vo.search.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.search.SearchVO;

public interface ComSearchService {
    /**
     *
     * @param comSearchInpVO POST信息
     * @return ResposeVO
     */
    ResponseVO<SearchVO> comSearchDocument(ComSearchInpVO comSearchInpVO);
}
