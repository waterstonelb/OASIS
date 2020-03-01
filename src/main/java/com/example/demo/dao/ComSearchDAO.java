package com.example.demo.dao;

import com.example.demo.po.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComSearchDAO extends JpaRepository<Document,Long> {

    Document findById(String Id);
}
