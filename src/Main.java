import com.oop.data.*;
import com.oop.service.NhanVatService;
import com.oop.view.MainScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
//        new ThoiKy();
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "ThoiKy":
                        (new ThoiKy()).crawl();
                        break;
                    case "DiaDanh":
                        (new DiaDanh()).crawl();
                        break;
                    case "SuKien":
                        (new SuKien()).crawl();
                        break;
                    case "DanhNhan":
                        (new DanhNhan()).crawl();
                        break;
                    case "TranDanh":
                        (new TranDanh()).crawl();
                        break;
                }
            }
        } else {
            MainScreen mainScreen = new MainScreen();
            mainScreen.on(args);
        }
    }

}
