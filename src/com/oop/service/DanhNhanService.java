package com.oop.service;

import com.oop.model.DanhNhanModel;

import java.util.List;
import java.util.Map;

public interface DanhNhanService {
    List<DanhNhanModel> getAllDanhNhan();
    Map<String, String> getDanhNhanByName(String name);
}
