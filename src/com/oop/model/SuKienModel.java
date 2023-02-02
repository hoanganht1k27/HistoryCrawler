package com.oop.model;

import com.oop.util.HienThi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuKienModel extends Model implements Description, CustomUrl{
    private String description;
    private List<String> nhanVatLienQuan = new ArrayList<>();
    private List<String> diaDanhLienQuan = new ArrayList<>();
    public SuKienModel(String name) {
        super(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getNhanVatLienQuan() {
        return nhanVatLienQuan;
    }

    public void setNhanVatLienQuan(List<String> nhanVatLienQuan) {
        this.nhanVatLienQuan = nhanVatLienQuan;
    }

    public List<String> getDiaDanhLienQuan() {
        return diaDanhLienQuan;
    }

    public void setDiaDanhLienQuan(List<String> diaDanhLienQuan) {
        this.diaDanhLienQuan = diaDanhLienQuan;
    }

    @Override
    public String getUrl() {
        return HienThi.getSuKienUrl() + "/" + name;
    }

    @Override
    public Map<String, String> getMapDescription() {
        Map<String, String> des = new HashMap<>();
        des.put("Tên", name);
        des.put("Chi tiết", description);
        return des;
    }
}
