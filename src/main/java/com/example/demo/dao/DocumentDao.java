package com.example.demo.dao;


import com.example.demo.po.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {

//    List<Document> findByAuthorsContaining(String author);
//
//    List<Document> findByAuthorAffiliationsContaining(String institution);
//
//    List<Document> findByPublicationTitleContaining(String conference);
//
//    List<Document> findByAuthorKeywordsContaining(String keyword);
//
//    @Query(value = "select * from document where if(?1 !='',authors like concat('%',?1,'%'),1=1)" +
//            " and if(?2 !='',author_affiliations like concat('%',?2,'%'),1=1)" +
//            " and if(?3 !='',publication_title like concat('%',?3,'%'),1=1)" +
//            " and if(?4 !='',author_keywords like concat('%',?4,'%'),1=1)",nativeQuery = true)
//    List<Document> find(String author,String institution, String conference, String keyword);
    //select title from document where id in
    // (select document_id from author_publish where author_id in
    // (select id from author where name like '%Ali%')) and id in
    // (select document_id from affiliation_publish where aff_id in
    // (select id from affiliation where name like '%Dallas%')) and publication like '%34th%';
    @Query(value = "select * from document where " +
            "if(?1 != '', id in (select document_id from author_publish where author_id in " +
            "(select id from author where name like concat('%',?1,'%'))),1=1) " +
            "and if(?2 != '',id in (select document_id from affiliation_publish where aff_id in " +
            "(select id from affiliation where name like concat('%',?2,'%'))),1=1) " +
            "and if(?3 != '',publication like concat('%',?3,'%'),1=1) " +
            "and if(?4 != '',keywords like concat('%',?4,'%'),1=1);" ,nativeQuery = true)
    List<Document> find(String author,String affiliation,String publication,String keywords);

}
