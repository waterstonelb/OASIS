package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dataSource.AuthorData;
import com.example.demo.dataSource.ContextData;
import com.example.demo.dataSource.Data;
import com.example.demo.dataSource.RefData;
import com.example.demo.po.*;
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

    @Autowired
    private AuthorPublishDao authorPublishDao;

    @Autowired
    private AffiliationPublishDao affiliationPublishDao;

    @Autowired
    private RefDao refDao;

    @Autowired
    private ContextDao contextDao;



    //postconstruct注解使方法在springboot初始化完成后执行该方法
    //该方法运行较慢, 所以只在需要时使用!!!!!!!!!!!!!!!
    //@PostConstruct
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

        int now = 0;
        int all = filData.size();
        for (Data data : filData) {


            System.out.println("process: " + now + "/" + all);
            now++;


            try {
            Document document = Document.builder().abst(data.getAbst())
                    .doi(data.getDoi()).keywords(data.getKeywords()).title(data.getTitle())
                    .publication(data.getPublication()).pdfLink(data.getPdf_link())
                    .publicationYear(Integer.parseInt(data.getPublish_year())).build();

            Document docRes = documentDao.saveAndFlush(document);
            int docId = docRes.getId();

            for (AuthorData adata : data.getAuthors()) {
                /*--------------load author and author publish message-----------------*/
                boolean auafexists = authorDao
                        .existsByNameAndAffiliation(adata.getName(), adata.getAffiliation());

                if (auafexists){
                    int auId = authorDao
                            .findFirstByNameAndAffiliation(adata.getName()
                                    , adata.getAffiliation()).getId();
                    AuthorPublish authorPublish = AuthorPublish.builder()
                            .authorId(auId).documentId(docId).build();
                    authorPublishDao.saveAndFlush(authorPublish);

                }
                else{
                    Author author = Author.builder()
                            .name(adata.getName()).affiliation(adata.getAffiliation())
                            .ieeeId(adata.getId()).firstName(adata.getFirstName())
                            .lastName(adata.getLastName()).build();
                    Author auRes = authorDao.saveAndFlush(author);
                    AuthorPublish authorPublish = AuthorPublish.builder()
                            .authorId(auRes.getId()).documentId(docId).build();
                    authorPublishDao.saveAndFlush(authorPublish);
                }

                /*--------------load affiliation and affiliation publish message--------------*/
                boolean afexists = affiliationDao.existsByName(adata.getAffiliation());

                if (afexists){
                    int afid = affiliationDao.findFirstByName(adata.getAffiliation()).getId();
                    AffiliationPublish affiliationPublish = AffiliationPublish.builder()
                            .affId(afid).documentId(docId).build();
                    affiliationPublishDao.saveAndFlush(affiliationPublish);
                }
                else{
                    Affiliation affiliation = Affiliation.builder()
                            .name(adata.getAffiliation()).build();
                    Affiliation afRes = affiliationDao.saveAndFlush(affiliation);
                    AffiliationPublish affiliationPublish = AffiliationPublish.builder()
                            .affId(afRes.getId()).documentId(docId).build();
                    affiliationPublishDao.saveAndFlush(affiliationPublish);
                }




            }

            for (RefData refData : data.getRef()){
                /*----------load ref msg--------------------*/

                Ref ref = Ref.builder().docId(docId)
                        .googleScholarLink(refData.getGoogleScholarLink())
                        .refnum(refData.getId()).refOrder(refData.getOrder())
                        .refType(refData.getRefType()).refText(refData.getText())
                        .title(refData.getTitle())
                        .build();


                if (refData.getLinks() != null) {
                    ref.setAbst(refData.getLinks().getAbst());
                    ref.setAcmLink(refData.getLinks().getAcmLink());
                    ref.setCrossRefLink(refData.getLinks().getCrossRefLink());
                    ref.setDocumentLink(refData.getLinks().getDocumentLink());
                    ref.setOpenUrlImgLoc(refData.getLinks().getOpenUrlImgLoc());
                    ref.setPdfLink(refData.getLinks().getPdfLink());
                    ref.setPdfSize(refData.getLinks().getPdfSize());

                }

                Ref refRes = refDao.saveAndFlush(ref);
                int refId = refRes.getId();

                for (ContextData contextData : refData.getContext()){
                    Context context = Context.builder().refId(refId)
                            .txt(contextData.getText())
                            .part(contextData.getPart()).sec(contextData.getSec())
                            .build();
                    contextDao.saveAndFlush(context);

                }

            }


        }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }


}
