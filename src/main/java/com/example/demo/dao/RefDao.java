package com.example.demo.dao;

import com.example.demo.po.Ref;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefDao extends JpaRepository<Ref, Integer> {
}
