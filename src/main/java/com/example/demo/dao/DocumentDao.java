package com.example.demo.dao;


import com.example.demo.po.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {

    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.id in " +
            "(select aup.documentId from AuthorPublish aup where aup.authorId in " +
            "(select au.id from Author au where au.name like concat('%',?1,'%') ))")
    Page<Document> findByAuthor(String author, int startTime, int endTime, Pageable pageable);

    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.id in " +
            "(select afp.documentId from AffiliationPublish afp where afp.affId in " +
            "(select af.id from Affiliation af where af.name like concat('%',?1,'%') ))")
    Page<Document> findByInstitution(String institution, int startTime, int endTim, Pageable pageable);

    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.publication like concat('%',?1,'%')")
    Page<Document> findByPublication(String publication, int startTime, int endTim, Pageable pageable);

    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.keywords like concat('%',?1,'%')")
    Page<Document> findByKeywords(String keyword, int startTime, int endTime, Pageable pageable);

    Document findById(int id);

    @Query(value = "select d from Document d where d.publicationYear between ?5 and ?6 and (?1 is null or d.id in " +
            "(select aup.documentId from AuthorPublish aup where aup.authorId in " +
            "(select au.id from Author au where au.name like %?1%))) " +
            "and (?2 is null or d.id in (select afp.documentId from AffiliationPublish afp where afp.affId in " +
            "(select af.id from Affiliation af where af.name like %?2%))) " +
            "and (?3 is null or d.publication like %?3%) " +
            "and (?4 is null or d.keywords like %?4%) ")
    Page<Document> comFind(String author,String affiliation, String publication, String keywords, int startTime, int endTime, Pageable pageable);


}
