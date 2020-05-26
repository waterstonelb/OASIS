package com.example.demo.dao;

import com.example.demo.po.TopAffliation;
import com.example.demo.vo.field.FieldPieVO;
import com.example.demo.vo.figure.AffiliationLink;
import com.example.demo.vo.figure.AffiliationNode;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AffiliationPublishDaoTest {
    @Autowired
    private AffiliationPublishDao affiliationPublishDao;

    @Test
    public void findTopAffliationTest(){
        Page<TopAffliation> topAffliations = affiliationPublishDao
                .findTopAffliation(PageRequest.of(0, 10));
        assertFalse(topAffliations.isEmpty());
    }

    @Test
    public void getTopAffiliationNodesTest(){
        List<AffiliationNode> affiliationNodes = affiliationPublishDao
                .getTopAffiliationNodes(PageRequest.of(0, 100)).getContent();
        assertFalse(affiliationNodes.isEmpty());
    }

    @Test
    public void getTopAndRelationsTest(){
        List<Integer> affIds = affiliationPublishDao
                .getTopAffiliationNodes(PageRequest.of(0, 100))
                .getContent().stream().map(AffiliationNode::getId)
                .collect(Collectors.toList());
        List<AffiliationNode> affiliationNodes = affiliationPublishDao
                .getTopAndRelations(affIds);
        assertFalse(affiliationNodes.isEmpty());
    }

    @Test
    public void getTopAffiliationLinksTest(){
        List<AffiliationNode> affiliationNodes = affiliationPublishDao
                .getTopAffiliationNodes(PageRequest.of(0, 100)).getContent();

        List<AffiliationLink> affiliationLinks = affiliationPublishDao
                .getTopAffiliationLinks(affiliationNodes
                        .stream().map(AffiliationNode::getId)
                        .collect(Collectors.toList()));

        assertFalse(affiliationLinks.isEmpty());
    }

    @Test
    public void findAffiliationKeyWords() {
        List<String> res=affiliationPublishDao.findAffiliationKeyWords(1);
        System.out.println(res.size());
        assertFalse(res.isEmpty());
    }

    @Test
    public void getAffiliationOnFieldTest(){
        List<FieldPieVO> fieldPieVOS = affiliationPublishDao
                .getAffiliationOnField("software");
        assert fieldPieVOS.size() > 0;
    }
}
