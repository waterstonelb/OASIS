package com.example.demo.service;

import com.example.demo.dataSource.Data;
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


    private ObjectMapper objectMapper;

    @Autowired
    public DataLoad(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

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


        /*--------------------Write to database-------------------------------*/

        writeToDB(originalData);
    }

    private void writeToDB(List<Data> originalData){

    }

}
