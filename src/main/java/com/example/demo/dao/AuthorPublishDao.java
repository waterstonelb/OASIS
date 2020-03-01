package com.example.demo.dao;

import com.example.demo.po.AuthorPublishEntity;
import com.example.demo.po.AuthorPublishEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorPublishDao extends JpaRepository<AuthorPublishEntity, AuthorPublishEntityPK> {


}
