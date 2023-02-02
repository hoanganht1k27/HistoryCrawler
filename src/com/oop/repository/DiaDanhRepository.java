package com.oop.repository;

import com.oop.data.DiaDanh;
import com.oop.model.Description;
import com.oop.model.DiaDanhModel;

import java.util.List;

public interface DiaDanhRepository {
    List<DiaDanhModel> getAllDiaDanh();
    DiaDanhModel getDiaDanhByName(String name);
    DiaDanhModel getDiaDanhByName(String name, boolean isContain);
}
