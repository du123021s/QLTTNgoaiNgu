package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.HocVien;

public class HocVienService extends ConnectMySQLServer{

    public ObservableList<HocVien> hvList = FXCollections.observableArrayList();



}
