package com.example.demo.dao;

import com.example.demo.po.AuthorDirectInfo;
import com.example.demo.po.AuthorPublish;
import com.example.demo.po.AuthorPublishPK;
import com.example.demo.po.TopAuthor;
import com.example.demo.vo.figure.AuthorLink;
import com.example.demo.vo.figure.AuthorNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    /**
     * 查询作者的所有关键词
     * @param id authorId
     * @return {@link List<String>}
     */
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

    /**
     * 获取作者一级关系
     * @param authorId 作者ID
     * @return {@link List<AuthorDirectInfo>}
     */
    @Query("select new com.example.demo.po.AuthorDirectInfo(ap.authorId,a.name,count(ap.documentId)) " +
            "from AuthorPublish ap join Author a on a.id=ap.authorId where ap.documentId in " +
            "(select ap1.documentId from AuthorPublish ap1 where ap1.authorId=?1) " +
            "group by ap.authorId order by count (ap.documentId) ")
    List<AuthorDirectInfo> getAuthorDirect(int authorId);

    /**
     * 获取相关联作者之间关系
     * @param authorId 相关作者ID
     * @return Integer
     */
    @Query("select ap.authorId from AuthorPublish ap where ap.documentId in " +
            "(select ap1.documentId from AuthorPublish ap1 where ap1.authorId=?1)")
    List<Integer> getAuthorRelations(int authorId);

    /**
     * 获取TOP作者节点信息
     * @return Page<AuthorNode>
     */
    @Query("select new com.example.demo.vo.figure.AuthorNode(a.name, count(ap.authorId), a.id) from " +
            "AuthorPublish ap, Author a where ap.authorId = a.id " +
            "group by a.id order by count(ap.authorId) desc")
    Page<AuthorNode> getTopAuthorNodes(Pageable pageable);

    /**
     * 获取TOP作者以及和TOP作者关联的作者信息
     * @return List<AuthorNode>
     */
    @Query("select new com.example.demo.vo.figure.AuthorNode(a.name, count(ap.authorId), a.id) from " +
            "AuthorPublish ap, Author a where ap.authorId = a.id and a.id in ?1 " +
            "group by a.id")
    List<AuthorNode> getTopAndRelations(List<Integer> authorIds);

    /**
     * 获取TOP作者关系信息
     * @return List<AuthorLink>
     */
    @Query("select new com.example.demo.vo.figure.AuthorLink(ap1.authorId, ap2.authorId, count(ap1.authorId)) " +
            "from AuthorPublish ap1, AuthorPublish ap2 where ap1.documentId = ap2.documentId " +
            "and ap1.authorId < ap2.authorId and (ap1.authorId in ?1 or ap2.authorId in ?1)" +
            "group by ap1.authorId, ap2.authorId")
    List<AuthorLink> getTopAuthorLinks(List<Integer> topIds);

}
