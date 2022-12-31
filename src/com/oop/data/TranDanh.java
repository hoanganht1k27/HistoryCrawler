package com.oop.data;

import com.oop.util.CallAPI;
import com.oop.util.JsonHandler;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TranDanh {

    private final String BASE_URL = "https://vi.wikipedia.org";
    private final String ALL_URL = "https://vi.wikipedia.org/wiki/Danh_sách_trận_đánh_trong_lịch_sử_Việt_Nam";
    public static final String FILENAME = "database/trandanh.json";

    public static JSONObject tranDanhObject = new JSONObject();
    private final ExecutorService pool = Executors.newFixedThreadPool(8);

    public TranDanh() {
        Document doc = CallAPI.callAPI(ALL_URL);
        Elements elements = doc.select(".mw-parser-output h2");
        for (Element e : elements) {
            String thoiKyName = e.select(".mw-headline").text();
//            System.out.println(thoiKyName);
            Elements listTranDanh = e.nextElementSibling().select("li a");
//            System.out.println(listTranDanh.size());
            for (Element ee : listTranDanh) {
                String tranDanhUrl = ee.attr("href");
//                System.out.println(tranDanhUrl);
//                if (thoiKyName.contains("(938"))
                    pool.submit(new TranDanhData(thoiKyName, BASE_URL + tranDanhUrl));
            }
        }

        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            } else {
                JsonHandler.writeJson(tranDanhObject, FILENAME);
            }
        } catch (InterruptedException ex) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
