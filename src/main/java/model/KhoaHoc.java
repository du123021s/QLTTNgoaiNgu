package model;

import java.sql.Date;
import java.time.LocalDate;

public class KhoaHoc {
    private String maKH;
    private String tenKH;
    private String motaKH;
    private int thoiGianHocKH;
    private Double hocPhiKH;
    private LocalDate ngayBatDauKH;
    private LocalDate ngayKetThucKH;
    private int soLuongHVToiDa;
    private int soLuongHVHienTai;
    private String trangThaiKH;


    public KhoaHoc() {
    }

    public KhoaHoc(String maKH, String tenKH, String motaKH, int thoiGianHocKH, Double hocPhiKH, LocalDate ngayBatDauKH, LocalDate ngayKetThucKH, int soLuongHVToiDa, int soLuongHVHienTai, String trangThaiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.motaKH = motaKH;
        this.thoiGianHocKH = thoiGianHocKH;
        this.hocPhiKH = hocPhiKH;
        this.ngayBatDauKH = ngayBatDauKH;
        this.ngayKetThucKH = ngayKetThucKH;
        this.soLuongHVToiDa = soLuongHVToiDa;
        this.soLuongHVHienTai = soLuongHVHienTai;
        this.trangThaiKH = trangThaiKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMotaKH() {
        return motaKH;
    }

    public void setMotaKH(String motaKH) {
        this.motaKH = motaKH;
    }

    public int getThoiGianHocKH() {
        return thoiGianHocKH;
    }

    public void setThoiGianHocKH(int thoiGianHocKH) {
        this.thoiGianHocKH = thoiGianHocKH;
    }

    public Double getHocPhiKH() {
        return hocPhiKH;
    }

    public void setHocPhiKH(Double hocPhiKH) {
        this.hocPhiKH = hocPhiKH;
    }

    public LocalDate getNgayBatDauKH() {
        return ngayBatDauKH;
    }

    public void setNgayBatDauKH(LocalDate ngayBatDauKH) {
        this.ngayBatDauKH = ngayBatDauKH;
    }

    public LocalDate getNgayKetThucKH() {
        return ngayKetThucKH;
    }

    public void setNgayKetThucKH(LocalDate ngayKetThucKH) {
        this.ngayKetThucKH = ngayKetThucKH;
    }

    public int getSoLuongHVToiDa() {
        return soLuongHVToiDa;
    }

    public void setSoLuongHVToiDa(int soLuongHVToiDa) {
        this.soLuongHVToiDa = soLuongHVToiDa;
    }

    public int getSoLuongHVHienTai() {
        return soLuongHVHienTai;
    }

    public void setSoLuongHVHienTai(int soLuongHVHienTai) {
        this.soLuongHVHienTai = soLuongHVHienTai;
    }

    public String getTrangThaiKH() {
        return trangThaiKH;
    }

    public void setTrangThaiKH(String trangThaiKH) {
        this.trangThaiKH = trangThaiKH;
    }
}













