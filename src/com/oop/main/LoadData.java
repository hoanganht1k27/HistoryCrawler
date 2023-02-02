package com.oop.main;

import com.oop.data.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadData {
    private final Crawler[] crawlers = {
            new ThoiKy(),
            new DanhNhan(),
            new DiaDanh(),
            new TranDanh(),
            new SuKien()
    };

    private Logger logger = LogManager.getLogger(this.getClass().getName());
    public LoadData() {
        logger.info("Bat dau load data");
        for (Crawler crawler : crawlers) {
            crawler.crawl();
        }
    }
}
