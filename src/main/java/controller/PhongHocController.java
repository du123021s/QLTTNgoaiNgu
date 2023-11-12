package controller;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.PhongHoc;
import service.PhongHocService;
import view.AlertUI;
import view.FormOfPhongHoc;

public class PhongHocController extends PhongHocService {

    public ObservableList getAll(){
        PhongHocService phongHocService = new PhongHocService();
        ObservableList  list = phongHocService.getPhonglist();
        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Dữ liệu trống");
        }
        return list;
    }

    public void showCreateForm() throws Exception {
        FormOfPhongHoc formOfPhongHoc = new FormOfPhongHoc();
        Stage stage = new Stage();

        formOfPhongHoc.start(stage);
    }

    public void create(PhongHoc phongHoc){
        PhongHocService phongHocService = new PhongHocService();
        int rs = phongHocService.createPhong(phongHoc);
        if(rs!=0){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Tạo phòng học mới thành công");

        }else {
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Có lỗi xảy ra","Tạo mới thất bại");
        }
    }

    public ObservableList  find(String val){
        PhongHocService phongHocService = new PhongHocService();
        ObservableList list = phongHocService.findPhongHoc(val);

        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Không tìm thấy phòng học");
        }
        return list;
    }

    public void showUpdateForm(PhongHoc phongHoc) throws Exception {
        if(phongHoc==null){
            AlertUI alertUI =  new AlertUI();
            alertUI.showAlert("Chú ý","Chọn phòng học trước khi cập nhật");
        }else {
            FormOfPhongHoc formOfPhongHoc = new FormOfPhongHoc();
            formOfPhongHoc.recivePhong(phongHoc);
            Stage stage = new Stage();
            formOfPhongHoc.start(stage);
        }

    }

    public void update(PhongHoc phongHoc){
        PhongHocService phongHocService = new PhongHocService();
        int rs = phongHocService.updatePhongHoc(phongHoc);
        AlertUI alertUI = new AlertUI();
        if(rs!=0){
            alertUI.showAlert("Thông báo","Cập nhật thành công");
        }else {
            alertUI.showAlert("Lỗi","Cập nhật không thành công");
        }
    }

    public void delete(PhongHoc phongHoc){

        AlertUI alertUI = new AlertUI();
        int confirm = alertUI.showConfirmAlert();
        if(confirm!=0){
            PhongHocService phongHocService = new PhongHocService();
            int rs = (phongHocService.deletePhongHoc(phongHoc));
            if (rs!=0){
                alertUI.showAlert("Thông báo","Xóa phòng học thành công");
            }
        }

    }
}
