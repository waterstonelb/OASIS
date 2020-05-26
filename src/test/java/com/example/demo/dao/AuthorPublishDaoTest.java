package com.example.demo.dao;

import com.example.demo.po.AuthorDirectInfo;
import com.example.demo.po.AuthorPublish;
import com.example.demo.vo.figure.AuthorLink;
import com.example.demo.vo.figure.AuthorNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class AuthorPublishDaoTest {

    @Autowired
    private AuthorPublishDao authorPublishDao;

    @Test
    public void findByAuthorId() {
        List<AuthorPublish> authorPublish = authorPublishDao.findByAuthorId(1);
        assertFalse(authorPublish.isEmpty());

    }

    @Test
    public void countByAuthorId() {
    }

    @Test
    public void sumCitationCountByAuthorId() {
    }

    @Test
    public void findAuthorKeyWords() {
        List<String> res = authorPublishDao.findAuthorKeyWords(1);
        assert res.size() > 0;
    }

    @Test
    public void findTopAuthor() {
    }

    @Test
    public void getAuthorDirect() {
        List<AuthorDirectInfo> res=authorPublishDao.getAuthorDirect(5);
        assertFalse(res.isEmpty());
    }

    @Test
    public void getAuthorRelations() {
        List<Integer> res=authorPublishDao.getAuthorRelations(5);
        assertFalse(res.isEmpty());
    }

    @Test
    public void getTopAuthorNodesTest() {
        List<AuthorNode> authorNodes = authorPublishDao
                .getTopAuthorNodes(PageRequest.of(0, 100)).getContent();
        assertFalse(authorNodes.isEmpty());
    }

    @Test
    public void getTopAndRelationsTest(){
        List<AuthorNode> authorNodes = authorPublishDao
                .getTopAndRelations(Arrays.asList(1, 2, 3, 4, 5));
        assertFalse(authorNodes.isEmpty());
    }
    @Test
    public void getTopAuthorLinksTest() {
        List<AuthorLink> authorLinks = authorPublishDao
                .getTopAuthorLinks(Arrays.asList(1, 2, 3, 4, 5));
        assertFalse(authorLinks.isEmpty());
    }

}