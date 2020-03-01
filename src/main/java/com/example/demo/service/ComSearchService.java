package com.example.demo.service;

import com.example.demo.po.Document;
import com.example.demo.vo.ResponseVO;

import java.util.List;

public interface ComSearchService {
    /**
     * @param author      作者
     * @param institution 机构
     * @param conference  会议
     * @param keyword     研究关键字
     * @return ResponseVO
     */
    ResponseVO<List<Document>> comSearchCocument(String author, String institution, String conference, String keyword);
}
