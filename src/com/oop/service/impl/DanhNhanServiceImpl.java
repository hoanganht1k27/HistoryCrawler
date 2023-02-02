package com.oop.service.impl;

import com.oop.model.DanhNhanModel;
import com.oop.model.Description;
import com.oop.repository.DanhNhanRepository;
import com.oop.repository.impl.DanhNhanRepositoryImpl;
import com.oop.service.DanhNhanService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanhNhanServiceImpl implements DanhNhanService {
    private static DanhNhanServiceImpl instance;
    private DanhNhanRepository danhNhanRepository = DanhNhanRepositoryImpl.getInstance();

    public static DanhNhanServiceImpl getInstance() {
        if (instance == null) {
            instance = new DanhNhanServiceImpl();
        }
        return instance;
    }

    private DanhNhanServiceImpl() {

    }

    @Override
    public List<DanhNhanModel> getAllDanhNhan() {
        return danhNhanRepository.getAllDanhNhan();
    }

    @Override
    public Map<String, String> getDanhNhanByName(String name) {
        Description des = danhNhanRepository.getDanhNhanByName(name);
        if (des == null) {
            return new HashMap<>();
        }
        return des.getMapDescription();
    }
}
