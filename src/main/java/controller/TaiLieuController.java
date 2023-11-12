package controller;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.TaiLieu;
import service.TaiLieuService;
import view.AlertUI;
import view.FormOfTaiLieu;

import java.util.concurrent.atomic.AtomicInteger;

public class TaiLieuController extends TaiLieuService {

    public void showCreateForm() throws Exception {
        FormOfTaiLieu formOfTaiLieu = new FormOfTaiLieu();
        Stage stage = new Stage();

        formOfTaiLieu.start(stage);
    }

    public ObservableList getAll(){
        TaiLieuService taiLieuService = new TaiLieuService();
        ObservableList list = taiLieuService.getTLlist();

        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Dữ liệu trống");
        }
        return list;
    }

    public ObservableList  find(String val){
        TaiLieuService taiLieuService = new TaiLieuService();
        ObservableList list = taiLieuService.findTaiLieu(val);

        if(list.isEmpty()){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Không tìm thấy tài liệu");
        }
        return list;
    }

    public void create(TaiLieu taiLieu){
        TaiLieuService taiLieuService = new TaiLieuService();
        int rs = taiLieuService.createTL(taiLieu);
        if(rs!=0){
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Thông báo","Tạo tài liệu mới thành công");
        }else {
            AlertUI alertUI = new AlertUI();
            alertUI.showAlert("Có lỗi xảy ra","Tạo mới thất bại");
        }
    }

    public void delete(ObservableList<TaiLieu> list){
        AtomicInteger rs = new AtomicInteger();
        AlertUI alertUI = new AlertUI();
        int confirm = alertUI.showConfirmAlert();
        if(confirm!=0){
            list.forEach(item ->{
                TaiLieuService taiLieuService = new TaiLieuService();
                rs.set(taiLieuService.deleteTaiLieu(item));
            });
            if (rs.get()!=0){
                alertUI.showAlert("Thông báo","Xóa tài liệu thành công");
            }
        }

    }

    public void showUpdateForm(TaiLieu  taiLieu) throws Exception {
        if (taiLieu==null){
            AlertUI alertUI =  new AlertUI();
            alertUI.showAlert("Chú ý","Chọn tài liệu trước khi cập nhật");
        }else {
            FormOfTaiLieu formOfTaiLieu = new FormOfTaiLieu();
            formOfTaiLieu.reciveTaiLieu(taiLieu);
            Stage stage = new Stage();
            formOfTaiLieu.start(stage);
        }

    }

    public void update(TaiLieu taiLieu){
        TaiLieuService taiLieuService = new TaiLieuService();
        int rs = taiLieuService.updateTailieu(taiLieu);
        AlertUI alertUI = new AlertUI();
        if(rs!=0){

            alertUI.showAlert("Thông báo","Cập nhật thành công");
        }else {
            alertUI.showAlert("Lỗi","Cập nhật không thành công");
        }
    }


}
