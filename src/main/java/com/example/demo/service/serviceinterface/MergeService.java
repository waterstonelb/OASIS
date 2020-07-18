package com.example.demo.service.serviceinterface;

import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.merge.MergeAffFormVO;

import java.util.List;

public interface MergeService {

    ResponseVO<List<String>> getAffiliations();

    ResponseVO<List<String>> mergeAffiliations(MergeAffFormVO mergeAffFormVO);
}
