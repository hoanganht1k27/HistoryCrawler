package com.oop.controller;

import com.oop.data.ThoiKy;
import com.oop.model.*;
import com.oop.service.*;
import com.oop.service.impl.*;
import com.oop.util.HienThi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class MainScreenController {

    @FXML
    private HBox linkContainer;

    @FXML
    private VBox main;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTf;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button backBtn;

    private ThoiKyService thoiKyService = ThoiKyServiceImpl.getInstance();
    private NhanVatService nhanVatService = NhanVatServiceImpl.getInstance();
    private DiaDanhService diaDanhService = DiaDanhServiceImpl.getInstance();
    private DanhNhanService danhNhanService = DanhNhanServiceImpl.getInstance();
    private SuKienService suKienService = SuKienServiceImpl.getInstance();


    private String url = HienThi.HOME_URL;
    private Deque<String> urls = new ArrayDeque<>();
    private static final int MAX_URL_SIZE = 10;
    @FXML
    private void searchBtnPressed(ActionEvent event) {
//        System.out.println(searchTf.getText());
        if (searchTf.getText() != null) {
            moveToUrl(this.url, searchTf.getText());
        }
    }

    @FXML
    void backBtnPressed(ActionEvent event) {
        if (this.urls.size() > 0)
        moveToUrl(this.urls.removeLast());
    }

    private void addList(String title, String url, String searchVal) {
        if (searchVal != null) {
            if (title.contains(searchVal)) {
                addList(title, url);
            }
        } else addList(title, url);
    }

    private void addList(String title, String url) {
        ListController controller = new ListController();
        controller.setTitle(title);
        controller.setLink(url);
        controller.add(main);
//        System.out.println(listContainer.getHeight());
    }

    private void addDanhSach(String title) {
        DanhSachController danhSach = new DanhSachController();
        danhSach.setDanhSachTitle(title);
        danhSach.add(main);
    }

    public void addHomeData() {
        addDanhSach(HienThi.DANH_SACH_TINH_NANG);
        addList(HienThi.DANH_SACH_THOI_KY, HienThi.getThoiKyUrl());
        addList(HienThi.DANH_SACH_NHAN_VAT, HienThi.getNhanVatUrl());
        addList(HienThi.DANH_SACH_DIA_DANH, HienThi.getDiaDanhUrl());
        addList(HienThi.DANH_SACH_DANH_NHAN, HienThi.getDanhNhanUrl());
        addList(HienThi.DANH_SACH_SU_KIEN, HienThi.getSuKienUrl());
    }

    private void setLinkContainer(String url) {
        linkContainer.getChildren().clear();
        String[] arr = url.split("/");
        String realUrl = "";
        for (int i = 1; i < arr.length; i++) {
            realUrl += "/" + arr[i];
            UrlController urlController = new UrlController(realUrl);
            urlController.setUrl("/ " + arr[i]);
            urlController.add(linkContainer);
        }
    }

    private void addDanhSachThoiKyData(String searchVal) {
        addDanhSach(HienThi.DANH_SACH_THOI_KY);
        List<ThoiKyModel> data = thoiKyService.getAllThoiKy();
        for (ThoiKyModel d : data) {
            addList(d.getName(), d.getUrl(), searchVal);
        }
    }

    private void addChiTiet(String title, String value) {
        ChiTietController chiTietController = new ChiTietController();
        chiTietController.setTitle(title);
        chiTietController.setValue(value);
        chiTietController.add(main);
    }

    private void addDetail(String name) {
        DetailController detailController = new DetailController();
        detailController.setDetailTitle(name);
        detailController.add(main);
    }

    public void showNhanVatLienQuanThoiKy(String name) {
        List<NhanVatModel> arr = nhanVatService.getNhanVatByThoiKyName(name);
        addDanhSach(HienThi.NHAN_VAT_LIEN_QUAN);
        for (NhanVatModel model : arr) {
            addList(model.getName(), model.getUrl());
        }
    }

    private void showDetailThoiKy(String name) {
        addDetail(name);

        Map<String, String> des = thoiKyService.getThoiKyByName(name);
        for (Map.Entry<String, String> m : des.entrySet()) {
            addChiTiet(m.getKey(), m.getValue());
        }

        showNhanVatLienQuanThoiKy(name);
    }

    private void showDetailNhanVat(String name) {
        addDetail(name);

        Map<String, String> des = nhanVatService.getNhanVatByName(name);
        for (Map.Entry<String, String> m : des.entrySet()) {
            addChiTiet(m.getKey(), m.getValue());
        }

        ThoiKyModel thoiKyModel = nhanVatService.getThoiKyByNhanVatName(name);
        if (thoiKyModel != null) {
            addDanhSach(HienThi.THOI_KY_LIEN_QUAN);
            addList(thoiKyModel.getName(), thoiKyModel.getUrl());
        }
        List<NhanVatModel> nhanVatModels = nhanVatService.getNhanVatLienQuan(name);
        if (nhanVatModels.size() > 0) {
            addDanhSach(HienThi.NHAN_VAT_LIEN_QUAN);
            for (NhanVatModel model : nhanVatModels) {
                addList(model.getName(), model.getUrl());
            }
        }

    }

    private void showDetailDiaDanh(String name) {
        addDetail(name);
        Map<String, String> des = diaDanhService.getDiaDanhByName(name);
        for (Map.Entry<String, String> m : des.entrySet()) {
            addChiTiet(m.getKey(), m.getValue());
        }
    }

    private void showDetailDanhNhan(String name) {
        addDetail(name);
        Map<String, String> des = danhNhanService.getDanhNhanByName(name);
        for (Map.Entry<String, String> m : des.entrySet()) {
            addChiTiet(m.getKey(), m.getValue());
        }
    }

    private void showDetailSuKien(String name) {
        addDetail(name);
        Map<String, String> des = suKienService.getSuKienByName(name);
        for (Map.Entry<String, String> m : des.entrySet()) {
            addChiTiet(m.getKey(), m.getValue());
        }

        List<DanhNhanModel> danhNhanModels = suKienService.getDanhNhanBySuKienName(name);
        List<DiaDanhModel> diaDanhModels = suKienService.getDiaDanhBySuKienName(name);

        if (danhNhanModels.size() > 0) {
            addDanhSach(HienThi.DANH_NHAN_LIEN_QUAN);
            for (DanhNhanModel model : danhNhanModels) {
                addList(model.getName(), model.getUrl());
            }
        }

        if (diaDanhModels.size() > 0) {
            addDanhSach(HienThi.DIA_DANH_LIEN_QUAN);
            for (DiaDanhModel model : diaDanhModels) {
                addList(model.getName(), model.getUrl());
            }
        }
    }

    private void addDanhSachNhanVatData(String searchVal) {
        addDanhSach(HienThi.DANH_SACH_NHAN_VAT);

        for (NhanVatModel nhanVatModel : nhanVatService.getAllNhanVat()) {
            addList(nhanVatModel.getName(), nhanVatModel.getUrl(), searchVal);
        }
    }

    private void addDanhSachDiaDanhData(String searchVal) {
        addDanhSach(HienThi.DANH_SACH_DIA_DANH);

        for (DiaDanhModel diaDanhModel : diaDanhService.getAllDiaDanh()) {
            addList(diaDanhModel.getName(), diaDanhModel.getUrl(), searchVal);
        }
    }

    private void addDanhSachDanhNhanData(String searchVal) {
        addDanhSach(HienThi.DANH_SACH_DANH_NHAN);

        for (DanhNhanModel danhNhanModel : danhNhanService.getAllDanhNhan()) {
            addList(danhNhanModel.getName(), danhNhanModel.getUrl(), searchVal);
        }
    }

    private void addDanhSachSuKienData(String searchVal) {
        addDanhSach(HienThi.DANH_SACH_SU_KIEN);

        for (SuKienModel suKienModel : suKienService.getAllSuKien()) {
            addList(suKienModel.getName(), suKienModel.getUrl(), searchVal);
        }
    }

    private void reset(String url) {
        this.urls.addLast(this.url);
        if (this.urls.size() > MAX_URL_SIZE) {
            this.urls.pop();
        }
        this.url = url;
        resetMainScreen(url);
    }

    public void moveToUrl(String url, String searchVal) {
        reset(url);
        addDanhSach("Kết quả tìm kiếm của \"" + searchVal + "\"" );
        generateFromUrl(url, searchVal);
    }

    public void moveToUrl(String url) {
        reset(url);
        generateFromUrl(url, null);
    }

    private void resetMainScreen(String url) {
        setLinkContainer(url);
        main.getChildren().clear();
    }

    private void generateFromUrl(String url, String searchVal) {
        if (HienThi.HOME_URL.equalsIgnoreCase(url)) {
            addHomeData();
        }
        if (HienThi.getThoiKyUrl().equalsIgnoreCase(url)) {
            addDanhSachThoiKyData(searchVal);
        } else if (url.contains(HienThi.getThoiKyUrl())) {
            String thoiKyName = HienThi.getDataFromUrl(url);
            showDetailThoiKy(thoiKyName);
        }
        if (HienThi.getNhanVatUrl().equalsIgnoreCase(url)) {
            addDanhSachNhanVatData(searchVal);
        } else if(url.contains(HienThi.getNhanVatUrl())) {
            String nhanVatname = HienThi.getDataFromUrl(url);
            showDetailNhanVat(nhanVatname);
        }
        if (HienThi.getDiaDanhUrl().equalsIgnoreCase(url)) {
            addDanhSachDiaDanhData(searchVal);
        } else if (url.contains(HienThi.getDiaDanhUrl())) {
            String diaDanhName = HienThi.getDataFromUrl(url);
            showDetailDiaDanh(diaDanhName);
        }
        if (HienThi.getDanhNhanUrl().equalsIgnoreCase(url)) {
            addDanhSachDanhNhanData(searchVal);
        } else if (url.contains(HienThi.getDanhNhanUrl())) {
            String danhNhanName = HienThi.getDataFromUrl(url);
            showDetailDanhNhan(danhNhanName);
        }
        if (HienThi.getSuKienUrl().equalsIgnoreCase(url)) {
            addDanhSachSuKienData(searchVal);
        } else if (url.contains(HienThi.getSuKienUrl())) {
            String suKienName = HienThi.getDataFromUrl(url);
            showDetailSuKien(suKienName);
        }
    }

}
