package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.LoaiTaiLieu;
import model.TaiLieu;

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
                tailieu.setGia(rs.getFloat(3));
                tailieu.setNguon(rs.getString(4));
                tailieu.setMota(rs.getString(5));
                tailieu.setMaLoaiTL(rs.getString("maLoaiTaiLieu"));

                TLlist.add(tailieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TLlist;
    }

    public void createLTL(LoaiTaiLieu ltl) {
        String maltl = ltl.getMaLoaiTL();
        String ten = ltl.getTenTL();

        try {
            String sql = "INSERT INTO `loaitailieu` VALUES (?,?);";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1, maltl);
            ps.setString(2, ten);
            ResultSet rs = ps.executeQuery(sql);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful");
            alert.setHeaderText(null);
            alert.setContentText("Create is successful!");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
