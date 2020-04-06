package com.example.demo.service.serviceinterface;

import com.example.demo.vo.affiliation.AffPieChartVO;
import com.example.demo.vo.affiliation.AffTableVO;
import com.example.demo.vo.affiliation.AffiliationVO;
import com.example.demo.vo.ResponseVO;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.List;
import java.util.Map;

public interface AffiliationService {

    /**
     * 获取机构详细信息
     * @param affId 机构ID
     * @return {@link AffiliationVO}
     */
    ResponseVO<AffiliationVO> getAffiliationInfo(int affId);

    /**
     * 获取机构饼状图
     * @param affId 机构Id
     * @return {@link List<AffPieChartVO>}
     */
    ResponseVO<List<AffPieChartVO>> getAffPieChart(int affId);

    /**
     * 获取机构H-index统计图
     * @param affId 机构Id
     * @return {@link AffTableVO}
     */
    ResponseVO<AffTableVO> getHindexTable(int affId);

    /**
     * 获取机构paper数量统计图
     * @param affId 机构Id
     * @return {@link AffTableVO}
     */
    ResponseVO<AffTableVO> getPaperTable(int affId);

    /**
     * 获取机构citation统计图
     * @param affId 机构Id
     * @return {@link AffTableVO}
     */
    ResponseVO<AffTableVO> getCitationTable(int affId);
}
