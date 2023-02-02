package com.oop.util;

public class HienThi {
    public static final String DANH_SACH_THOI_KY = "Danh sách thời kỳ lịch sử";
    public static final String DANH_SACH_TINH_NANG = "Cùng tìm hiểu lịch sử Việt Nam nào!!!";
    public static final String DANH_SACH_NHAN_VAT = "Danh sách nhân vật lịch sử";
    public static final String DANH_SACH_SU_KIEN = "Danh sách sự kiện lịch sử";
    public static final String DANH_SACH_DANH_NHAN = "Danh sách danh nhân lịch sử";
    public static final String DANH_SACH_DIA_DANH = "Danh sách địa danh lịch sử";
    public static final String HOME_URL = "/Trang chủ";
    public static final String THOI_KY_URL = "/Thời kỳ";
    public static final String NHAN_VAT_URL = "/Nhân vật";
    public static final String DIA_DANH_URL = "/Địa danh";
    public static final String DANH_NHAN_URL = "/Danh nhân";
    public static final String SU_KIEN_URL = "/Sự kiện";
    public static final String NHAN_VAT_LIEN_QUAN = "Các nhân vật liên quan";
    public static final String THOI_KY_LIEN_QUAN = "Các thời kỳ liên quan";
    public static final String DANH_NHAN_LIEN_QUAN = "Các danh nhân lịch sử liên quan";
    public static final String DIA_DANH_LIEN_QUAN = "Các địa danh lịch sử liên quan";
    public static String getThoiKyUrl() {
        return HOME_URL + THOI_KY_URL;
    }
    public static String getNhanVatUrl() {
        return HOME_URL + NHAN_VAT_URL;
    }
    public static String getDiaDanhUrl() { return HOME_URL + DIA_DANH_URL; }
    public static String getDanhNhanUrl() { return HOME_URL + DANH_NHAN_URL; }
    public static String getSuKienUrl() { return HOME_URL + SU_KIEN_URL; }

    public static String getDataFromUrl(String url) {
        String[] arr = url.split("/");
        if (arr.length > 0) {
            return arr[arr.length - 1];
        }
        return null;
    }
}
