package com.example.demo.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "author_publish", schema = "shkb")
@IdClass(AuthorPublishEntityPK.class)
public class AuthorPublishEntity {
    private int documentId;
    private int authorId;
    private int publicationYear;

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
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "publication_year")
    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorPublishEntity that = (AuthorPublishEntity) o;
        return documentId == that.documentId &&
                authorId == that.authorId &&
                publicationYear == that.publicationYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, authorId, publicationYear);
    }
}
