package com.example.demo.dao;

import com.example.demo.po.Author;
import net.sf.ehcache.search.parser.MValue;
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
}
