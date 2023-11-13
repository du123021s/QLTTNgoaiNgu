package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LoaiTaiLieu;
import model.PhongHoc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhongHocService extends ConnectMySQLServer{
    public ObservableList<PhongHoc> getPhonglist(){
        ObservableList<PhongHoc> phongHocs = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM phonghoc";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                PhongHoc phongHoc = new PhongHoc();

                phongHoc.setMaPhong(rs.getString(1));
                phongHoc.setTrangthai(rs.getString(2));
                phongHocs.add(phongHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongHocs;
    }

    public int createPhong(PhongHoc phongHoc){
        String maPhong = phongHoc.getMaPhong();
        String trangthai = phongHoc.getTrangthai();

        try {
            String sql = "INSERT INTO `phonghoc` (maPhongHoc, trangthai) VALUES (?,?);";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maPhong);
            ps.setString(2,trangthai);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ObservableList<PhongHoc> findPhongHoc(String val){
        String sql = "SELECT * FROM phonghoc WHERE maPhongHoc LIKE ? ;";
        ObservableList<PhongHoc> phongHocs = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,"%"+val+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                PhongHoc phongHoc = new PhongHoc();
                phongHoc.setMaPhong(rs.getString(1));
                phongHoc.setTrangthai(rs.getString(2));

                phongHocs.add(phongHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongHocs;
    }

    public int updatePhongHoc(PhongHoc phongHoc){
        String maP = phongHoc.getMaPhong();
        String trangthai = phongHoc.getTrangthai();
        System.out.println(trangthai);
        String sql = "UPDATE phonghoc SET trangthai=? WHERE maPhongHoc=?";
        try {
            PreparedStatement ps =connectDB.prepareStatement(sql);
            ps.setString(1,trangthai);
            ps.setString(2,maP);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deletePhongHoc(PhongHoc phongHoc){
        String maP = phongHoc.getMaPhong();

        try {
            String sql = "DELETE FROM phonghoc WHERE maPhongHoc=?;";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maP);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ObservableList getOneLoaiPH(String colName) {
        ObservableList data = FXCollections.observableArrayList();
        try {
            String sql = "SELECT " + colName + " FROM phonghoc;";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString(colName));
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public int getPhongHocById(String id){
        String sql = "SELECT Count(*) FROM phonghoc WHERE maPhongHoc=?";
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
