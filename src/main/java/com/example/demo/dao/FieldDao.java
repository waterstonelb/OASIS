package com.example.demo.dao;

import com.example.demo.po.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldDao extends JpaRepository<Field, Integer> {



    @Query("select distinct f.name from Field f")
    List<String> getAllFields();

    @Query("select distinct f.conference from Field f where f.name in ?1")
    List<String> getConferencesByFields(List<String> fields);

}
