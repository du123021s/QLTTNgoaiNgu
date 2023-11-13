package model;

import java.math.BigDecimal;

public class TaiLieu {
    private String maTaiLieu;
    private String ten;
    private BigDecimal gia;
    private String nguon;
    private String trangThai;
    private String mota;
    private String maLoaiTL;

    public String getMaTaiLieu() {
        return maTaiLieu;
    }

    public void setMaTaiLieu(String maTaiLieu) {
        this.maTaiLieu = maTaiLieu;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMaLoaiTL() {
        return maLoaiTL;
    }

    public void setMaLoaiTL(String maLoaiTL) {
        this.maLoaiTL = maLoaiTL;
    }
}
