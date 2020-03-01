package com.example.demo.dao;


import com.example.demo.po.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {

    List<Document> findByAuthorsContaining(String author);

    List<Document> findByAuthorAffiliationsContaining(String institution);

    List<Document> findByPublicationTitleContaining(String conference);

    List<Document> findByAuthorKeywordsContaining(String keyword);
}
