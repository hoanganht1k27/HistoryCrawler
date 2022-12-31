package com.oop.repository.impl;

import com.oop.data.NhanVat;
import com.oop.data.ThoiKy;
import com.oop.data.TranDanh;
import com.oop.repository.Repository;
import com.oop.util.JsonHandler;
import org.json.simple.JSONObject;

public class RepositoryImpl implements Repository {
    @Override
    public JSONObject getNhanVat() {
        return JsonHandler.readJson(NhanVat.FILENAME);
    }

    @Override
    public JSONObject getThoiKy() {
        return JsonHandler.readJson(ThoiKy.FILENAME);
    }

    @Override
    public JSONObject getTranDanh() {
        return JsonHandler.readJson(TranDanh.FILENAME);
    }
}
