package model;

import controller.HocVienController;

import java.time.LocalDate;

public class HocVien {
    private String maHV;
    private String hoTenHV;
    private String gioiTinhHV;
    private LocalDate ngaySinhHV;
    private String sdtHV;
    private String emailHV;
    private String diaChiHV;
    private LocalDate ngayDangKyHoc;
    private String trangThaiHV;
    private LopHoc maLop;

    public HocVien() {
    }

    public HocVien(String maHV, String hoTenHV, String gioiTinhHV, LocalDate ngaySinhHV, String sdtHV, String emailHV, String diaChiHV, LocalDate ngayDangKyHoc, String trangThaiHV, LopHoc maLop) {
        this.maHV = maHV;
        this.hoTenHV = hoTenHV;
        this.gioiTinhHV = gioiTinhHV;
        this.ngaySinhHV = ngaySinhHV;
        this.sdtHV = sdtHV;
        this.emailHV = emailHV;
        this.diaChiHV = diaChiHV;
        this.ngayDangKyHoc = ngayDangKyHoc;
        this.trangThaiHV = trangThaiHV;
        this.maLop = maLop;
    }


    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getHoTenHV() {
        return hoTenHV;
    }

    public void setHoTenHV(String hoTenHV) {
        this.hoTenHV = hoTenHV;
    }

    public String getGioiTinhHV() {
        return gioiTinhHV;
    }

    public void setGioiTinhHV(String gioiTinhHV) {
        this.gioiTinhHV = gioiTinhHV;
    }

    public LocalDate getNgaySinhHV() {
        return ngaySinhHV;
    }

    public void setNgaySinhHV(LocalDate ngaySinhHV) {
        this.ngaySinhHV = ngaySinhHV;
    }

    public String getSdtHV() {
        return sdtHV;
    }

    public void setSdtHV(String sdtHV) {
        this.sdtHV = sdtHV;
    }

    public String getEmailHV() {
        return emailHV;
    }

    public void setEmailHV(String emailHV) {
        this.emailHV = emailHV;
    }

    public String getDiaChiHV() {
        return diaChiHV;
    }

    public void setDiaChiHV(String diaChiHV) {
        this.diaChiHV = diaChiHV;
    }

    public LocalDate getNgayDangKyHoc() {
        return ngayDangKyHoc;
    }

    public void setNgayDangKyHoc(LocalDate ngayDangKyHoc) {
        this.ngayDangKyHoc = ngayDangKyHoc;
    }

    public String getTrangThaiHV() {
        return trangThaiHV;
    }

    public void setTrangThaiHV(String trangThaiHV) {
        this.trangThaiHV = trangThaiHV;
    }

    public LopHoc getMaLop() {
        return maLop;
    }

    public void setMaLop(LopHoc maLop) {
        this.maLop = maLop;
    }
    public String getMaHV(){
        return maHV;
    }

    @Override
    public String toString() {
        return "HocVien{" +
                "maHV='" + maHV + '\'' +
                ", hoTenHV='" + hoTenHV + '\'' +
                ", gioiTinhHV='" + gioiTinhHV + '\'' +
                ", ngaySinhHV=" + ngaySinhHV +
                ", sdtHV='" + sdtHV + '\'' +
                ", emailHV='" + emailHV + '\'' +
                ", diaChiHV='" + diaChiHV + '\'' +
                ", ngayDangKyHoc=" + ngayDangKyHoc +
                ", trangThaiHV='" + trangThaiHV + '\'' +
                ", maLop=" + maLop +
                '}';
    }

}
