package model;

import java.time.LocalDate;
public class TaiLieu_KhoaHoc {
    private LocalDate taoNgay;
    private String ghiChu;

    public LocalDate getTaoNgay() {
        return taoNgay;
    }

    public void setTaoNgay(LocalDate taoNgay) {
        this.taoNgay = taoNgay;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
