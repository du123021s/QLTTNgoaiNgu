package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LichHoc;
import model.PhongHoc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LichHocService extends ConnectMySQLServer {
    public ObservableList<LichHoc> getLichHoc(){
        ObservableList<LichHoc> lichHocs = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM lichhoc";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                LichHoc lichHoc = new LichHoc();
                lichHoc.setSTT(rs.getInt(1));
                System.out.println(rs.getString(1));
                lichHoc.setMaPhong(rs.getString(2));
                lichHoc.setThuTrongTuan(rs.getString(3));
                lichHoc.setMaLop(rs.getString(4));
                lichHoc.setThoiGianHoc(rs.getString(5));
                lichHoc.setTrangthai(rs.getString(6));
                lichHocs.add(lichHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lichHocs;
    }

    public ObservableList<LichHoc> findLichHoc(String val){
        String sql = "SELECT * FROM lichhoc WHERE maPhongHoc LIKE ? or Thu LIKE ? or maLop LIKE ? or shift LIKE ?;";
        ObservableList<LichHoc> lichHocs = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,"%"+val+"%");
            ps.setString(2,"%"+val+"%");
            ps.setString(3,"%"+val+"%");
            ps.setString(4,"%"+val+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                LichHoc lichHoc = new LichHoc();
                lichHoc.setSTT(rs.getInt(1));
                lichHoc.setMaPhong(rs.getString(2));
                lichHoc.setThuTrongTuan(rs.getString(3));
                lichHoc.setMaLop(rs.getString(4));
                lichHoc.setThoiGianHoc(rs.getString(5));
                lichHoc.setTrangthai(rs.getString(6));

                lichHocs.add(lichHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lichHocs;
    }

    public int createLichHoc(LichHoc lichHoc){
        String maPhong = lichHoc.getMaPhong();
        String thu = lichHoc.getThuTrongTuan();
        String maLop = lichHoc.getMaLop();
        String time = lichHoc.getThoiGianHoc();
        String trangthai = lichHoc.getTrangthai();

        try {
            String sql = "INSERT INTO `lichhoc` (maPhongHoc, Thu, maLop, shift, trangthai) VALUES (?,?,?,?,?);";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maPhong);
            ps.setString(2,thu);
            ps.setString(3,maLop);
            ps.setString(4,time);
            ps.setString(5,trangthai);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateLichHoc(LichHoc lichHoc){
        int stt = lichHoc.getSTT();
        String maPhong = lichHoc.getMaPhong();
        String thu = lichHoc.getThuTrongTuan();
        String maLop = lichHoc.getMaLop();
        String time = lichHoc.getThoiGianHoc();
        String trangthai = lichHoc.getTrangthai();
        System.out.println(stt);

        String sql = "UPDATE lichhoc SET maPhongHoc=?, Thu=?, shift=?, trangthai=?, maLop=? WHERE STT=?";
        try {
            PreparedStatement ps =connectDB.prepareStatement(sql);
            ps.setString(1,maPhong);
            ps.setString(2,thu);
            ps.setString(3,time);
            ps.setString(4,trangthai);
            ps.setString(5,maLop);
            ps.setInt(6,stt);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteLichHoc(LichHoc lichHoc){
        int stt =  lichHoc.getSTT();
        try {
            String sql = "DELETE FROM lichhoc WHERE STT=?;";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setInt(1,stt);

            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
