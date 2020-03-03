package com.example.demo.dao;

import com.example.demo.po.Author;
import com.example.demo.po.AuthorPublishPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorPublishDao extends JpaRepository<Author, AuthorPublishPK> {


}
