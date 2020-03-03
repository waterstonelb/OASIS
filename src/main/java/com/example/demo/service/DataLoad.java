package com.example.demo.service;

import com.example.demo.dao.AffiliationDao;
import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.dataSource.AuthorData;
import com.example.demo.dataSource.Data;
import com.example.demo.po.Affiliation;
import com.example.demo.po.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoad {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AffiliationDao affiliationDao;

    @PostConstruct
    public void load(){

        /*-------------------Read Data---------------------------------------*/
        List<String> lines = new ArrayList<>();

        try {
            InputStream in = this.getClass().getResourceAsStream("/data.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            String line;
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
            br.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        /*------------------Transfer json to object---------------------------*/
        List<Data> originalData = new ArrayList<>();

        try {
            for (String line : lines)
                originalData.add(objectMapper.readValue(line, Data.class));
        }catch (Exception ex){
            ex.printStackTrace();
        }

        /*-------------------------------Filter-------------------------------*/


        List<Data> filData = new ArrayList<Data>();

        for (Data data : originalData)
            if (data.getAuthors().size() != 0)
                filData.add(data);

        /*--------------------Write to database-------------------------------*/

        writeToDB(filData);
    }

    private void writeToDB(List<Data> filData){

        for (Data data : filData) {
            try {
            Document document = Document.builder().abst(data.getAbst())
                    .doi(data.getDoi()).keywords(data.getKeywords()).title(data.getTitle())
                    .publication(data.getPublication()).pdfLink(data.getPdf_link())
                    .publicationYear(Integer.parseInt(data.getPublish_year())).build();

            Document result = documentDao.saveAndFlush(document);
            int docId = result.getId();

            for (AuthorData adata : data.getAuthors()) {
                boolean auafexists = authorDao
                        .existsByNameAndAffiliation(adata.getName(), adata.getAffiliation());

                if (auafexists){
                    int auId = authorDao
                            .findFirstByNameAndAffiliation(adata.getName()
                                    , adata.getAffiliation()).getId();


                }

            }
        }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }


}
