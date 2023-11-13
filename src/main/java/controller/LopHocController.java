package controller;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.LopHoc;
import model.PhongHoc;
import service.LopHocService;
import service.PhongHocService;
import view.AlertUI;
import view.FormOfLop;
import view.FormOfPhongHoc;

public class LopHocController extends LopHocService {
    public ObservableList getAll(){
        LopHocService lopHocService = new LopHocService();
        ObservableList  list =lopHocService.getLopHoclist();
        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Dữ liệu trống");
        }
        return list;
    }
    public void showCreateForm() throws Exception {
        FormOfLop formOfLop = new FormOfLop();
        Stage stage = new Stage();

        formOfLop.start(stage);
    }

    public void create(LopHoc lopHoc){
        LopHocService lopHocService = new LopHocService();
        int rs = lopHocService.createLop(lopHoc);
        if(rs!=0){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Tạo lớp học mới thành công");

        }else {
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Có lỗi xảy ra","Tạo mới thất bại");
        }
    }
    public ObservableList  find(String val){
        LopHocService lopHocService = new LopHocService();
        ObservableList list = lopHocService.findLopHoc(val);

        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Không tìm thấy lớp học");
        }
        return list;
    }

    public void showUpdateForm(LopHoc lopHoc) throws Exception {
        if(lopHoc==null){
            AlertUI alertUI =  new AlertUI();
            alertUI.showAlert("Chú ý","Chọn lớp học trước khi cập nhật");
        }else {
            FormOfLop formOfLop = new FormOfLop();
            formOfLop.reciveLop(lopHoc);
            Stage stage = new Stage();
            formOfLop.start(stage);
        }

    }
    public void update(LopHoc lopHoc){
        LopHocService lopHocService = new LopHocService();
        int rs = lopHocService.updateLopHoc(lopHoc);
        AlertUI alertUI = new AlertUI();
        if(rs!=0){
            alertUI.showAlert("Thông báo","Cập nhật thành công");
        }else {
            alertUI.showAlert("Lỗi","Cập nhật không thành công");
        }
    }
    public void delete(LopHoc lopHoc){

        AlertUI alertUI = new AlertUI();
        int confirm = alertUI.showConfirmAlert();
        if(confirm!=0){
            LopHocService lopHocService = new LopHocService();
            int rs = (lopHocService.deleteLopHoc(lopHoc));
            if (rs!=0){
                alertUI.showAlert("Thông báo","Xóa lớp học thành công");
            }
        }

    }
}
