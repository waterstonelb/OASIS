package com.example.demo.service;

import com.example.demo.dao.AffiliationPublishDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.service.serviceinterface.FieldService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.field.FieldPieVO;
import com.example.demo.vo.field.FieldPromptVO;
import com.example.demo.vo.field.FieldWcVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FieldServiceImpl implements FieldService {

    private final int PIE_TOP = 40;

    private DocumentDao documentDao;

    @Autowired
    public void setDocumentDao(DocumentDao documentDao){
        this.documentDao = documentDao;
    }

    private AffiliationPublishDao affiliationPublishDao;
    @Autowired
    public void setAffiliationPublishDao(AffiliationPublishDao affiliationPublishDao){
        this.affiliationPublishDao = affiliationPublishDao;
    }

    @Override
    public ResponseVO<List<FieldWcVO>> getFieldWc() {
        try {

            List<String> kwlist = documentDao.findAllKeywords().stream()
                    .filter(s -> !s.isEmpty()).map(s -> s.split(",")).
                    flatMap(Arrays::stream).collect(Collectors.toList());
            Map<String, Long> kwTimes = new HashMap<>();
            for (String kw : kwlist){
                if (!kwTimes.containsKey(kw))
                    kwTimes.put(kw, 1L);
                else
                    kwTimes.put(kw, kwTimes.get(kw) + 1);
            }

            List<FieldWcVO> fieldWcVOS = new ArrayList<>();
            for(Map.Entry<String, Long> entry : kwTimes.entrySet())
                fieldWcVOS.add(FieldWcVO.builder().name(entry.getKey())
                        .value(entry.getValue()).build());


            log.info("领域云图建立成功");
            return ResponseVO.buildSuccess(fieldWcVOS);
        }catch (Exception ex){
            log.error("领域云图建立失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("领域云图建立失败");
        }
    }

    @Override
    public ResponseVO<List<FieldPieVO>> getFieldPie(String field) {
        try {
            List<FieldPieVO> fieldPieVOS = affiliationPublishDao.getAffiliationOnField(field);
            fieldPieVOS.sort((o1, o2) -> (int)(o2.getValue() - o1.getValue()));
            int top = Math.min(fieldPieVOS.size(), PIE_TOP);
            List<FieldPieVO> res = fieldPieVOS.subList(0, top);
            FieldPieVO others = FieldPieVO.builder().id(-1).name("others").value(0).build();
            for(FieldPieVO fieldPieVO : fieldPieVOS.subList(top, fieldPieVOS.size()))
                others.setValue(others.getValue() + fieldPieVO.getValue());
            res.add(others);
            log.info("领域饼图建立成功");
            return ResponseVO.buildSuccess(res);
        }catch (Exception ex){
            log.error("领域饼图建立失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("领域饼图建立失败");
        }
    }

    @Override
    public ResponseVO<List<FieldPromptVO>> getFieldPrompt(String field) {
        try {
            List<String> kwlist = documentDao.findAllKeywords().stream()
                    .filter(s -> !s.isEmpty()).map(s -> s.split(","))
                    .flatMap(Arrays::stream).distinct().collect(Collectors.toList());

            Map<String, Integer> kwIdMap = new HashMap<>();
            for (int i = 0; i < kwlist.size(); i++)
                kwIdMap.put(kwlist.get(i), i);

            List<FieldPromptVO> fieldPromptVOS = new ArrayList<>();
            for(String kw : kwlist)
                if (kw.toLowerCase().contains(field.toLowerCase()))
                    fieldPromptVOS.add(FieldPromptVO.builder()
                            .id(kwIdMap.get(kw)).name(kw).build());

                log.info("获取实时提醒成功");
                return ResponseVO.buildSuccess(fieldPromptVOS);

        }catch (Exception ex){
            log.error("获取实时提醒失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("获取实时提醒失败");
        }
    }
}
