package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.FXPermission;
import model.LichHoc;
import model.Shift;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftService extends ConnectMySQLServer{
    public ObservableList<Shift> getAll(){
        ObservableList<Shift> shifts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM shift;";
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                Shift shift = new Shift();
                shift.setId(rs.getString(1));
                shift.setTime(rs.getString(2));
                shifts.add(shift);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shifts;
    }

    public String getShiftDetailById(String id){
        String sql = "SELECT time FROM shift WHERE idshift=?";
        String time ="";
        try {
            PreparedStatement ps =connectDB.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                time = rs.getString(1);
                return time;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return time;
    }
}
