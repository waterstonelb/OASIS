package com.example.demo.po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AuthorPublishPK implements Serializable {
    private int documentId;
    private String authorId;

    @Column(name = "document_id")
    @Id
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Column(name = "author_id")
    @Id
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
        AuthorPublishPK that = (AuthorPublishPK) o;
        return documentId == that.documentId &&
                Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, authorId);
    }
}
