package model;

import java.time.LocalDate;

public class LichHoc {
    private LocalDate thoiGianHoc;

    private PhongHoc phongHoc;
    private String thuTrongTuan;
    private LopHoc lopHoc;

    public LichHoc() {
    }

    public LichHoc(LocalDate thoiGianHoc, PhongHoc phongHoc, String thuTrongTuan, LopHoc lopHoc) {
        this.thoiGianHoc = thoiGianHoc;
        this.phongHoc = phongHoc;
        this.thuTrongTuan = thuTrongTuan;
        this.lopHoc = lopHoc;
    }

    public LocalDate getThoiGianHoc() {
        return thoiGianHoc;
    }

    public void setThoiGianHoc(LocalDate thoiGianHoc) {
        this.thoiGianHoc = thoiGianHoc;
    }

    public PhongHoc getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(PhongHoc phongHoc) {
        this.phongHoc = phongHoc;
    }

    public String getThuTrongTuan() {
        return thuTrongTuan;
    }

    public void setThuTrongTuan(String thuTrongTuan) {
        this.thuTrongTuan = thuTrongTuan;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }
}
