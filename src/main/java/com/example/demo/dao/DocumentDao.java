package com.example.demo.dao;


import com.example.demo.po.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {


    Document findFirstById(int id);

    @Query(value = "select d from Document d where d.id in " +
            "(select aup.documentId from AuthorPublish aup where aup.authorId in " +
            "(select au.id from Author au where au.name like concat('%',?1,'%') ))")
    Page<Document> findByAuthor(String author, Pageable pageable);

    @Query(value = "select d from Document d where d.id in " +
            "(select afp.documentId from AffiliationPublish afp where afp.affId in " +
            "(select af.id from Affiliation af where af.name like concat('%',?1,'%') ))")
    Page<Document> findByInstitution(String institution, Pageable pageable);

    Page<Document> findByPublicationContaining(String publication, Pageable pageable);

    Page<Document> findByKeywordsContaining(String keyword, Pageable pageable);

    @Query(value = "select d from Document d left join where (?1 is null or d.id in " +
            "(select aup.documentId from AuthorPublish aup where aup.authorId in " +
            "(select au.id from Author au where au.name like %?1%))) " +
            "and (?2 is null or d.id in (select afp.documentId from AffiliationPublish afp where afp.affId in " +
            "(select af.id from Affiliation af where af.name like %?2))) " +
            "and (?3 is null or d.publication like %?3%) " +
            "and (?4 is null or d.keywords like %?4%) ")
    Page<Document> comFind(String author,String affiliation, String publication, String keywords,Pageable pageable);

//    @Query(value = "select * from document d where " +
//            "if(?1 != '', d.id in (select ap.document_id from author_publish ap where ap.author_id in " +
//            "(select a.id from author a where a.name like concat('%',?1,'%'))),1=1) " +
//            "and if(?2 != '',d.id in (select af.document_id from affiliation_publish af where af.aff_id in " +
//            "(select aa.id from affiliation aa where aa.name like concat('%',?2,'%'))),1=1) " +
//            "and if(?3 != '',d.publication like concat('%',?3,'%'),1=1) " +
//            "and if(?4 != '',d.keywords like concat('%',?4,'%'),1=1); " ,nativeQuery = true)
//    Page<Document> find(String author, String affiliation, String publication, String keywords,Pageable pageable);

//    @Query(value = "select * from document d where d.id=?1 ;",nativeQuery = true)
//    Page<Document> findByDid(int id, Pageable pageable);


}
