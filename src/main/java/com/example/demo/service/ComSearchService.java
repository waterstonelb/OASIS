package com.example.demo.service;

import com.example.demo.po.Document;
import com.example.demo.vo.ComSearchInpVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.SearchVO;

import java.util.List;

public interface ComSearchService {
    /**
     *
     * @param comSearchInpVO POST信息
     * @return ResposeVO
     */
    ResponseVO<SearchVO> comSearchDocument(ComSearchInpVO comSearchInpVO);
}
