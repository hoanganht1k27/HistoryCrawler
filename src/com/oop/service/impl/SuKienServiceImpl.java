package com.oop.service.impl;

import com.oop.model.DanhNhanModel;
import com.oop.model.Description;
import com.oop.model.DiaDanhModel;
import com.oop.model.SuKienModel;
import com.oop.repository.DanhNhanRepository;
import com.oop.repository.DiaDanhRepository;
import com.oop.repository.SuKienRepository;
import com.oop.repository.impl.DanhNhanRepositoryImpl;
import com.oop.repository.impl.DiaDanhRepositoryImpl;
import com.oop.repository.impl.SuKienRepositoryImpl;
import com.oop.service.SuKienService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuKienServiceImpl implements SuKienService {
    private SuKienRepository suKienRepository = SuKienRepositoryImpl.getInstance();
    private DanhNhanRepository danhNhanRepository = DanhNhanRepositoryImpl.getInstance();
    private DiaDanhRepository diaDanhRepository = DiaDanhRepositoryImpl.getInstance();
    private static SuKienServiceImpl instance;

    public static SuKienServiceImpl getInstance() {
        if (instance == null) {
            instance = new SuKienServiceImpl();
        }
        return instance;
    }

    @Override
    public List<SuKienModel> getAllSuKien() {
        return suKienRepository.getAllSuKien();
    }

    @Override
    public Map<String, String> getSuKienByName(String name) {
        Description des = suKienRepository.getSuKienByName(name);
        if (des == null) {
            return new HashMap<>();
        }
        return des.getMapDescription();
    }

    @Override
    public List<DanhNhanModel> getDanhNhanBySuKienName(String name) {
        SuKienModel model = suKienRepository.getSuKienByName(name);
        List<DanhNhanModel> res = new ArrayList<>();
        if (model == null) {
            return res;
        }
        for (String s : model.getNhanVatLienQuan()) {
            DanhNhanModel danhNhanModel = danhNhanRepository.getDanhNhanByName(s, true);
            if (danhNhanModel != null) {
                res.add(danhNhanModel);
            }
        }

        return res;
    }

    @Override
    public List<DiaDanhModel> getDiaDanhBySuKienName(String name) {
        SuKienModel model = suKienRepository.getSuKienByName(name);
        List<DiaDanhModel> res = new ArrayList<>();
        if (model != null) {
            for (String s : model.getDiaDanhLienQuan()) {
                DiaDanhModel diaDanhModel = diaDanhRepository.getDiaDanhByName(s, true);
                if (diaDanhModel != null) {
                    res.add(diaDanhModel);
                }
            }
        }
        return res;
    }
}
