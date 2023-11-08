package model;

import service.ConnectMySQLServer;

import java.sql.SQLException;
import java.sql.Statement;

public class LoaiTaiLieu {
    public String maLoaiTL;
    public String tenTL;

    public LoaiTaiLieu() {
    }

    public LoaiTaiLieu(String maLoaiTL, String tenTL) {
        this.maLoaiTL = maLoaiTL;
        this.tenTL = tenTL;
    }

    public String getMaLoaiTL() {
        return maLoaiTL;
    }

    public void setMaLoaiTL(String maLoaiTL) {
        this.maLoaiTL = maLoaiTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }
}
