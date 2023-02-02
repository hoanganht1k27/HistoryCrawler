package com.oop.service.impl;

import com.mysql.cj.log.Log;
import com.oop.model.Description;
import com.oop.model.DiaDanhModel;
import com.oop.repository.DiaDanhRepository;
import com.oop.repository.impl.DiaDanhRepositoryImpl;
import com.oop.service.DiaDanhService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaDanhServiceImpl implements DiaDanhService {

    private Logger logger = LogManager.getLogger(this.getClass().getName());
    private static DiaDanhServiceImpl instance;

    private DiaDanhServiceImpl() {

    }

    public static DiaDanhServiceImpl getInstance() {
        if (instance == null) {
            instance = new DiaDanhServiceImpl();
        }
        return instance;
    }

    private DiaDanhRepository diaDanhRepository = DiaDanhRepositoryImpl.getInstance();

    @Override
    public List<DiaDanhModel> getAllDiaDanh() {
        return diaDanhRepository.getAllDiaDanh();
    }

    @Override
    public Map<String, String> getDiaDanhByName(String name) {
        Description des = diaDanhRepository.getDiaDanhByName(name);
        if (des == null) {
            return new HashMap<>();
        }
        return des.getMapDescription();
    }
}
