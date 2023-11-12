package model;

import java.time.LocalDate;


public class TaiLieu {
    private String maTL;
    private String tenTL;
    private Float gia;
    private String nguon;
    private String trangThai;
    private String moTa;
    private String dinhDang;
    private LocalDate taoNgay;
    private LocalDate ngayCapNhat;
    private LoaiTaiLieu loaiTL;
    private TaiLieu_KhoaHoc tL_KH;

    public TaiLieu(){

    }

    public TaiLieu(String maTL, String tenTL, Float gia, String nguon, String trangThai, String moTa, String dinhDang, LocalDate taoNgay, LocalDate ngayCapNhat){
        this.maTL = maTL;
        this.tenTL = tenTL;
        this.gia = gia;
        this.nguon = nguon;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.dinhDang = dinhDang;
        this.taoNgay = taoNgay;
        this.ngayCapNhat = ngayCapNhat;
    }

    public TaiLieu(String maTL, String tenTL, Float gia, String nguon, String trangThai, String moTa, String dinhDang, LocalDate taoNgay, LocalDate ngayCapNhat, LoaiTaiLieu loaiTL, TaiLieu_KhoaHoc tL_KH){
        this.maTL = maTL;
        this.tenTL = tenTL;
        this.gia = gia;
        this.nguon = nguon;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.dinhDang = dinhDang;
        this.taoNgay = taoNgay;
        this.ngayCapNhat = ngayCapNhat;
      //  this.loaiTL = loaiTL;
      //  this.tL_KH = tL_KH;
    }
    public String getmaTL(){ return maTL;}

    public void setMaTl(String maTL ){ this.maTL = maTL;}

    public String getTenTL(){ return tenTL;}

    public void setTenTL(String tenTL){ this.tenTL = tenTL;}

    public Float getGia() { return gia;}

    public void setGia(Float gia){ this.gia = gia;}

    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai){
        this.trangThai = trangThai;
    }

    public String getMoTa(){ return moTa;}
    public void setMoTa(String moTa){
        this.moTa = moTa;
    }

    public String getDinhDang() {
        return dinhDang;
    }

    public void setDinhDang(String dinhDang) {
        this.dinhDang = dinhDang;
    }

    public LocalDate getTaoNgay() {
        return taoNgay;
    }

    public void setTaoNgay(LocalDate taoNgay) {
        this.taoNgay = taoNgay;
    }

    public LocalDate getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public LoaiTaiLieu getLoaiTL() {
        return loaiTL;
    }

    public void setLoaiTL(LoaiTaiLieu loaiTL) {
        this.loaiTL = loaiTL;
    }

    public TaiLieu_KhoaHoc gettL_KH() {
        return tL_KH;
    }

    public void settL_KH(TaiLieu_KhoaHoc tL_KH) {
        this.tL_KH = tL_KH;
    }
}
