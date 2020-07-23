package com.example.demo.service.serviceinterface;

import com.example.demo.vo.author.AuthorMapVO;
import com.example.demo.vo.author.AuthorPrd;
import com.example.demo.vo.author.AuthorStudyHis;
import com.example.demo.vo.author.AuthorVO;
import com.example.demo.vo.ResponseVO;
import java.util.List;

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

    /**
     * 获取作者研究方向历史
     * @param authorId 作者ID
     * @return {@link AuthorStudyHis}
     */
    ResponseVO<List<AuthorStudyHis>> getAuthorStudyHistory(int authorId);

    /**
     * 获取作者合作关系预测
     * @param authorId 作者id
     * @return {@link AuthorPrd}
     */
    ResponseVO<List<AuthorPrd>> getAuthorPrediction(int authorId);

}
