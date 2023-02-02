package com.oop.repository;

import com.oop.model.DanhNhanModel;
import com.oop.model.Description;

import java.util.List;

public interface DanhNhanRepository {
    List<DanhNhanModel> getAllDanhNhan();
    DanhNhanModel getDanhNhanByName(String name);
    DanhNhanModel getDanhNhanByName(String name, boolean isContain);
}
