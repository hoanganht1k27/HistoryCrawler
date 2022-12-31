import com.oop.data.TranDanh;
import com.oop.service.NhanVatService;

public class Main {
    NhanVatService nhanVatService;
    public static void main(String[] args) {
//        new ThoiKy();
//        new TranDanh();
        Main m = new Main();
        m.test();
    }

    public void test() {
        nhanVatService.printName();
    }
}
