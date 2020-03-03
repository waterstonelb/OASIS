package com.example.demo.dao;

import com.example.demo.po.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

    boolean existsByName(String name);


}
