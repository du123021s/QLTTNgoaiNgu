package controller;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.HocVien;
import service.HocVienService;
import view.ChucNangConHV;
import view.HocVienUI;

public class HocVienController {
    private HocVien model;
    private ChucNangConHV view;
    private HocVienService hocVienService;

    public HocVienController() {
        this.model = new HocVien();
        this.view = new ChucNangConHV();
        // Đăng ký lắng nghe sự kiện từ View
    }

    public HocVienController(HocVien model, ChucNangConHV view) {
        this.model = model;
        this.view = view;
    }

    public void setUpdateDataUI() {
        String valueTitleForm = "Cập Nhật Học Viên";
        String valueBtn = "Cập nhật";
        String valueOtherBtn = "Hủy";
        this.view.setUpdataDataUI(valueTitleForm, valueBtn, valueOtherBtn, model);
        this.view.show();
        clear();
    }

    public void loadAddData() {
        this.view.show();
        kiemTraMaHV();
        clear();
    }

    public void loadUpdateData() {
        this.view.show();
        handleUpdateHv();
        kiemTraMaHV();
        close();
    }

    public HocVien handleAddHv() {
        HocVien hocVienMoi = new HocVien();
        this.view.addHvBtn.setOnAction(event -> {
            System.out.println("cÓ Nha.");
            if (checkForm() == 0) {
                hocVienMoi.setMaLop(this.view.lopHoc);
                hocVienMoi.setNgaySinhHV(this.view.ngaySinhHv);
                hocVienMoi.setMaHV(this.view.maHvTxt.getText());
                hocVienMoi.setTrangThaiHV(this.view.trangThaiHv);
                hocVienMoi.setSdtHV(this.view.sdtHvTxt.getText());
                if ("Nữ".equals(this.view.selectedValue)) {
                    hocVienMoi.setGioiTinhHV("F");
                } else if ("Nam".equals(this.view.selectedValue)) {
                    hocVienMoi.setGioiTinhHV("M");
                }

                hocVienMoi.setHoTenHV(this.view.tenHvTxt.getText());
                hocVienMoi.setEmailHV(this.view.emailHvTxt.getText());
                hocVienMoi.setDiaChiHV(this.view.diaChiHvTxt.getText());

                HocVienService hocVienService = new HocVienService();
                int result = hocVienService.dangkyHocVienMoi(hocVienMoi);
                if (result > 0) {
                    this.view.messageSuccess("Thành công", "Đăng ký học viên mới thành công!");
                    this.view.statusLbl.setText("Học Viên mới đã được đăng ký thành công.");
                    this.view.statusLbl.setStyle("-fx-text-fill:green; -fx-font-size:16px");
                } else {
                    this.view.messageSuccess("Thất bại", "Đăng ký học viên mới không thành công. " +
                            "\nHọc viên này có thể đã đăng ký trước đó.");
                    this.view.statusLbl.setText("Đăng ký thất bại.");
                    this.view.statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
                }
            }
        });
        return hocVienMoi;
    }


    protected void handleUpdateHv() {
        this.view.addHvBtn.setOnAction(event -> {
            this.view.getUpdateDataUI();
            this.view.maHvTxt.isDisabled();

            hocVienService = new HocVienService();
            if (checkForm() == 0) {
                int value = hocVienService.updateHocVien(this.view.model);

                if (value != 0) {
                    this.view.messageSuccess("Thành công", "Cập nhật học viên thành công.");
                } else {
                    this.view.messageError("Thất bại", "Cập nhật học viên không thành công." +
                            "\nVui lòng kiểm tra lại. " +
                            "\nHoặc liên hệ: Mr.A" +
                            "\nSĐT:190067777");
                }
            }
        });
    }

    protected void kiemTraMaHV() {
        this.view.maHvTxt.setOnKeyReleased(event -> {
            HocVienService hocVienService = new HocVienService();
            if (hocVienService.kiemTraHocVien(view.maHvTxt.getText()) > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Lỗi Mã Học Viên");
                alert.setHeaderText("Mã HV này đã tồn tại. Vui lòng chọn một mã HV khác.");
                alert.showAndWait();
                this.view.maHvTxt.isFocused();
            } else {
                System.out.println("Không hợp lệ.");
            }
        });
    }

    protected void close() {
        this.view.clearBtn.setOnAction(event -> {
            Stage stage = (Stage) this.view.clearBtn.getScene().getWindow();
            stage.close();
        });
    }

    protected void clear() {
        this.view.clearBtn.setOnAction(event -> {
            this.view.clearData();
        });
    }

    public void delete() {
        hocVienService = new HocVienService();
        boolean value = hocVienService.deleteHocVien(this.model.getMaHV());
        if (value) {
            this.view.messageSuccess("Thành công", "Xóa học viên thành công.");
        } else {
            this.view.messageError("Thất bại", "Xóa học viên thất bại." +
                    "\nVui lòng kiểm tra lại. " +
                    "\nHoặc liên hệ: Mr.A" +
                    "\nSĐT:190067777");
        }
    }

    protected boolean isPhoneNumberValid(String phoneNumber) {
        // Sử dụng biểu thức chính quy để kiểm tra
        String phoneRegex = "^(\\+\\d{1,3})?\\d{10,12}$";
        return phoneNumber.matches(phoneRegex);
    }

    protected boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    protected int checkForm() {
        this.view.statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
        this.view.statusLbl.setPadding(new Insets(15));
        this.view.statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra

        if (this.view.maHvTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Mã HV không được để trống.");
            this.view.maHvTxt.isFocused();
            return 1;
        } else if (this.view.tenHvTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Tên HV không được để trống.");
            this.view.tenHvTxt.isFocused();
            return 1;
        } else if (this.view.toggleGroup.getSelectedToggle() == null) {
            this.view.statusLbl.setText("Vui lòng chọn giới tính!");
            return 1;
        } else if (this.view.ngaySinhHvDatePicker.getValue() == null) {
            this.view.statusLbl.setText("Vui lòng chọn ngày sinh!");
            this.view.ngaySinhHvDatePicker.isFocused();
            return 1;
        } else if (this.view.sdtHvTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("SĐT không được để trống.");
            this.view.sdtHvTxt.isFocused();
            return 1;
        } else if (!isPhoneNumberValid(this.view.sdtHvTxt.getText())) {
            this.view.statusLbl.setText("Số điện thoại không đúng định dạng!");
            this.view.sdtHvTxt.isFocused();
            return 1;
        } else if (this.view.emailHvTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Email không được để trống.");
            this.view.emailHvTxt.isFocused();
            return 1;
        } else if (!isEmailValid(this.view.emailHvTxt.getText())) {
            this.view.statusLbl.setText("Vui lòng nhập đúng định dạng Email!");
            this.view.emailHvTxt.isFocused();
            return 1;
        } else if (this.view.lopHocComboBox.getItems() == null) {
            this.view.statusLbl.setText("Vui lòng chọn lớp học sẽ tham gia.");
            this.view.lopHocComboBox.isFocused();
            return 1;
        } else if (this.view.trangThaiHvCbb.getValue() == null) {
            this.view.statusLbl.setText("Vui lòng chọn trạng thái học viên.");
            this.view.trangThaiHvCbb.isFocused();
            return 1;
        } else if (this.view.diaChiHvTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Vui lòng nhập địa chỉ học viên!");
            this.view.diaChiHvTxt.isFocused();
            return 1;
        }
        return 0;
    }
}
