package com.example.demo.dao;

import com.example.demo.po.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorPublishDao extends JpaRepository<Author, Long> {


}
