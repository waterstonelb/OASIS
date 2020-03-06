package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import com.example.demo.po.AffiliationPublish;
import com.example.demo.po.AffiliationPublishPK;
import com.example.demo.po.TopAffliation;
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
            " from AffiliationPublish afp group by afp.affId order by count (afp.documentId) desc")
    Page<TopAffliation> findTopAffliation(Pageable pageable);

}
