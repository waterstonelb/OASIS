package com.example.demo.service.serviceinterface;

import com.example.demo.vo.author.AuthorMapVO;
import com.example.demo.vo.author.AuthorVO;
import com.example.demo.vo.ResponseVO;

/**
 * @author 梁斌
 */
public interface AuthorService {

    /**
     * 查询作者详细信息
     * @param authorId 作者ID
     * @return {@link AuthorVO}
     */
    ResponseVO<AuthorVO> getAuthorInfo(int authorId);

    /**
     * 获取作者关系图
     * @param authorId 作者ID
     * @return {@link AuthorMapVO}
     */
    ResponseVO<AuthorMapVO> getAuthorMap(int authorId);

}
