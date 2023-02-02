package com.oop.model;

import com.oop.util.HienThi;

import java.util.HashMap;
import java.util.Map;

public class DiaDanhModel extends Model implements Description, CustomUrl{
    private String description;
    public DiaDanhModel(String name) {
        super(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getUrl() {
        return HienThi.getDiaDanhUrl() + "/" + name;
    }

    @Override
    public Map<String, String> getMapDescription() {
        Map<String, String> des = new HashMap<>();
        des.put("Tên", name);
        des.put("Chi tiết", description);
        return des;
    }
}
