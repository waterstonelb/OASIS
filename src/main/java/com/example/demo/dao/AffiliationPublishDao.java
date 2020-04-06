package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import com.example.demo.po.AffiliationPublish;
import com.example.demo.po.AffiliationPublishPK;
import com.example.demo.po.TopAffliation;
import com.example.demo.vo.field.FieldPieVO;
import com.example.demo.vo.figure.AffiliationLink;
import com.example.demo.vo.figure.AffiliationNode;
import com.example.demo.vo.figure.FieldFigureVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliationPublishDao extends
        JpaRepository<AffiliationPublish, AffiliationPublishPK> {

    @Query("select new com.example.demo.po.TopAffliation(afp.affId, count (afp.documentId))" +
            " from AffiliationPublish afp " +
            "group by afp.affId order by count (afp.documentId) desc")
    Page<TopAffliation> findTopAffliation(Pageable pageable);

    /**
     * 获取所有机构节点信息
     * @return List<AffiliationNode>
     */
    @Query("select new com.example.demo.vo.figure.AffiliationNode(a.name, count(ap.affId), a.id) from " +
            "AffiliationPublish ap, Affiliation a where ap.affId = a.id " +
            "group by a.id")
    List<AffiliationNode> getAllAffiliationNodes();

    /**
     * 获取所有机构关系信息
     * @return List<AffiliationLink>
     */
    @Query("select new com.example.demo.vo.figure.AffiliationLink(ap1.affId, ap2.affId, count(ap1.affId)) " +
            "from AffiliationPublish ap1, AffiliationPublish ap2 where ap1.documentId = ap2.documentId " +
            "and ap1.affId < ap2.affId group by ap1.affId, ap2.affId")
    List<AffiliationLink> getAllAffiliationLinks();

    /**
     * 研究方向相关机构信息
     * @return List<FieldPieVO>
     */
    @Query("select new com.example.demo.vo.field.FieldPieVO(af.name,count(af.id), af.id) from Affiliation af, AffiliationPublish afp " +
            "where af.id = afp.affId and exists (select d.id from Document d " +
            "where afp.documentId = d.id and d.keywords like concat('%', ?1, '%')) group by af.id")
    List<FieldPieVO> getAffiliationOnField(String field);

}
