package model;

public class PhongHoc {
    private String maPhong;
    private String trangthai;

    public PhongHoc(String maPhong) {
        this.maPhong = maPhong;
    }

    public PhongHoc() {

    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
