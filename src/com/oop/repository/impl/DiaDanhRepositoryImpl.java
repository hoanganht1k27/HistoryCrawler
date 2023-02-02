package com.oop.repository.impl;

import com.oop.model.DiaDanhModel;
import com.oop.repository.DiaDanhRepository;
import com.oop.repository.Repository;
import com.oop.util.Config;
import com.oop.util.JsonHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiaDanhRepositoryImpl implements DiaDanhRepository, Repository {
    private static DiaDanhRepositoryImpl instance;
    private static final Logger logger = LogManager.getLogger(DiaDanhRepositoryImpl.class.getName());
    private List<DiaDanhModel> models = new ArrayList<>();

    public static DiaDanhRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DiaDanhRepositoryImpl();
        }

        return instance;
    }

    private DiaDanhRepositoryImpl() {

    }

    @Override
    public void loadData() {
        logger.info("Bat dau load data dia danh");
        JSONObject object = JsonHandler.readJson(Config.DIA_DANH_FILENAME);
        JSONArray array = (JSONArray) object.get("list");
        for (int i = 0; i < array.size(); i++) {
            try {
                JSONObject ob = (JSONObject) array.get(i);
                String name = (String) ob.get("name");
                String description = (String) ob.get("description");
                DiaDanhModel diaDanhModel = new DiaDanhModel(name);
                diaDanhModel.setDescription(description);
                models.add(diaDanhModel);
            } catch (Exception ex) {
                logger.error("Loi khi load data dia danh", ex);
            }
        }
    }

    @Override
    public List<DiaDanhModel> getAllDiaDanh() {
        return models;
    }

    @Override
    public DiaDanhModel getDiaDanhByName(String name) {
        for (DiaDanhModel model : models) {
            if(name.equalsIgnoreCase(model.getName())) {
                return model;
            }
        }
        return null;
    }

    @Override
    public DiaDanhModel getDiaDanhByName(String name, boolean isContain) {
        if (isContain) {
            for (DiaDanhModel model : models) {
                if (model.getName().contains(name)) {
                    return model;
                }
            }
            return null;
        }
        return getDiaDanhByName(name);
    }
}
