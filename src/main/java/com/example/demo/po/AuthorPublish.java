package com.example.demo.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "author_publish")
@IdClass(AuthorPublishPK.class)
public class AuthorPublish {
    private int documentId;
    private String authorId;

    @Id
    @Column(name = "document_id")
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Id
    @Column(name = "author_id")
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorPublish that = (AuthorPublish) o;
        return documentId == that.documentId &&
                Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, authorId);
    }
}
