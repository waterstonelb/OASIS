package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AffiliationDaoTest {

    @Autowired
    private AffiliationDao affiliationDao;

    @Test
    public void existsByName() {
        boolean exists = affiliationDao.existsByName("Nanjing University");
        assertTrue(exists);
    }

    @Test
    public void findFirstByName() {
        Affiliation res = affiliationDao.findFirstByName("Nanjing University");
        assertEquals("Nanjing University", res.getName());
    }


    @Test
    public void getPaperCount() {
        int res=affiliationDao.getPaperCount(710);
        assert res>0;
    }

    @Test
    public void getCitationCount() {
        int res=affiliationDao.getCitationCount(4);
        assert res>0;
    }

    @Test
    public void getAuthorCount() {
        int res=affiliationDao.getAuthorCount(4);
        assert res>0;
    }

    @Test
    public void getHindexList() {
        List<Integer> res=affiliationDao.getHindexList(4);
        res.sort(Integer::compareTo);
        for (int i:res
             ) {
            System.out.println(i);
        }
        assert res.size()>0;
    }
}