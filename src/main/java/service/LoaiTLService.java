package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.LoaiTaiLieu;
import model.TaiLieu;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoaiTLService extends ConnectMySQLServer{
    public ObservableList<LoaiTaiLieu> getLoaiTLlist(){
        ObservableList<LoaiTaiLieu> loaiTLlist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM loaitailieu";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                LoaiTaiLieu ltailieu = new LoaiTaiLieu();
                ltailieu.setMaLoaiTL(rs.getString(1));
                ltailieu.setTen(rs.getString(2));

                loaiTLlist.add(ltailieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiTLlist;
    }

    public int createLTL(LoaiTaiLieu ltl){
        String maltl = ltl.getMaLoaiTL();
        String ten = ltl.getTen();

        try {
            String sql = "INSERT INTO `loaitailieu` (maLoaiTaiLieu, ten) VALUES (?,?);";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maltl);
            ps.setString(2,ten);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ObservableList getOneLoaiTL(String colName) {
        ObservableList data = FXCollections.observableArrayList();
        try {
            String sql = "SELECT " + colName + " FROM loaitailieu;";
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

    public int deleteLoaiTaiLieu(LoaiTaiLieu loaiTaiLieu){
        String maLoaiTL = loaiTaiLieu.getMaLoaiTL();
        System.out.println(maLoaiTL);
        try {
            String sql = "DELETE FROM loaitailieu WHERE maLoaiTaiLieu = ?;";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maLoaiTL);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ObservableList<LoaiTaiLieu> findLoaiTaiLieu(String val){
        String sql = "SELECT * FROM loaitailieu WHERE maLoaiTaiLieu LIKE ? OR ten LIKE ?;";
        ObservableList<LoaiTaiLieu> loaiTaiLieus = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,"%"+val+"%");
            ps.setString(2,"%"+val+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                LoaiTaiLieu loaiTaiLieu = new LoaiTaiLieu();
                loaiTaiLieu.setMaLoaiTL(rs.getString(1));
                loaiTaiLieu.setTen(rs.getString(2));

                loaiTaiLieus.add(loaiTaiLieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiTaiLieus;
    }

    public int updateLoaiTailieu(LoaiTaiLieu loaiTaiLieu){
        String maLoaiTL = loaiTaiLieu.getMaLoaiTL();
        String ten = loaiTaiLieu.getTen();
        String sql = "UPDATE loaitailieu SET ten=? WHERE maLoaiTaiLieu=?";
        try {
            PreparedStatement ps =connectDB.prepareStatement(sql);
            ps.setString(1,ten);
            ps.setString(2,maLoaiTL);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return 0;
    }
}
