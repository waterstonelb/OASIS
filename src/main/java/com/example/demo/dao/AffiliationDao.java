package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import com.example.demo.po.AffiliationTable;
import com.example.demo.po.HindexEntry;
import com.example.demo.vo.ranking.AffRankingVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("select count(ap.documentId) from AffiliationPublish ap join Document d on ap.documentId = d.id " +
            "where ap.affId = ?1 and d.publicationYear = ?2")
    long getPaperCountByYear(int affId, int year);

    @Query("select count(ap.documentId) from AffiliationPublish ap join Document d on ap.documentId = d.id " +
            "where ap.affId = ?1 and d.id in ?2")
    long getPaperCountByField(int affId, List<Integer> docIds);

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

    @Query("select new com.example.demo.vo.ranking.AffRankingVO" +
            "(a.id, a.name, count(ap.documentId), sum(d.citationCount), 0, " +
            "(sum(d.citationCount)/count(ap.documentId))) " +
            "from Affiliation a, AffiliationPublish ap, Document d " +
            "where a.id = ap.affId and ap.documentId in ?1 and ap.documentId = d.id " +
            "group by a.id order by " +
            "count(ap.documentId) desc")
    Page<AffRankingVO> getAffTopSortByCount(List<Integer> docIds, Pageable pageable);

    @Query("select new com.example.demo.vo.ranking.AffRankingVO" +
            "(a.id, a.name, count(ap.documentId), sum(d.citationCount), 0, " +
            "(sum(d.citationCount)/count(ap.documentId))) " +
            "from Affiliation a, AffiliationPublish ap, Document d " +
            "where a.id = ap.affId and ap.documentId in ?1 and ap.documentId = d.id " +
            "group by a.id order by " +
            "sum(d.citationCount) desc")
    Page<AffRankingVO> getAffTopSortByCitation(List<Integer> docIds, Pageable pageable);

    @Query("select new com.example.demo.vo.ranking.AffRankingVO" +
            "(a.id, a.name, count(ap.documentId), sum(d.citationCount), 0, " +
            "(sum(d.citationCount)/count(ap.documentId))) " +
            "from Affiliation a, AffiliationPublish ap, Document d " +
            "where a.id = ap.affId and ap.documentId in ?1 and ap.documentId = d.id " +
            "group by a.id order by " +
            "(sum(d.citationCount)/count(ap.documentId)) desc")
    Page<AffRankingVO> getAffTopSortByShkbScore(List<Integer> docIds, Pageable pageable);

    @Query("select d.citationCount from AffiliationPublish a join Document d on " +
            "a.affId=?2 and d.id=a.documentId and d.id in ?1")
    List<Integer> getHindexListFilterByField(List<Integer> docIds, int affId);



}
