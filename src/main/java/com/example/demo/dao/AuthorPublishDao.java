package com.example.demo.dao;

import com.example.demo.po.Author;
import com.example.demo.po.AuthorPublish;
import com.example.demo.po.AuthorPublishPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorPublishDao extends JpaRepository<AuthorPublish, AuthorPublishPK> {

    List<AuthorPublish> findByAuthorId(int aid);


}
