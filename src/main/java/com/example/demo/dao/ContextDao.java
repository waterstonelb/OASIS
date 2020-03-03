package com.example.demo.dao;

import com.example.demo.po.Context;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextDao extends JpaRepository<Context, Integer> {
}
