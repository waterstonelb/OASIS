package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import com.example.demo.po.AffiliationTable;
import com.example.demo.po.HindexEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AffiliationDaoTest {

    @Autowired
    private AffiliationDao affiliationDao;

    @Test
    public void existsByName() {
        boolean exists = affiliationDao.existsByName("Acreo Swedish ICT, Stockholm, Sweden");
        assertTrue(exists);
    }

    @Test
    public void findFirstByName() {
        Affiliation res = affiliationDao.findFirstByName("Acreo Swedish ICT, Stockholm, Sweden");
        assertEquals("Acreo Swedish ICT, Stockholm, Sweden", res.getName());
    }


    @Test
    public void getPaperCount() {
        int res=affiliationDao.getPaperCount(1);
        assertTrue(res>0);
    }

    @Test
    public void getCitationCount() {
        int res=affiliationDao.getCitationCount(4);
        assertTrue(res>0);
    }

    @Test
    public void getAuthorCount() {
        int res=affiliationDao.getAuthorCount(4);
        assertTrue(res>0);
    }

    @Test
    public void getHindexList() {
        List<Integer> res=affiliationDao.getHindexList(4);
        res.sort(Integer::compareTo);
        for (int i:res
             ) {
            System.out.println(i);
        }
        assertFalse(res.isEmpty());
    }

    @Test
    public void getHindexListByYear() {
        List<HindexEntry> res=affiliationDao.getHindexListWithYear(4);
        assertFalse(res.isEmpty());
    }

    @Test
    public void getCitationByYear() {
        List<AffiliationTable> res=affiliationDao.getCitationWithYear(1);
        assertFalse(res.isEmpty());
    }

    @Test
    public void getPaperCountByYear() {
        List<AffiliationTable> res=affiliationDao.getPaperCountWithYear(1);

        assertFalse(res.isEmpty());
    }
}