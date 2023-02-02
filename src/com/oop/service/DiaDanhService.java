package com.oop.service;

import com.oop.model.DiaDanhModel;

import java.util.List;
import java.util.Map;

public interface DiaDanhService {
    List<DiaDanhModel> getAllDiaDanh();
    Map<String, String> getDiaDanhByName(String name);
}
