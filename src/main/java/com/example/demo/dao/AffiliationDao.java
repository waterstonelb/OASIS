package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import com.example.demo.po.AffiliationTable;
import com.example.demo.po.HindexEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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

    @Query("select new com.example.demo.po.HindexEntry(d.publicationYear,d.citationCount) from AffiliationPublish a join Document d on d.id=a.documentId " +
            "where a.affId=?1 and d.publicationYear>2013")
    List<HindexEntry> getHindexListWithYear(int affId);

    @Query("select new com.example.demo.po.AffiliationTable(d.publicationYear,sum(d.citationCount)) from AffiliationPublish a join Document d on a.documentId=d.id " +
            "where a.affId=?1 and d.publicationYear>2013 group by d.publicationYear")
    List<AffiliationTable> getCitationWithYear(int affId);

    @Query("select new com.example.demo.po.AffiliationTable(d.publicationYear,count (a.documentId)) from AffiliationPublish a join Document d on a.documentId=d.id " +
            "where a.affId=?1 and d.publicationYear>2013 group by d.publicationYear")
    List<AffiliationTable> getPaperCountWithYear(int affId);

    @Query("select a.name from Affiliation a")
    List<String> getAffiliationNames();

    @Query("select a.id from Affiliation a where a.name in ?1")
    List<Integer> getIdsByNames(List<String> names);

    @Modifying
    void deleteByIdIsIn(List<Integer> idList);
}
