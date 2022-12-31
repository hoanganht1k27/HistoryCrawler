package com.oop.data;

import com.oop.data.DongLichSu;
import com.oop.util.CallAPI;
import com.oop.util.JsonHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThoiKy {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    private static final String BASE_URL = "https://nguoikesu.com";
    public static final String FILENAME = "database/danhsachthoiky.json";
    private static final String NHAN_VAT_FILE = "database/nhanvat.json";
    private Document doc;
    private int numOfThoiKy = 0;

    public static JSONObject nhanVatObject = new JSONObject();

    private final ExecutorService pool = Executors.newFixedThreadPool(8);

    public ThoiKy() {
        try {
            logger.info("Bat dau lay thong tin cac thoi ky lich su");
            doc = CallAPI.callAPI(BASE_URL);
            Elements cacThoiKyLichSu = doc.select(".jm-module-in .level-0");
            numOfThoiKy = cacThoiKyLichSu.size();
            JSONObject obj = new JSONObject();
            obj.put("description", "Danh sách các thời kỳ lịch sử Việt Nam");
            JSONArray arr = new JSONArray();
            for(int i = 0; i < numOfThoiKy; i++) {
                Elements es = cacThoiKyLichSu.get(i).select("a");
                for (Element e : es) {
                    JSONObject o = new JSONObject();
                    o.put("name", e.text());
                    o.put("link", BASE_URL + e.attr("href"));
                    pool.submit(new DongLichSu(e.text(), BASE_URL + e.attr("href")));
                    arr.add(o);
                }
            }
            obj.put("list", arr);
            JsonHandler.writeJson(obj, FILENAME);
            pool.shutdown();
            try {
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                } else {
                    JsonHandler.writeJson(nhanVatObject, NHAN_VAT_FILE);
                }
            } catch (InterruptedException ex) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }

        } catch (Exception e) {
            logger.error("Loi khi lay thong tin cac thoi ky lich su", e);
        }
    }
}
