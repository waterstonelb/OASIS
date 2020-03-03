package com.example.demo.dao;

import com.example.demo.po.Affiliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliationDao extends JpaRepository<Affiliation, Integer> {

    boolean existsByName(String name);

    Affiliation findFirstByName(String name);

    List<Affiliation> findByNameContaining(String aff);
}
