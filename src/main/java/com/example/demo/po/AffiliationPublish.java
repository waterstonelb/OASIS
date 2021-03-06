package com.example.demo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor

@Entity
@Table(name = "affiliation_publish")
@IdClass(AffiliationPublishPK.class)
public class AffiliationPublish {

    //文章id
    private int documentId;

    //机构id
    private int affId;

    public AffiliationPublish() {
    }

    @Id
    @Column(name = "document_id", nullable = false)
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Id
    @Column(name = "aff_id", nullable = false)
    public int getAffId() {
        return affId;
    }

    public void setAffId(int affId) {
        this.affId = affId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AffiliationPublish that = (AffiliationPublish) o;
        return documentId == that.documentId &&
                affId == that.affId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, affId);
    }
}
