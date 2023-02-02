package com.oop.model;

import com.oop.util.HienThi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NhanVatModel extends Model implements Description, CustomUrl{

    private Map<String, String> info = new HashMap<>();
    private Logger logger = LogManager.getLogger(this.getClass().getName());
    private String thoiKyName;
    private String thoiKyCode;
    private String code;

    public NhanVatModel(String name) {
        super(name);
    }

    public void setInfo(JSONObject obj) {
        try {
            this.thoiKyCode = (String) obj.get("thoi-ky-code");
            this.thoiKyName = (String) obj.get("thoi-ky-name");
            this.code = (String) obj.get("code");
            for (Object s : obj.keySet()) {
                String key = (String) s;
                if (!"thoi-ky-code".equalsIgnoreCase(key)
                    && !"thoi-ky-name".equalsIgnoreCase(key)
                    && !"name".equalsIgnoreCase(key)
                    && !"code".equalsIgnoreCase(key)) {
                    this.info.put(key, (String) obj.get(s));
                }
            }
        } catch (Exception ex) {
            logger.error("Co loi khi set thong tin cho nhan vat " + name, ex);
        }
    }

    @Override
    public String getUrl() {
        return HienThi.getNhanVatUrl() + "/" + name;
    }

    @Override
    public Map<String, String> getMapDescription() {
        Map<String, String> res = new HashMap<>();
        res.putAll(info);
        res.put("Tên", name);
        return res;
    }

    public String getThoiKyName() {
        return thoiKyName;
    }

    public String getThoiKyCode() {
        return thoiKyCode;
    }

    public String getCode() {
        return code;
    }
}
