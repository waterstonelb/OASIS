package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliationDao extends JpaRepository<Affiliation, Integer> {

    boolean existsByName(String name);
}
