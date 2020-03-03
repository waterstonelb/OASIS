package com.example.demo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Builder
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "ref", schema = "shkb")
public class Ref {
    private int id;
    private int docId;
    private String googleScholarLink;
    private String refnum;
    private String refOrder;
    private String refType;
    private String refText;
    private String title;
    private String abst;
    private String acmLink;
    private String crossRefLink;
    private String documentLink;
    private String openUrlImgLoc;
    private String pdfLink;
    private String pdfSize;

    //id
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //文章id
    @Basic
    @Column(name = "doc_id", nullable = false)
    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    //谷歌学术链接
    @Basic
    @Column(name = "google_scholar_link", length = 300)
    public String getGoogleScholarLink() {
        return googleScholarLink;
    }

    public void setGoogleScholarLink(String googleScholarLink) {
        this.googleScholarLink = googleScholarLink;
    }

    //引用在文章中的编号
    @Basic
    @Column(name = "refnum", length = 50)
    public String getRefnum() {
        return refnum;
    }

    public void setRefnum(String refnum) {
        this.refnum = refnum;
    }

    //ref_{ref_num}
    @Basic
    @Column(name = "ref_order", length = 50)
    public String getRefOrder() {
        return refOrder;
    }

    public void setRefOrder(String refOrder) {
        this.refOrder = refOrder;
    }

    //引用类型
    @Basic
    @Column(name = "ref_type", length = 50)
    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    //引用文本
    @Basic
    @Column(name = "ref_text", length = 10000)
    public String getRefText() {
        return refText;
    }

    public void setRefText(String refText) {
        this.refText = refText;
    }

    //引用的文章标题
    @Basic
    @Column(name = "title", length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //引用的文章摘要
    @Basic
    @Column(name = "abst", length = 10000)
    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    //acm 链接
    @Basic
    @Column(name = "acm_link", length = 100)
    public String getAcmLink() {
        return acmLink;
    }

    public void setAcmLink(String acmLink) {
        this.acmLink = acmLink;
    }

    //crossref链接
    @Basic
    @Column(name = "cross_ref_link", length = 100)
    public String getCrossRefLink() {
        return crossRefLink;
    }

    public void setCrossRefLink(String crossRefLink) {
        this.crossRefLink = crossRefLink;
    }

    //文档链接
    @Basic
    @Column(name = "document_link", length = 50)
    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    //图像链接
    @Basic
    @Column(name = "open_url_img_loc", length = 100)
    public String getOpenUrlImgLoc() {
        return openUrlImgLoc;
    }

    public void setOpenUrlImgLoc(String openUrlImgLoc) {
        this.openUrlImgLoc = openUrlImgLoc;
    }

    //pdf链接
    @Basic
    @Column(name = "pdf_link", length = 200)
    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    //pde大小
    @Basic
    @Column(name = "pdf_size", length = 50)
    public String getPdfSize() {
        return pdfSize;
    }

    public void setPdfSize(String pdfSize) {
        this.pdfSize = pdfSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ref refEntity = (Ref) o;
        return id == refEntity.id &&
                docId == refEntity.docId &&
                Objects.equals(googleScholarLink, refEntity.googleScholarLink) &&
                Objects.equals(refnum, refEntity.refnum) &&
                Objects.equals(refOrder, refEntity.refOrder) &&
                Objects.equals(refType, refEntity.refType) &&
                Objects.equals(refText, refEntity.refText) &&
                Objects.equals(title, refEntity.title) &&
                Objects.equals(abst, refEntity.abst) &&
                Objects.equals(acmLink, refEntity.acmLink) &&
                Objects.equals(crossRefLink, refEntity.crossRefLink) &&
                Objects.equals(documentLink, refEntity.documentLink) &&
                Objects.equals(openUrlImgLoc, refEntity.openUrlImgLoc) &&
                Objects.equals(pdfLink, refEntity.pdfLink) &&
                Objects.equals(pdfSize, refEntity.pdfSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docId, googleScholarLink, refnum, refOrder, refType, refText, title, abst, acmLink, crossRefLink, documentLink, openUrlImgLoc, pdfLink, pdfSize);
    }
}
