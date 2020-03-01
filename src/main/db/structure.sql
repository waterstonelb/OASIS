USE shkb;


#create document table
DROP TABLE IF EXISTS document;
CREATE TABLE document(
     document_id                 INT NOT NULL AUTO_INCREMENT
    ,document_title              VARCHAR(200) NOT NULL
    ,authors                     VARCHAR(500) NOT NULL
    ,author_affiliations         VARCHAR(1200)
    ,publication_title           VARCHAR(100) NOT NULL
    ,date_added_to_xplore        VARCHAR(100)
    ,publication_year            INTEGER  NOT NULL
    ,volume                      VARCHAR(50)
    ,issue                       VARCHAR(100)
    ,start_page                  VARCHAR(50) NOT NULL
    ,end_page                    VARCHAR(50) NOT NULL
    ,abstract                    VARCHAR(8000) NOT NULL
    ,issn                        VARCHAR(100)
    ,isbns                       VARCHAR(100)
    ,doi                         VARCHAR(100)
    ,funding_information         VARCHAR(100)
    ,pdf_link                    VARCHAR(200) NOT NULL
    ,author_keywords             VARCHAR(1000)
    ,ieee_terms                  VARCHAR(200)
    ,inspec_controlled_terms     VARCHAR(800)
    ,inspec_noncontrolled_terms  VARCHAR(800)
    ,mesh_terms                  VARCHAR(100)
    ,article_citation_count      INTEGER
    ,reference_count             INTEGER
    ,license                     VARCHAR(100)
    ,online_date                 VARCHAR(50)
    ,issue_date                  VARCHAR(50)
    ,meeting_date                VARCHAR(50)
    ,publisher                   VARCHAR(100) NOT NULL
    ,document_identifier         VARCHAR(100) NOT NULL

    ,PRIMARY KEY (document_id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

#create author table
DROP TABLE IF EXISTS author;
CREATE TABLE author(
     author_id          INT NOT NULL AUTO_INCREMENT
    ,author_name        VARCHAR(100) NOT NULL
    ,author_affiliation  VARCHAR(200)

    ,PRIMARY KEY (author_id)
    ,UNIQUE KEY (author_name, author_affiliation)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

#create publish table
DROP TABLE IF EXISTS author_publish;
CREATE TABLE author_publish(
     document_id         INT NOT NULL
    ,author_id           INT NOT NULL
    ,publication_year    INTEGER  NOT NULL
    ,PRIMARY KEY (document_id, author_id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

#index
CREATE INDEX aindex ON author(author_name, author_affiliation);