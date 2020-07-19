package com.example.demo.service.serviceinterface;

import com.example.demo.vo.ResponseVO;

import java.util.List;

public interface RankingService {

    ResponseVO<List<String>> getAllFields();

    ResponseVO<List<String>> getConferencesByFields(List<String> fields);
}
