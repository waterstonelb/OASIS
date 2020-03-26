package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import com.example.demo.vo.AffiliationVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliationDao extends JpaRepository<Affiliation, Integer> {

    boolean existsByName(String name);

    Affiliation findFirstByName(String name);

    Affiliation findFirstById(int afid);

    @Query("select count(a.documentId) as paperCount from AffiliationPublish a where a.affId=?1")
    int getPaperCount(int affId);

    @Query("select sum(d.citationCount) from AffiliationPublish a join Document d on d.id=a.documentId where a.affId=?1")
    int getCitationCount(int affId);
    @Query("select count(a) from Author a where a.affiliation in (select af.name from Affiliation af where af.id=?1)")
    int getAuthorCount(int affId);

    @Query("select d.citationCount from AffiliationPublish a join Document d on d.id=a.documentId where a.affId=?1")
    List<Integer> getHindexList(int affId);

}
