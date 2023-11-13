package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TaiLieu;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiLieuService extends ConnectMySQLServer {

    public ObservableList<TaiLieu> getTLlist() {
        ObservableList<TaiLieu> TLlist = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM tailieu";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                TaiLieu tailieu = new TaiLieu();

                tailieu.setMaTaiLieu(rs.getString(1));
                tailieu.setTen(rs.getString(2));
                tailieu.setGia(BigDecimal.valueOf(rs.getFloat(3)));
                tailieu.setNguon(rs.getString(4));
                tailieu.setMota(rs.getString(5));
                tailieu.setMaLoaiTL(rs.getString("maLoaiTaiLieu"));
                tailieu.setTrangThai(rs.getString("trangthai"));

                TLlist.add(tailieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TLlist;
    }

    public int createTL(TaiLieu taiLieu) {
        String maTL = taiLieu.getMaTaiLieu();
        String tenTL = taiLieu.getTen();
        BigDecimal giaTL = taiLieu.getGia();
        String nguon = taiLieu.getNguon();
        String motaTL = taiLieu.getMota();
        String maLTL = taiLieu.getMaLoaiTL();
        String trangthai = taiLieu.getTrangThai();

        try {
            String sql = "INSERT INTO `tailieu` (maTaiLieu,ten,gia,nguon,mota,maLoaiTaiLieu,trangthai) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1, maTL);
            ps.setString(2, tenTL);
            ps.setBigDecimal(3, giaTL);
            ps.setString(4,nguon);
            ps.setString(5,motaTL);
            ps.setString(6,maLTL);
            ps.setString(7,trangthai);

            int rs = ps.executeUpdate();
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteTaiLieu(TaiLieu taiLieu){
        String maTL = taiLieu.getMaTaiLieu();
        System.out.println(maTL);
        try {
            String sql = "DELETE FROM tailieu WHERE maTaiLieu = ?;";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maTL);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ObservableList<TaiLieu> findTaiLieu(String val){
        String sql = "SELECT * FROM tailieu WHERE maTaiLieu LIKE ? OR ten LIKE ?;";
        ObservableList<TaiLieu> taiLieus = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,"%"+val+"%");
            ps.setString(2,"%"+val+"%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                TaiLieu taiLieu = new TaiLieu();

                taiLieu.setMaTaiLieu(rs.getString(1));
                taiLieu.setTen(rs.getString(2));
                taiLieu.setGia(BigDecimal.valueOf(rs.getFloat(3)));
                taiLieu.setNguon(rs.getString(4));
                taiLieu.setMota(rs.getString(5));
                taiLieu.setMaLoaiTL(rs.getString("maLoaiTaiLieu"));
                taiLieu.setTrangThai(rs.getString("trangthai"));

                taiLieus.add(taiLieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiLieus;
    }

    public int updateTailieu(TaiLieu taiLieu){
        String maTL = taiLieu.getMaTaiLieu();
        String tenTL = taiLieu.getTen();
        BigDecimal gia = taiLieu.getGia();
        String nguonTL = taiLieu.getNguon();
        String mota = taiLieu.getMota();
        String maLTL = taiLieu.getMaLoaiTL();
        String trangthai = taiLieu.getTrangThai();
        String sql = "UPDATE tailieu SET ten= ?, gia= ?, nguon= ?, mota= ?, maLoaiTaiLieu= ?, trangthai=? WHERE maTaiLieu = ?";
        try {
            PreparedStatement ps =connectDB.prepareStatement(sql);
            ps.setString(1,tenTL);
            ps.setBigDecimal(2,gia);
            ps.setString(3,nguonTL);
            ps.setString(4,mota);
            ps.setString(5,maLTL);
            ps.setString(6,trangthai);
            ps.setString(7,maTL);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTaiLieuById(String id){
        String sql = "SELECT Count(*) FROM tailieu WHERE maTaiLieu=?";
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
