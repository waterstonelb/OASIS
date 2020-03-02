package com.example.demo.po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AffiliationPublishPK implements Serializable {
    private int documentId;
    private int affId;

    @Column(name = "document_id")
    @Id
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Column(name = "aff_id")
    @Id
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
        AffiliationPublishPK that = (AffiliationPublishPK) o;
        return documentId == that.documentId &&
                affId == that.affId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, affId);
    }
}
