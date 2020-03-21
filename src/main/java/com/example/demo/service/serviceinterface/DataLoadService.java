package com.example.demo.service.serviceinterface;

import com.example.demo.datasource.Data;

public interface DataLoadService {
    void load();

    void loadSingle(Data data);
}
