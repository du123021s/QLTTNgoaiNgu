package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LopHoc;
import model.GiangVien;
import model.KhoaHoc;
import model.PhongHoc;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LopHocService extends ConnectMySQLServer {
    public ObservableList<LopHoc> getLopHoclist(){
        ObservableList<LopHoc> lopHocs = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM lop";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                LopHoc lopHoc = new LopHoc();
                KhoaHoc khoaHoc = new KhoaHoc();
                GiangVien giangVien  = new GiangVien();

                lopHoc.setMaLop(rs.getString(1));
                lopHoc.setTrangThaiLop(rs.getString(2));
                lopHoc.setLopTaoNgay(rs.getDate(3).toLocalDate());

                giangVien.setMaGiangVien(rs.getString(4));
                lopHoc.setGiangVien(giangVien);

                khoaHoc.setMaKH(rs.getString(5));
                lopHoc.setKhoaHoc(khoaHoc);
                lopHocs.add(lopHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lopHocs;
    }

    public int createLop(LopHoc lopHoc){
        String maLop = lopHoc.getMaLop();
        String trangthai = lopHoc.getTrangThaiLop();
        LocalDate ngayTao = lopHoc.getLopTaoNgay();
        String maGV = lopHoc.getGiangVien().getMaGiangVien();
        String maKhoaHoc = lopHoc.getKhoaHoc().getMaKH();

        try {
            String sql = "INSERT INTO `lop` (maLop, trangthai, create_at,maGiangVien,maKhoaHoc) VALUES (?,?,?,?,?);";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maLop);
            ps.setString(2,trangthai);
            ps.setDate(3, Date.valueOf(ngayTao));
            ps.setString(4,maGV);
            ps.setString(5,maKhoaHoc);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public ObservableList<LopHoc> findLopHoc(String val){
        String sql = "SELECT * FROM lophoc WHERE maLopHoc LIKE ? ;";
        ObservableList<LopHoc> lopHocs = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,"%"+val+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                LopHoc lopHoc = new LopHoc();
                KhoaHoc khoaHoc = new KhoaHoc();
                GiangVien giangVien  = new GiangVien();

                lopHoc.setMaLop(rs.getString(1));
                lopHoc.setTrangThaiLop(rs.getString(2));
                lopHoc.setLopTaoNgay(rs.getDate(3).toLocalDate());

                giangVien.setMaGiangVien(rs.getString(4));
                lopHoc.setGiangVien(giangVien);

                khoaHoc.setMaKH(rs.getString(5));
                lopHoc.setKhoaHoc(khoaHoc);
                lopHocs.add(lopHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lopHocs;
    }
    public int updateLopHoc(LopHoc lopHoc){
        String maLop = lopHoc.getMaLop();
        String trangthai = lopHoc.getTrangThaiLop();


        System.out.println(trangthai);
        String sql = "UPDATE lophoc SET trangthai=? WHERE maLop=?";
        try {
            PreparedStatement ps =connectDB.prepareStatement(sql);
            ps.setString(1,trangthai);
            ps.setString(2,maLop);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int deleteLopHoc(LopHoc lopHoc){
        String maLop = lopHoc.getMaLop();

        try {
            String sql = "DELETE FROM lop WHERE maLop=?;";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maLop);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public int getLopHocById(String id){
        String sql = "SELECT Count(*) FROM lop WHERE maLop=?";
        try {
            PreparedStatement ps =connectDB.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }



}