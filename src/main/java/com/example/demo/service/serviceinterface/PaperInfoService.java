package com.example.demo.service.serviceinterface;


import com.example.demo.vo.PaperInfoVO;
import com.example.demo.vo.ResponseVO;

public interface PaperInfoService {

    /**
     * @author 梁斌
     * @param documentId 论文ID
     * @return PaperInfoVO
     */
    ResponseVO<PaperInfoVO> getPaperInfo(int documentId);

}
