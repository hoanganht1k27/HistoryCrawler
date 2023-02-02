package com.oop.service;

import com.oop.model.NhanVatModel;
import com.oop.model.ThoiKyModel;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface NhanVatService {
    List<NhanVatModel> getAllNhanVat();
    Map<String, String> getNhanVatByName(String name);
    List<NhanVatModel> getNhanVatByThoiKyName(String name);
    ThoiKyModel getThoiKyByNhanVatName(String nhanVatName);
    List<NhanVatModel> getNhanVatLienQuan(String name);


}
