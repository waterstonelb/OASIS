package com.example.demo.dao;

import com.example.demo.po.Author;
import com.example.demo.vo.ranking.AffRankingVO;
import com.example.demo.vo.ranking.AuthorRankingVO;
import com.example.demo.vo.ranking.PaperItem;
import net.sf.ehcache.search.parser.MValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {


    boolean existsByIeeeId(String ieeeId);

    Author findFirstByIeeeId(String ieeeId);


    List<Author> findByNameContaining(String author);

    @Query("select au from Author au where au.id in " +
            "(select aup.authorId from AuthorPublish aup where aup.documentId = ?1)")
    List<Author> findByDocumentId(int docId);

    @Query(value = "select * from author where id = (select author_id from author_publish group by author_id order by count(*) desc limit 1) ;",nativeQuery = true)
    @Cacheable("author")
    Author authorRecommend();

    Author findFirstById(int auid);

    Author findById(int id);

    @Query("select d.citationCount from AuthorPublish a join Document d on d.id=a.documentId where a.authorId=?1")
    List<Integer> getHindexList(int authorId);



    /**
     * 将合并前的机构名称更新为合并后的
     */
    @Modifying
    @Query("update Author set affiliation = ?2 where affiliation in ?1")
    void updateToMerge(List<String> before, String after);

    @Query("select count(ap.documentId) from AuthorPublish ap join Document d on ap.documentId = d.id " +
            "where ap.authorId = ?1 and d.publicationYear = ?2")
    long getPaperCountByYear(int auId, int year);

    @Query("select count(ap.documentId) from AuthorPublish ap join Document d on ap.documentId = d.id " +
            "where ap.authorId = ?1 and d.id in ?2")
    long getPaperCountByField(int auId, List<Integer> docIds);

    @Query("select new com.example.demo.vo.ranking.PaperItem(d.id, d.citationCount, d.title) " +
            "from Author a, AuthorPublish ap, Document d " +
            "where a.id = ?1 and a.id = ap.authorId and ap.documentId = d.id " +
            "order by d.citationCount desc")
    Page<PaperItem> getTopPapers(int auId, Pageable pageable);


    @Query("select new com.example.demo.vo.ranking.AuthorRankingVO" +
            "(a.id, a.name, count(ap.documentId), sum(d.citationCount), 0, " +
            "(sum(d.citationCount)/count(ap.documentId))) " +
            "from Author a, AuthorPublish ap, Document d " +
            "where a.id = ap.authorId and ap.documentId in ?1 and ap.documentId = d.id " +
            "group by a.id order by " +
            "count(ap.documentId) desc")
    Page<AuthorRankingVO> getAuTopSortByCount(List<Integer> docIds, Pageable pageable);

    @Query("select new com.example.demo.vo.ranking.AuthorRankingVO" +
            "(a.id, a.name, count(ap.documentId), sum(d.citationCount), 0, " +
            "(sum(d.citationCount)/count(ap.documentId))) " +
            "from Author a, AuthorPublish ap, Document d " +
            "where a.id = ap.authorId and ap.documentId in ?1 and ap.documentId = d.id " +
            "group by a.id order by " +
            "sum(d.citationCount) desc")
    Page<AuthorRankingVO> getAuTopSortByCitation(List<Integer> docIds, Pageable pageable);

    @Query("select new com.example.demo.vo.ranking.AuthorRankingVO" +
            "(a.id, a.name, count(ap.documentId), sum(d.citationCount), 0, " +
            "(sum(d.citationCount)/count(ap.documentId))) " +
            "from Author a, AuthorPublish ap, Document d " +
            "where a.id = ap.authorId and ap.documentId in ?1 and ap.documentId = d.id " +
            "group by a.id order by " +
            "(sum(d.citationCount)/count(ap.documentId)) desc")
    Page<AuthorRankingVO> getAuTopSortByShkbScore(List<Integer> docIds, Pageable pageable);

    @Query("select d.citationCount from AuthorPublish a join Document d on " +
            "a.authorId=?2 and d.id=a.documentId and d.id in ?1")
    List<Integer> getHindexListFilterByField(List<Integer> docIds, int auId);
}
