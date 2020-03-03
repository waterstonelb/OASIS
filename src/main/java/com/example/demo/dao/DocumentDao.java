package com.example.demo.dao;


import com.example.demo.po.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {

    Document findFirstById(int id);

    List<Document> findByKeywordsContaining(String keyword);

    List<Document> findByPublicationContaining(String conference);

}
