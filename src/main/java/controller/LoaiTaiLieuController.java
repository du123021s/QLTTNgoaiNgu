package controller;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.LoaiTaiLieu;
import model.TaiLieu;
import service.LoaiTLService;
import service.TaiLieuService;
import view.AlertUI;
import view.FormOfLoaiTL;
import view.FormOfTaiLieu;
import view.PopupUI;

import java.util.concurrent.atomic.AtomicInteger;

public class LoaiTaiLieuController extends LoaiTLService {
    public void showCreateForm() throws Exception {
        FormOfLoaiTL formOfLoaiTL = new FormOfLoaiTL();
        Stage stage = new Stage();
        formOfLoaiTL.start(stage);
    }

    public ObservableList getAll(){
        LoaiTLService loaiTLService = new LoaiTLService();
        ObservableList list = loaiTLService.getLoaiTLlist();

        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Dữ liệu trống");
        }
        return list;
    }

    public ObservableList  find(String val){
        LoaiTLService loaiTLService = new LoaiTLService();
        ObservableList list = loaiTLService.findLoaiTaiLieu(val);

        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Không tìm thấy loại tài liệu "+ val);
        }
        return list;
    }

    public void create(LoaiTaiLieu loaiTaiLieu){
        LoaiTLService loaiTLService = new LoaiTLService();
        int rs = loaiTLService.createLTL(loaiTaiLieu);
        if(rs!=0){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Tạo loại tài liệu mới thành công");
        }else {
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Có lỗi xảy ra","Tạo mới thất bại");
        }
    }

    public void delete(ObservableList<LoaiTaiLieu> list){
        AtomicInteger rs = new AtomicInteger();
        AlertUI alertUI = new AlertUI();
        int confirm = alertUI.showConfirmAlert();
        if(confirm!=0){
            list.forEach(item ->{
                LoaiTLService loaiTLService = new LoaiTLService();
                rs.set(loaiTLService.deleteLoaiTaiLieu(item));
            });
            if (rs.get()!=0){
                alertUI.showAlert("Thông báo","Xóa tài liệu thành công");
            }
        }

    }

    public void showUpdateForm(LoaiTaiLieu loaiTaiLieu) throws Exception {
        if (loaiTaiLieu==null){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Chú ý", "Chọn loại tài liệu trước khi cập nhật");
        }else {
            FormOfLoaiTL formOfLoaiTL = new FormOfLoaiTL();
            formOfLoaiTL.reciveLoaiTaiLieu(loaiTaiLieu);
            Stage stage = new Stage();
            formOfLoaiTL.start(stage);
        }

    }

    public void update(LoaiTaiLieu loaiTaiLieu){
        LoaiTLService loaiTLService = new LoaiTLService();
        int rs = loaiTLService.updateLoaiTailieu(loaiTaiLieu);
        AlertUI alertUI = new AlertUI();
        if(rs!=0){

            alertUI.showAlert("Thông báo","Cập nhật thành công");
        }else {
            alertUI.showAlert("Lỗi","Cập nhật không thành công");
        }
    }
}
