package com.example.demo.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "author_publish")
@IdClass(AuthorPublishPK.class)
public class AuthorPublish {
    //文章id
    private int documentId;
    //作者id
    private int authorId;

    @Id
    @Column(name = "document_id", nullable = false)
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Id
    @Column(name = "author_id", nullable = false)
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }


}
