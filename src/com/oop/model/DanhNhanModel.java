package com.oop.model;

import com.oop.util.HienThi;

import java.util.HashMap;
import java.util.Map;

public class DanhNhanModel extends Model implements Description, CustomUrl{

    private String description;

    public DanhNhanModel(String name) {
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
        return HienThi.getDanhNhanUrl() + "/" + name;
    }

    @Override
    public Map<String, String> getMapDescription() {
        Map<String, String> des = new HashMap<>();
        des.put("Tên", name);
        des.put("Chi tiết", description);
        return des;
    }
}
