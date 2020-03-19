package com.example.demo.service.serviceinterface;

import com.example.demo.vo.AuthorVO;
import com.example.demo.vo.ResponseVO;

/**
 * @author 梁斌
 */
public interface AuthorService {

    /**
     *查询作者详细信息
     *
     * @param authorId 作者ID
     * @return AuthorVO
     */
    ResponseVO<AuthorVO> getAuthorInfo(int authorId);
}
