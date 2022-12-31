package com.oop.data;

import com.oop.util.CallAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static com.oop.data.TranDanh.tranDanhObject;

public class TranDanhData implements Runnable{
    private String thoiKyName;
    private String url;
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    public TranDanhData(String thoiKyName, String url) {
        this.thoiKyName = thoiKyName;
        this.url = url;
    }
    @Override
    public void run() {
        Document doc = CallAPI.callAPI(url);
        JSONArray arr = tranDanhObject.containsKey("list") ? (JSONArray) tranDanhObject.get("list") : new JSONArray();
        JSONObject obj = new JSONObject();
//        try {
//            String khaiQuat = doc.select(".mw-parser-output p").get(0).text();
//            obj.put("khai-quat", khaiQuat);
//        } catch (Exception e) {
//            logger.error("Duong link " + url + " co the khong phai 1 tran danh", e);
//        }

        try {
            Element tranDanhDoc = doc.select(".infobox").get(0);
            String name = doc.select(".mw-page-title-main").text();
            obj.put("name", name);
            Element info = tranDanhDoc.select("table").get(0);
            for (Element e : info.select("tr")) {
                String key = e.select("th").text();
                String value = e.select("td").text();
                if (key.equals("Thời gian Địa điểm Kết quả") || key.equals("Thời gian Địa điểm Kết quả Thay đổi lãnh thổ")) {
                    continue;
                }
                if (key.length() > 0 && value.length() > 0) {
                    obj.put(key, value);
                }
            }

            for (Element e : doc.select("tr")) {
                if (e.text().equals("Tham chiến") || e.text().equals("Chỉ huy và lãnh đạo") || e.text().equals("Lực lượng") || e.text().equals("Thương vong và tổn thất")) {
                    Element ne = e.nextElementSibling();
                    JSONObject o = new JSONObject();
                    o.put("a", ne.select("td").get(0).text());
                    o.put("b", ne.select("td").get(1).text());
                    obj.put(e.text(), o);
                }
            }
        } catch (Exception e) {
            logger.error("Loi khi doc thong tin cua tran danh", e);
        }

        if (obj.keySet().size() > 0) {
            arr.add(obj);
            tranDanhObject.put("list", arr);
        }
    }
}
