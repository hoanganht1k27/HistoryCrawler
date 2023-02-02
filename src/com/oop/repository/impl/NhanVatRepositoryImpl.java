package com.oop.repository.impl;

import com.oop.model.Description;
import com.oop.model.NhanVatModel;
import com.oop.model.ThoiKyModel;
import com.oop.repository.NhanVatRepository;
import com.oop.repository.Repository;
import com.oop.util.Config;
import com.oop.util.JsonHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NhanVatRepositoryImpl implements NhanVatRepository, Repository {

    private static NhanVatRepositoryImpl instance;
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    private List<NhanVatModel> models = new ArrayList<>();

    public static NhanVatRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new NhanVatRepositoryImpl();
        }

        return instance;
    }

    private NhanVatRepositoryImpl() {

    }
    @Override
    public void loadData() {
        logger.info("Bat dau load data cac nhan vat lich su");
        JSONObject object = JsonHandler.readJson(Config.NHAN_VAT_FILENAME);
        JSONArray arr = (JSONArray) object.get("list");
        for (int i = 0; i < arr.size(); i++) {
            JSONObject ob = (JSONObject) arr.get(i);
            try {
                String name = (String) ob.get("name");
                NhanVatModel model = new NhanVatModel(name);
                model.setInfo(ob);
                models.add(model);
            } catch (Exception ex) {
                logger.error("Loi khi load data nhan vat lich su", ex);
            }
        }
    }

    @Override
    public List<NhanVatModel> getAllNhanVat() {
        return models;
    }

    @Override
    public NhanVatModel getNhanVatByName(String name) {
        for (NhanVatModel nhanVatModel : models) {
            if (name.equalsIgnoreCase(nhanVatModel.getName())) {
                return nhanVatModel;
            }
        }
        return null;
    }
}
