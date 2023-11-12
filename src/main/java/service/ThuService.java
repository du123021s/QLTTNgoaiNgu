package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Shift;
import model.ThuTrongTuan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThuService extends ConnectMySQLServer{
    public ObservableList getAll(){
        ObservableList thus = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Thu FROM thutrongtuan;";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                thus.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return thus;
    }
}
