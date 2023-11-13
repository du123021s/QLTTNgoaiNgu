//package controller;
//
//import javafx.geometry.Insets;
//import javafx.scene.control.Alert;
//import model.HocVien;
//import service.HocVienService;
//import view.ChucNangConHV;
//import view.HocVienUI;
//
//public class ChucNangConHvController {
//    private HocVien model;
//    private ChucNangConHV view;
//
//    public ChucNangConHvController() {
//        this.model = new HocVien();
//        this.view = new ChucNangConHV();
//        // Đăng ký lắng nghe sự kiện từ View
//    }
//
//    public void setUpdateDataUI(){
//        String valueTitleForm = "Cập Nhật Học Viên";
//        String valueBtn = "Cập nhật";
//        this.view.setUpdataDataUI(valueTitleForm, valueBtn, model);
//        this.view.show();
//        clear();
//    }
//
//    public void loadAddData() {
//        this.view.show();
//        handleAddHv();
//        handleUpdateHv();
//        kiemTraMaHV();
//        clear();
//    }
//
//    public void loadUpdateData() {
//        handleUpdateHv();
//        kiemTraMaHV();
//        clear();
//    }
//
//    protected void handleAddHv() {
//        this.view.addHvBtn.setOnAction(event -> {
//            System.out.println("cÓ Nha.");
//            if (checkForm() == 0) {
//                HocVien hocVienMoi = new HocVien();
//                hocVienMoi.setMaLop(this.view.lopHoc);
//                hocVienMoi.setNgaySinhHV(this.view.ngaySinhHv);
//                hocVienMoi.setMaHV(this.view.maHvTxt.getText());
//                hocVienMoi.setTrangThaiHV(this.view.trangThaiHv);
//                hocVienMoi.setSdtHV(this.view.sdtHvTxt.getText());
//                if ("Nữ".equals(this.view.selectedValue)) {
//                    hocVienMoi.setGioiTinhHV("F");
//                } else if ("Nam".equals(this.view.selectedValue)) {
//                    hocVienMoi.setGioiTinhHV("M");
//                }
//
//                hocVienMoi.setHoTenHV(this.view.tenHvTxt.getText());
//                hocVienMoi.setEmailHV(this.view.emailHvTxt.getText());
//                hocVienMoi.setDiaChiHV(this.view.diaChiHvTxt.getText());
//
//                HocVienService hocVienService = new HocVienService();
//                int result = hocVienService.dangkyHocVienMoi(hocVienMoi);
//                if (result > 0) {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Thành công");
//                    alert.setHeaderText("Đăng ký học viên mới thành công!");
//                    alert.showAndWait();
//
//                    this.view.statusLbl.setText("Học Viên mới đã được đăng ký thành công.");
//                    this.view.statusLbl.setStyle("-fx-text-fill:green; -fx-font-size:16px");
//                } else {
//                    this.view.statusLbl.setText("Đăng ký thất bại.");
//                    this.view.statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
//                }
//            }
//        });
//    }
//
//
//    protected void handleUpdateHv() {
//        this.view.addHvBtn.setOnAction(event -> {
//            this.view.getUpdateDataUI();
//            System.out.println("Hoc vien");
//            System.out.println(this.view.model.getGioiTinhHV() + " " + this.view.model.getMaLop());
//
//        });
//    }
//
//    protected void kiemTraMaHV() {
//        this.view.maHvTxt.setOnKeyReleased(event -> {
//            HocVienService hocVienService = new HocVienService();
//            if (hocVienService.kiemTraHocVien(view.maHvTxt.getText()) > 0) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Lỗi Mã Học Viên");
//                alert.setHeaderText("Mã HV này đã tồn tại. Vui lòng chọn một mã HV khác.");
//                alert.showAndWait();
//                this.view.maHvTxt.clear();
//            } else {
//                System.out.println("Không hợp lệ.");
//            }
//        });
//    }
//
//    protected void clear() {
//        this.view.clearBtn.setOnAction(event -> {
//            this.view.clearData();
//        });
//    }
//
//    protected boolean isPhoneNumberValid(String phoneNumber) {
//        // Sử dụng biểu thức chính quy để kiểm tra
//        String phoneRegex = "^(\\+\\d{1,3})?\\d{10,12}$";
//        return phoneNumber.matches(phoneRegex);
//    }
//
//    protected boolean isEmailValid(String email) {
//        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
//        return email.matches(emailRegex);
//    }
//
//    protected int checkForm() {
//        this.view.statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
//        this.view.statusLbl.setPadding(new Insets(15));
//        this.view.statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra
//
//        if (this.view.maHvTxt.getText().isEmpty()) {
//            this.view.statusLbl.setText("Mã HV không được để trống.");
//        } else if (this.view.tenHvTxt.getText().isEmpty()) {
//            this.view.statusLbl.setText("Tên HV không được để trống.");
//        } else if (this.view.toggleGroup.getSelectedToggle() == null) {
//            this.view.statusLbl.setText("Vui lòng chọn giới tính!");
//        } else if (this.view.ngaySinhHvDatePicker.getValue() == null) {
//            this.view.statusLbl.setText("Vui lòng chọn ngày sinh!");
//        } else if (this.view.sdtHvTxt.getText().isEmpty()) {
//            this.view.statusLbl.setText("SĐT không được để trống.");
//        } else if (!isPhoneNumberValid(this.view.sdtHvTxt.getText())) {
//            this.view.statusLbl.setText("Số điện thoại không đúng định dạng!");
//        } else if (this.view.emailHvTxt.getText().isEmpty()) {
//            this.view.statusLbl.setText("Email không được để trống.");
//        } else if (!isEmailValid(this.view.emailHvTxt.getText())) {
//            this.view.statusLbl.setText("Vui lòng nhập đúng định dạng Email!");
//        } else if (this.view.lopHocComboBox.getItems() == null) {
//            this.view.statusLbl.setText("Vui lòng chọn lớp học sẽ tham gia.");
//        } else if (this.view.trangThaiHvCbb.getValue() == null) {
//            this.view.statusLbl.setText("Vui lòng chọn trạng thái học viên.");
//        } else if (this.view.diaChiHvTxt.getText().isEmpty()) {
//            this.view.statusLbl.setText("Vui lòng nhập địa chỉ học viên!");
//        }
//        return 0;
//    }
//
//}
