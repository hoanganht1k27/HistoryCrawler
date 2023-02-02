package com.oop.service;

import com.oop.model.DanhNhanModel;
import com.oop.model.DiaDanhModel;
import com.oop.model.NhanVatModel;
import com.oop.model.SuKienModel;

import java.util.List;
import java.util.Map;

public interface SuKienService {
    List<SuKienModel> getAllSuKien();
    Map<String, String> getSuKienByName(String name);
    List<DanhNhanModel> getDanhNhanBySuKienName(String name);
    List<DiaDanhModel> getDiaDanhBySuKienName(String name);
}
