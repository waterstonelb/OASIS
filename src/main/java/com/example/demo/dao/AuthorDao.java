package com.example.demo.dao;

import com.example.demo.po.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

    boolean existsByAuthorNameAndAuthorAffiliation(String author, String affiliation);

    @Query("select a.authorId from Author a where a.authorName = ?1 and a.authorAffiliation = ?2")
    int findIdByAnameAndAaff(String aname, String aaff);
}
