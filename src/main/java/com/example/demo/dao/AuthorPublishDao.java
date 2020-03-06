package com.example.demo.dao;

import com.example.demo.po.Author;
import com.example.demo.po.AuthorPublish;
import com.example.demo.po.AuthorPublishPK;
import com.example.demo.po.TopAuthor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorPublishDao extends JpaRepository<AuthorPublish, AuthorPublishPK> {

    List<AuthorPublish> findByAuthorId(int aid);


    @Query("select new com.example.demo.po.TopAuthor(aup.authorId, count (aup.documentId))" +
            " from AuthorPublish aup group by aup.authorId order by count (aup.documentId) desc")
    Page<TopAuthor> findTopAuthor(Pageable pageable);

}
