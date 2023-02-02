package com.oop.service.impl;

import com.oop.model.Description;
import com.oop.model.NhanVatModel;
import com.oop.model.ThoiKyModel;
import com.oop.repository.NhanVatRepository;
import com.oop.repository.Repository;
import com.oop.repository.ThoiKyRepository;
import com.oop.repository.impl.NhanVatRepositoryImpl;
import com.oop.repository.impl.ThoiKyRepositoryImpl;
import com.oop.service.NhanVatService;
import com.oop.service.ThoiKyService;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NhanVatServiceImpl implements NhanVatService {
    private static NhanVatServiceImpl instance;
    private NhanVatRepository nhanVatRepository = NhanVatRepositoryImpl.getInstance();
    private ThoiKyService thoiKyService = ThoiKyServiceImpl.getInstance();
    private ThoiKyRepository thoiKyRepository = ThoiKyRepositoryImpl.getInstance();

    private NhanVatServiceImpl() {

    }

    public static NhanVatServiceImpl getInstance() {
        if (instance == null) {
            instance = new NhanVatServiceImpl();
        }
        return instance;
    }


    @Override
    public List<NhanVatModel> getAllNhanVat() {
        return nhanVatRepository.getAllNhanVat();
    }

    @Override
    public Map<String, String> getNhanVatByName(String name) {
        Description des = nhanVatRepository.getNhanVatByName(name);
        if (des == null) {
            return new HashMap<>();
        }
        return des.getMapDescription();
    }

    @Override
    public List<NhanVatModel> getNhanVatByThoiKyName(String name) {
        List<NhanVatModel> all = nhanVatRepository.getAllNhanVat();
        return all.stream().filter(nv -> {
            return name.equalsIgnoreCase(nv.getThoiKyName());
        }).collect(Collectors.toList());
    }

    @Override
    public ThoiKyModel getThoiKyByNhanVatName(String nhanVatName) {
        NhanVatModel model = nhanVatRepository.getNhanVatByName(nhanVatName);
        String thoiKyName = model.getThoiKyName();
        return thoiKyRepository.getThoiKyByName(thoiKyName);
    }

    @Override
    public List<NhanVatModel> getNhanVatLienQuan(String name) {
        NhanVatModel model = nhanVatRepository.getNhanVatByName(name);
        List<NhanVatModel> res = new ArrayList<>();
        if (model != null) {
            Map<String, String> des = ((Description) model).getMapDescription();
            if (des.containsKey("Tiền nhiệm")) {
                NhanVatModel nv = nhanVatRepository.getNhanVatByName(des.get("Tiền nhiệm"));
                if (nv != null) {
                    res.add(nv);
                }
            }
            if (des.containsKey("Kế nhiệm")) {
                NhanVatModel nv = nhanVatRepository.getNhanVatByName(des.get("Kế nhiệm"));
                if (nv != null) {
                    res.add(nv);
                }
            }
        }
        return res;

    }
}
