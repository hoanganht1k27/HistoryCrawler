package com.oop.repository.impl;

import com.oop.model.DanhNhanModel;
import com.oop.model.Description;
import com.oop.model.DiaDanhModel;
import com.oop.repository.DanhNhanRepository;
import com.oop.repository.Repository;
import com.oop.util.Config;
import com.oop.util.JsonHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DanhNhanRepositoryImpl implements DanhNhanRepository, Repository {

    private static DanhNhanRepositoryImpl instance;
    private List<DanhNhanModel> models = new ArrayList<>();
    private Logger logger = LogManager.getLogger(this.getClass().getName());

    private DanhNhanRepositoryImpl() {

    }

    public static DanhNhanRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DanhNhanRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void loadData() {
        logger.info("Bat dau load data danh nhan");
        JSONObject object = JsonHandler.readJson(Config.DANH_NHAN_FILENAME);
        JSONArray array = (JSONArray) object.get("list");
        for (int i = 0; i < array.size(); i++) {
            try {
                JSONObject ob = (JSONObject) array.get(i);
                String name = (String) ob.get("name");
                String description = (String) ob.get("description");
                DanhNhanModel danhNhanModel = new DanhNhanModel(name);
                danhNhanModel.setDescription(description);
                models.add(danhNhanModel);
            } catch (Exception ex) {
                logger.error("Loi khi load data danh nhan", ex);
            }
        }
    }

    @Override
    public List<DanhNhanModel> getAllDanhNhan() {
        return models;
    }

    @Override
    public DanhNhanModel getDanhNhanByName(String name) {
        for (DanhNhanModel model : models) {
            if (name.equalsIgnoreCase(model.getName())) {
                return model;
            }
        }
        return null;
    }

    @Override
    public DanhNhanModel getDanhNhanByName(String name, boolean isContain) {
        if (isContain) {
            for (DanhNhanModel model : models) {
                if (model.getName().contains(name)) {
                    return model;
                }
            }
            return null;
        }
        return getDanhNhanByName(name);
    }
}
