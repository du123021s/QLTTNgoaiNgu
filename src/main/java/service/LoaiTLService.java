package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.LoaiTaiLieu;

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
                ltailieu.setTenTL(rs.getString(2));

                loaiTLlist.add(ltailieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiTLlist;
    }

    public void createLTL(LoaiTaiLieu ltl){
        String maltl = ltl.getMaLoaiTL();
        String ten = ltl.getTenTL();

        try {
            String sql = "INSERT INTO `loaitailieu` VALUES (?,?);";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,maltl);
            ps.setString(2,ten);
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
