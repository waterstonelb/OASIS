package com.example.demo.service;

import com.example.demo.service.serviceinterface.MergeService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.merge.MergeAffFormVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MergeServiceImplTest {

    @Autowired
    MergeService mergeService;

    @Test
    public void getAffiliationsTest(){
        ResponseVO<List<String>> res = mergeService.getAffiliations();
        assert res.getData().size() > 0;
    }

    @Test
    public void mergeAffiliationsTest(){
        MergeAffFormVO mergeAffFormVO = MergeAffFormVO.builder()
                .namesBefore(Arrays.asList("NJU", "Nanjing University"))
                .namesAfter("Nanjing University")
                .password("123").build();
        ResponseVO<List<String>> res = mergeService.mergeAffiliations(mergeAffFormVO);
        assert res.getData().size() > 0;
    }
}
