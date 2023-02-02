package com.oop.repository;

import com.oop.model.Description;
import com.oop.model.SuKienModel;

import java.util.List;

public interface SuKienRepository {
    List<SuKienModel> getAllSuKien();
    SuKienModel getSuKienByName(String name);
}
