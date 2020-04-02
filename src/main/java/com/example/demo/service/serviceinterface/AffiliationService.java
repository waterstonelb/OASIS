package com.example.demo.service.serviceinterface;

import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import org.springframework.scheduling.annotation.AsyncResult;

public interface AffiliationService {

    /**
     *
     * @param affId 机构ID
     * @return AffiliationVO
     */
    ResponseVO<AffiliationVO> getAffiliationInfo(int affId);
}
