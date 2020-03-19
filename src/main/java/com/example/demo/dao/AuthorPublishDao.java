package com.example.demo.dao;

import com.example.demo.po.AuthorPublish;
import com.example.demo.po.AuthorPublishPK;
import com.example.demo.po.TopAuthor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorPublishDao extends JpaRepository<AuthorPublish, AuthorPublishPK> {

    /**
     *
     * @param aid AuthorId
     * @return findByAuthorId
     */
    List<AuthorPublish> findByAuthorId(int aid);

    /**
     * 返回author的文章数量
     * @param id AuthorId
     * @return int count
     */
    int countByAuthorId(int id);

    /**
     * 返回作者所有文章的citation和
     * @param id AuthorId
     * @return sumOfcitation
     */
    @Query("select sum(d.citationCount) from AuthorPublish a inner join Document d " +
            "on a.documentId=d.id where a.authorId=?1")
    int sumCitationCountByAuthorId(int id);

    @Query("select d.keywords from AuthorPublish a inner join Document d " +
            "on a.documentId=d.id where a.authorId=?1")
    List<String> findAuthorKeyWords(int id);

    /**
     * 返回热门作者
     * @param pageable 分页
     * @return TopAuthor
     */
    @Query("select new com.example.demo.po.TopAuthor(aup.authorId, count (aup.documentId))" +
            " from AuthorPublish aup group by aup.authorId order by count (aup.documentId) desc")
    Page<TopAuthor> findTopAuthor(Pageable pageable);

}
