package com.example.demo.po;



import lombok.Data;


import javax.persistence.*;

@Data

@Entity
@Table(name = "document")

public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private int id;

    @Column(name = "document_title", length = 200, nullable = false)
    private String documentTitle;

    @Column(name = "authors", length = 500)
    private String authors;

    @Column(name = "author_affiliations", length = 1200)
    private String authorAffiliations;

    @Column(name = "publication_title", length = 100, nullable = false)
    private String publicationTitle;

    @Column(name = "date_added_to_xplore", length = 100)
    private String dateAddedToXplore;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "volume", length = 50)
    private String volume;

    @Column(name = "issue", length = 100)
    private String issue;

    @Column(name = "start_page", length = 50, nullable = false)
    private String startPage;

    @Column(name = "end_page", length = 50, nullable = false)
    private String endPage;

    @Column(name = "abstract", length = 8000, nullable = false)
    private String abst;

    @Column(name = "issn", length = 100)
    private String issn;

    @Column(name = "isbns", length = 100)
    private String isbns;

    @Column(name = "doi", length = 100)
    private String doi;

    @Column(name = "funding_information", length = 100)
    private String fundingInfo;

    @Column(name = "pdf_link", length = 200, nullable = false)
    private String pdfLink;

    @Column(name = "author_keywords", length = 1000)
    private String authorKeywords;

    @Column(name = "ieee_terms", length = 200)
    private String ieeeTerms;

    @Column(name = "inspec_controlled_terms", length = 800)
    private String inspecControlledTerms;

    @Column(name = "inspec_noncontrolled_terms", length = 800)
    private String inspecNoncontrolledTerms;

    @Column(name = "mesh_terms", length = 100)
    private String meshTerms;

    @Column(name = "article_citation_count")
    private int articleCitationCount;

    @Column(name = "reference_count")
    private int referenceCount;

    @Column(name = "license", length = 100)
    private String license;

    @Column(name = "online_date", length = 50)
    private String onlineDate;

    @Column(name = "issue_date", length = 50)
    private String issueDate;

    @Column(name = "meeting_date", length = 50)
    private String meetingDate;

    @Column(name = "publisher", length = 100, nullable = false)
    private String publisher;

    @Column(name = "document_identifier", length = 100, nullable = false)
    private String documentIdentifier;




}
