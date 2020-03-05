USE shkb;


#create document table
DROP TABLE IF EXISTS document;
CREATE TABLE document(
     id                 INT NOT NULL AUTO_INCREMENT
    ,abst               TEXT
    ,doi                VARCHAR(50)
    ,keywords           VARCHAR(400)
    ,publication        VARCHAR(400)
    ,title              VARCHAR(200)
    ,publication_year   INT
    ,citation_count     INT
    ,pdf_link           VARCHAR(200)
    ,PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;



#create author table
DROP TABLE IF EXISTS author;
CREATE TABLE author(
     id                 INT NOT NULL AUTO_INCREMENT
    ,ieee_id            VARCHAR(100)
    ,name               VARCHAR(100) NOT NULL
    ,affiliation        VARCHAR(200)
    ,first_name         VARCHAR(50)
    ,last_name          VARCHAR(50)

    ,PRIMARY KEY (id)
    ,UNIQUE KEY (name, affiliation)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;



#create publish table
DROP TABLE IF EXISTS author_publish;
CREATE TABLE author_publish(
     document_id         INT NOT NULL
    ,author_id           INT NOT NULL
    ,PRIMARY KEY (document_id, author_id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;



#create affiliation table
DROP TABLE IF EXISTS affiliation;
CREATE TABLE affiliation(
     id             INT NOT NULL AUTO_INCREMENT
    ,name           VARCHAR(200)  NOT NULL
    ,PRIMARY KEY (id)
    ,UNIQUE key (name)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;



#create publish table
DROP TABLE IF EXISTS affiliation_publish;
CREATE TABLE affiliation_publish(
     document_id         INT NOT NULL
    ,aff_id              INT NOT NULL
    ,PRIMARY KEY (document_id, aff_id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

#craete reference table
DROP TABLE IF EXISTS ref;
CREATE TABLE ref(
     id                     INT NOT NULL AUTO_INCREMENT
    ,doc_id                 INT NOT NULL
    ,google_scholar_link    VARCHAR(300)
    ,refnum                 VARCHAR(50)
    ,ref_order              VARCHAR(50)
    ,ref_type               VARCHAR(50)
    ,ref_text               TEXT
    ,title                  VARCHAR(200)
    ,abst                   TEXT
    ,acm_link               VARCHAR(100)
    ,cross_ref_link         VARCHAR(100)
    ,document_link          VARCHAR(50)
    ,open_url_img_loc       VARCHAR(100)
    ,pdf_link               VARCHAR(200)
    ,pdf_size               VARCHAR(50)
    , PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

#create context table
DROP TABLE IF EXISTS context;
CREATE TABLE context(
    id                     INT NOT NULL AUTO_INCREMENT
    ,ref_id                INT NOT NULL
    ,txt                   TEXT
    ,part                  VARCHAR(50)
    ,sec                   VARCHAR(50)
    ,primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;