package com.example.demo.dao;

import com.example.demo.po.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {


    Author findFirstByNameAndAffiliation(String name, String aff);

    boolean existsByNameAndAffiliation(String name, String aff);

    List<Author> findByNameContaining(String author);

    @Query("select au from Author au where au.id in " +
            "(select aup.authorId from AuthorPublish aup where aup.documentId = ?1)")
    List<Author> findByDocumentId(int docId);

    @Query(value = "select * from author where id = (select author_id from author_publish group by author_id order by count(*) desc limit 1) ;",nativeQuery = true)
    Author authorRecommend();

    Author findFirstById(int auid);

    Author findById(int id);




}
