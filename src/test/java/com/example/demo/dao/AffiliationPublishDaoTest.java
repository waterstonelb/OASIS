package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import com.example.demo.po.TopAffliation;
import com.example.demo.vo.figure.AffiliationLink;
import com.example.demo.vo.figure.AffiliationNode;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AffiliationPublishDaoTest {
    @Autowired
    private AffiliationPublishDao affiliationPublishDao;

    @Test
    public void findTopAffliationTest(){
        Page<TopAffliation> topAffliations = affiliationPublishDao
                .findTopAffliation(PageRequest.of(0, 10));
        assert topAffliations.getContent().size() > 0;
    }

    @Test
    public void getAllAffiliationNodesTest(){
        List<AffiliationNode> affiliationNodes = affiliationPublishDao
                .getAllAffiliationNodes();
        assert affiliationNodes.size() > 0;
    }

    @Test
    public void getAllAffiliationLinksTest(){
        List<AffiliationLink> affiliationLinks = affiliationPublishDao
                .getAllAffiliationLinks();
        assert affiliationLinks.size() > 0;
    }
}
