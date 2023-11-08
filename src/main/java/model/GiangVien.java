package model;

import java.time.LocalDate;

public class GiangVien {
    private String maGiangVien;
    private String hoten;
    private String gioitinh;
    private LocalDate ngaysinh;
    private String diachi;
    private String sdt;
    private String email;
    private String chuyennganh;
    private int kinhnghiemgd;
    private String matkhau;
    private LocalDate ngaybatdau;
    private LocalDate ngayketthuc;
    private String avatar;
    private int is_admin;
    private String trangthai;

    public GiangVien(){
        maGiangVien="";
        hoten="";
        gioitinh="";
        ngaysinh= LocalDate.of(1970,1,1);
        diachi="";
        sdt="";
        email="";
        chuyennganh="";
        kinhnghiemgd=0;
        matkhau="";
        ngaybatdau= LocalDate.of(1970,1,1);
        ngayketthuc= LocalDate.of(1970,1,1);
        avatar="";
        is_admin=0;
        trangthai="";
    }



    public GiangVien(String maGiangVien, String hoten, String gioitinh, LocalDate ngaysinh,
                     String diachi, String sdt, String email, String chuyennganh,
                     int kinhnghiemgd, String matkhau, LocalDate ngaybatdau, LocalDate ngayketthuc, String avatar, int is_admin, String trangthai) {
        this.maGiangVien = maGiangVien;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.chuyennganh = chuyennganh;
        this.kinhnghiemgd = kinhnghiemgd;
        this.matkhau = matkhau;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.avatar = avatar;
        this.is_admin = is_admin;
        this.trangthai = trangthai;
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public LocalDate getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChuyennganh() {
        return chuyennganh;
    }

    public void setChuyennganh(String chuyennganh) {
        this.chuyennganh = chuyennganh;
    }

    public int getKinhnghiemgd() {
        return kinhnghiemgd;
    }

    public void setKinhnghiemgd(int kinhnghiemgd) {
        this.kinhnghiemgd = kinhnghiemgd;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public LocalDate getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(LocalDate ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public LocalDate getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(LocalDate ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
