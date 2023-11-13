package controller;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.LichHoc;
import model.LoaiTaiLieu;
import model.TaiLieu;
import service.LichHocService;
import service.LoaiTLService;
import service.TaiLieuService;
import view.AlertUI;
import view.FormOfLichHoc;
import view.FormOfLoaiTL;

import java.util.concurrent.atomic.AtomicInteger;

public class LichHocController extends LichHocService {

    public ObservableList getAll(){
        LichHocService lichHocService = new LichHocService();
        ObservableList list = lichHocService.getLichHoc();

        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Dữ liệu trống");
        }
        return list;
    }

    public ObservableList  find(String val){
        LichHocService lichHocService = new LichHocService();
        ObservableList list = lichHocService.findLichHoc(val);

        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Không tìm thấy lịch học");
        }
        return list;
    }

    public void delete(ObservableList<LichHoc> list) {
        AtomicInteger rs = new AtomicInteger();
        AlertUI alertUI = new AlertUI();
        int confirm = alertUI.showConfirmAlert();
        if (confirm != 0) {
            list.forEach(item -> {
                LichHocService lichHocService = new LichHocService();
                rs.set(lichHocService.deleteLichHoc(item));
            });
            if (rs.get() != 0) {
                alertUI.showAlert("Thông báo", "Xóa lịch học thành công");
            }
        }
    }

    public void showCreateForm() throws Exception {
        FormOfLichHoc formOfLichHoc = new FormOfLichHoc();
        Stage stage = new Stage();
        formOfLichHoc.start(stage);
    }

    public void create(LichHoc lichHoc){
        LichHocService lichHocService = new LichHocService();
        int rs = lichHocService.createLichHoc(lichHoc);
        if(rs!=0){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Tạo lịch học mới thành công");
        }else {
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Có lỗi xảy ra","Tạo mới thất bại");
        }
    }

    public void showUpdateForm(LichHoc lichHoc) throws Exception {
        if (lichHoc==null){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Chú ý","Vui lòng chọn lịch học trước khí cập nhật");
        }else {
            FormOfLichHoc formOfLichHoc = new FormOfLichHoc();
            formOfLichHoc.reciveLichHoc(lichHoc);
            Stage stage = new Stage();
            formOfLichHoc.start(stage);
        }

    }

    public void update(LichHoc lichHoc){
        LichHocService lichHocService = new LichHocService();
        int rs =lichHocService.updateLichHoc(lichHoc);
        AlertUI alertUI = new AlertUI();
        if(rs!=0){

            alertUI.showAlert("Thông báo","Cập nhật thành công");
        }else {
            alertUI.showAlert("Lỗi","Cập nhật không thành công");
        }
    }
}
