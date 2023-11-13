package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LopHoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LopHocService extends ConnectMySQLServer{

    public ObservableList<LopHoc> hienThiDanhSachLop(){
        ObservableList<LopHoc> lopHocList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT maLop FROM Lop WHERE trangthai NOT LIKE 'Kết thúc'; ";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                LopHoc lopHoc = new LopHoc();
                lopHoc.setMaLop(resultSet.getString(1));
                lopHocList.add(lopHoc);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return  lopHocList;
    }
}
