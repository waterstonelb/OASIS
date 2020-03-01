package com.example.demo.service.analyze;

import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.AuthorPublishDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Author;
import com.example.demo.po.AuthorPublishEntity;
import com.example.demo.po.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Service
public class AuthorAnalyzeImpl {

    @Autowired
    private DocumentDao documentDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private AuthorPublishDao authorPublishDao;


    @PostConstruct
    public void analyze(){
       List<Document> all = documentDao.findAll();

       int allsize = all.size();
       int currentsize = 0;

       for (Document document : all){

           //print percent of process
           System.out.println("-----------------------------------");
           System.out.println("process: " + 100* (double)currentsize / allsize +  "%");
           System.out.println("-----------------------------------");
           currentsize++;


           List<String> authors = Arrays.
                   asList(document.getAuthors().split("; "));
           List<String> affiliations = Arrays.
                   asList(document.getAuthorAffiliations().split("; "));

           for (int i = 0; i < authors.size(); i++){
               Author author = new Author();
               AuthorPublishEntity ap = new AuthorPublishEntity();

               if (authorDao.
                       existsByAuthorNameAndAuthorAffiliation(authors.get(i), affiliations.get(i))){
                   int authorId = authorDao.findIdByAnameAndAaff(authors.get(i), affiliations.get(i));
                   ap.setAuthorId(authorId);
                   ap.setDocumentId(document.getId());
                   ap.setPublicationYear(document.getPublicationYear());
                   authorPublishDao.saveAndFlush(ap);
               }
               else {
                   author.setAuthorName(authors.get(i));
                   author.setAuthorAffiliation(affiliations.get(i));
                   authorDao.saveAndFlush(author);
                   int authorId = author.getAuthorId();
                   ap.setAuthorId(authorId);
                   ap.setDocumentId(document.getId());
                   ap.setPublicationYear(document.getPublicationYear());
                   authorPublishDao.saveAndFlush(ap);
               }



           }


       }
   }


}
