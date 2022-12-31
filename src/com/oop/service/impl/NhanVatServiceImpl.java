package com.oop.service.impl;

import com.oop.repository.Repository;
import com.oop.repository.impl.RepositoryImpl;
import com.oop.service.NhanVatService;
import org.json.simple.JSONObject;

import java.util.List;

public class NhanVatServiceImpl implements NhanVatService {

    private Repository repository = new RepositoryImpl();

    private static JSONObject allNhanVat = null;

    @Override
    public JSONObject getNhanVatByName(String name) {
        if (allNhanVat == null) {
            allNhanVat = repository.getNhanVat();
        }
        return null;
    }

    @Override
    public List<JSONObject> getNhanVatByThoiKyCode(String thoiKyCode) {
        if (allNhanVat == null) {
            allNhanVat = repository.getNhanVat();
        }
        return null;
    }

    @Override
    public JSONObject getNhanVatByCode(String code) {
        if (allNhanVat == null) {
            allNhanVat = repository.getNhanVat();
        }
        return null;
    }

    @Override
    public int countAllNhanVat() {
        if (allNhanVat == null) {
            allNhanVat = repository.getNhanVat();
        }
        return 0;
    }
}
