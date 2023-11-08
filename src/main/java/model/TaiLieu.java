package model;

public class TaiLieu {
    public String maTaiLieu;
    public String ten;
    public float gia;
    public String nguon;
    public String mota;
    public String maLoaiTL;

    public TaiLieu(String maTaiLieu, String ten, float gia, String nguon, String mota, String maLoaiTL) {
        this.maTaiLieu = maTaiLieu;
        this.ten = ten;
        this.gia = gia;
        this.nguon = nguon;
        this.mota = mota;
        this.maLoaiTL = maLoaiTL;
    }

    public TaiLieu() {

    }

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

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
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
