package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import com.example.demo.po.AffiliationPublish;
import com.example.demo.po.AffiliationPublishPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliationPublishDao extends
        JpaRepository<AffiliationPublish, AffiliationPublishPK> {

}
