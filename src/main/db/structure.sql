USE shkb;


#create document table
DROP TABLE IF EXISTS document;
CREATE TABLE document(
     id              INT NOT NULL AUTO_INCREMENT
    ,abst            TEXT
    ,doi             VARCHAR(50)
    ,keywords        VARCHAR(400)
    ,publication     VARCHAR(400)
    ,title           VARCHAR(200)
    ,PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;



#create author table
DROP TABLE IF EXISTS author;
CREATE TABLE author(
     id                 VARCHAR(20) NOT NULL
    ,name               VARCHAR(100) NOT NULL
    ,affiliation        VARCHAR(200)
    ,first_name         VARCHAR(100)
    ,last_name          VARCHAR(100)

    ,PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;



#create publish table
DROP TABLE IF EXISTS author_publish;
CREATE TABLE author_publish(
     document_id         INT NOT NULL
    ,author_id           VARCHAR(20) NOT NULL
    ,PRIMARY KEY (document_id, author_id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;



#create affiliation table
DROP TABLE IF EXISTS affiliation;
CREATE TABLE affiliation(
     id             INT NOT NULL AUTO_INCREMENT
    ,name           VARCHAR(200)  NOT NULL
    ,PRIMARY KEY (id)
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
    ,refid                  VARCHAR(50)
    ,ref_order              VARCHAR(50)
    ,ref_type               VARCHAR(50)
    ,ref_text               TEXT
    ,title                  VARCHAR(200)
    ,abst                   TEXT
    ,acm_link               VARCHAR(100)
    ,cross_ref_link         VARCHAR(100)
    ,document_link          VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;