package com.example.demo.service;

import com.example.demo.dao.FieldDao;
import com.example.demo.po.Field;
import com.example.demo.service.serviceinterface.RankingService;
import com.example.demo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RankingServiceImpl implements RankingService {


    private FieldDao fieldDao;
    @Autowired
    public void setFieldDao(FieldDao fieldDao){
        this.fieldDao = fieldDao;
    }
    @Override
    public ResponseVO<List<String>> getAllFields() {
        try {
            log.info("获取领域成功");
            List<String> res =  fieldDao.getAllFields();
            return ResponseVO.buildSuccess(res);
        }catch (Exception ex){
            log.error("获取领域失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("获取领域失败");
        }

    }

    @Override
    public ResponseVO<List<String>> getConferencesByFields(List<String> fields) {
        try {
            log.info("获取会议成功");
            List<String> res =  fieldDao.getConferencesByFields(fields);
            return ResponseVO.buildSuccess(res);
        }catch (Exception ex){
            log.error("获取会议失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("获取会议失败");
        }
    }
}
