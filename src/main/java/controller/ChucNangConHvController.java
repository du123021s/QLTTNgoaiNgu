package controller;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import model.HocVien;
import service.HocVienService;

public class ChucNangConHV {
    HocVien model;
    ChucNangConHV view;

    public ChucNangConHV(HocVien hv, ChucNangConHV view){
        this.model = hv;
        this.view = view;

        // Đăng ký lắng nghe sự kiện từ View
    }

    public void updateData(){

    }

    /

//    protected void kiemTraMaHV(){
//        maHvTxt.setOnKeyReleased(event -> {
//            hocVienService = new HocVienService();
//            if(hocVienService.kiemTraHocVien(maHvTxt.getText()) > 0){
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Lỗi Mã Học Viên");
//                alert.setHeaderText("Mã HV này đã tồn tại. Vui lòng chọn một mã HV khác.");
//                alert.showAndWait();
//                maHvTxt.clear();
//            }else {
//                System.out.println("Không hợp lệ.");
//            }
//        });
//    }
//    protected void handleCancel() {
//    }
//
//    protected void handleAddHv() {
//        this.view.addHvBtn.setOnAction(event -> {
//            System.out.println("cÓ Nha.");
//            statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
//            statusLbl.setPadding(new Insets(15));
//            statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra
//
//            if (maHvTxt.getText().isEmpty()) {
//                statusLbl.setText("Mã HV không được để trống.");
//            }else if (tenHvTxt.getText().isEmpty()) {
//                statusLbl.setText("Tên HV không được để trống.");
//            }else if (selectedValue == null) {
//                statusLbl.setText("Vui lòng chọn giới tính!");
//            }else if (ngaySinhHvDatePicker.getValue() == null) {
//                statusLbl.setText("Vui lòng lựa chọn ngày sinh.");
//            }else if (sdtHvTxt.getText().isEmpty()) {
//                statusLbl.setText("SĐT không được để trống.");
//            }else if(!isPhoneNumberValid(sdtHvTxt.getText())){
//                statusLbl.setText("Số điện thoại không đúng định dạng!");
//            }else if(emailHvTxt.getText().isEmpty() ){
//                statusLbl.setText("Email không được để trống.");
//            }else if (!isEmailValid(emailHvTxt.getText())) {
//                statusLbl.setText("Vui lòng nhập đúng định dạng Email!");
//            }else if(lopHoc == null){
//                statusLbl.setText("Vui lòng chọn lớp học sẽ tham gia.");
//            }else if (trangThaiHvCbb.getValue().isEmpty()) {
//                statusLbl.setText("Vui lòng chọn trạng thái học viên.");
//            }else{
//                HocVien hocVienMoi = new HocVien();
//
//                hocVienMoi.setMaLop(lopHoc);
//                hocVienMoi.setNgaySinhHV(ngaySinhHv);
//                hocVienMoi.setMaHV(maHvTxt.getText());
//                hocVienMoi.setTrangThaiHV(trangThaiHv);
//                hocVienMoi.setSdtHV(sdtHvTxt.getText());
//                if(selectedValue == "Nữ"){
//                    hocVienMoi.setGioiTinhHV("F");
//                }else{
//                    hocVienMoi.setGioiTinhHV("M");
//                }
//
//                hocVienMoi.setHoTenHV(tenHvTxt.getText());
//                hocVienMoi.setEmailHV(emailHvTxt.getText());
//                hocVienMoi.setDiaChiHV(diaChiHvTxt.getText());
//
//                hocVienService = new HocVienService();
//                int result = hocVienService.dangkyHocVienMoi(hocVienMoi);
//                if (result > 0) {
//                    statusLbl.setText("Học Viên mới đã được đăng ký thành công.");
//                    statusLbl.setStyle("-fx-text-fill:green; -fx-font-size:16px");
//                }else{
//                    statusLbl.setText("Đăng ký thất bại.");
//                    statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
//                }
//            }
//        });
//
//    }
//
//    protected void handleClearHv(){
//        clearBtn.setOnAction(event -> {
//            maHvTxt.clear();
//            tenHvTxt.clear();
//            sdtHvTxt.clear();
//            emailHvTxt.clear();
//            diaChiHvTxt.clear();
//            maleRadioBtn.setSelected(false);
//            femaleRadioBtn.setSelected(false);
//
//        });
//    }
    public boolean isPhoneNumberValid(String phoneNumber){
        // Sử dụng biểu thức chính quy để kiểm tra
        String phoneRegex = "^(\\+\\d{1,3})?\\d{10,12}$";
        return phoneNumber.matches(phoneRegex);
    }

    public boolean isEmailValid(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

//    protected void changeColorBtn() {
//        this.view..setOnMouseEntered((event) -> {
//            addHvBtn.setStyle(btnEffect1+font);
//        });
//
//        addHvBtn.setOnMouseExited((event) -> {
//            addHvBtn.setStyle(format+font);
//        });
//
//        clearBtn.setOnMouseEntered((event) -> {
//            clearBtn.setStyle(btnEffect2+font);
//        });
//
//        clearBtn.setOnMouseExited((event) -> {
//            clearBtn.setStyle(bg_red+font);
//        });

    }
}
