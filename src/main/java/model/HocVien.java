package model;

import java.time.LocalDate;

public class HocVien {
    private String maHV;
    private String hoTenHV;
    private String gioiTinhHV;
    private LocalDate ngaySinhHV;
    private String sdtHV;
    private String emailHV;
    private String diaChiHV;
    private String ngayDangKyHoc;
    private String trangThaiHV;
    private String avatarHV;

    public HocVien() {
    }

    public HocVien(String maHV, String hocTenHV, String gioiTinhHV, LocalDate ngaySinhHV,
                   String sdtHV, String emailHV, String diaChiHV, String ngayDangKyHoc,
                   String trangThaiHV, String avatarHV) {
        this.maHV = maHV;
        this.hoTenHV = hocTenHV;
        this.gioiTinhHV = gioiTinhHV;
        this.ngaySinhHV = ngaySinhHV;
        this.sdtHV = sdtHV;
        this.emailHV = emailHV;
        this.diaChiHV = diaChiHV;
        this.ngayDangKyHoc = ngayDangKyHoc;
        this.trangThaiHV = trangThaiHV;
        this.avatarHV = avatarHV;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getHocTenHV() {
        return hoTenHV;
    }

    public void setHocTenHV(String hocTenHV) {
        this.hoTenHV = hocTenHV;
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

    public String getNgayDangKyHoc() {
        return ngayDangKyHoc;
    }

    public void setNgayDangKyHoc(String ngayDangKyHoc) {
        this.ngayDangKyHoc = ngayDangKyHoc;
    }

    public String getTrangThaiHV() {
        return trangThaiHV;
    }

    public void setTrangThaiHV(String trangThaiHV) {
        this.trangThaiHV = trangThaiHV;
    }

    public String getAvatarHV() {
        return avatarHV;
    }

    public void setAvatarHV(String avatarHV) {
        this.avatarHV = avatarHV;
    }
}
