package model;

import service.ConnectMySQLServer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GiangVien {

    public String maGiangVien;
    public String hoten;
    public String gioitinh;
    public Date ngaysinh;
    private String diachi;
    public String sdt;
    public String email;
    public String chuyennganh;
    public int kinhnghiemgd;
    public String matkhau;
    public Date ngaybatdau;
    public Date ngayketthuc;
    public String avatar;
    public int is_admin;
    public String trangthai;
    public static ConnectMySQLServer con = new ConnectMySQLServer();

    public GiangVien(){
        maGiangVien="";
        hoten="";
        gioitinh="";
        ngaysinh= Date.valueOf("1000-01-01");
        diachi="";
        sdt="";
        email="";
        chuyennganh="";
        kinhnghiemgd=0;
        matkhau="";
        ngaybatdau= Date.valueOf("1000-01-01");
        ngayketthuc=Date.valueOf("1000-01-01");
        avatar="";
        is_admin=0;
        trangthai="";
    }

}
