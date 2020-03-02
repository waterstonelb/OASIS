package com.example.demo.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "affiliation_publish", schema = "shkb", catalog = "")
@IdClass(AffiliationPublishPK.class)
public class AffiliationPublish {
    private int documentId;
    private int affId;

    @Id
    @Column(name = "document_id")
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Id
    @Column(name = "aff_id")
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
