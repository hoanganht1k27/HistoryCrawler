package com.oop.repository;

import com.oop.model.Description;
import com.oop.model.NhanVatModel;

import java.util.List;

public interface NhanVatRepository {
    List<NhanVatModel> getAllNhanVat();
    NhanVatModel getNhanVatByName(String name);
}
