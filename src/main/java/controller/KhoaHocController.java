package controller;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.HocVien;
import model.KhoaHoc;
import service.HocVienService;
import service.KhoaHocService;
import view.ChucNangConHV;
import view.ChucNangConKH;

import java.sql.SQLException;

public class KhoaHocController {
    KhoaHoc model;
    ChucNangConKH view;
    KhoaHocService khoaHocService;


    public KhoaHocController() {
        this.model = new KhoaHoc();
        this.view = new ChucNangConKH();
    }

    public KhoaHocController(KhoaHoc model, ChucNangConKH view) {
        this.model = model;
        this.view = view;
    }

    public void setUpdateDataUI() {
        String valueTitleForm = "Cập Nhật Khóa Học";
        String valueBtn = "Cập nhật";
        String valueOtherBtn = "Hủy";
        this.view.setUpdataDataUI(valueTitleForm, valueBtn, valueOtherBtn, model);
        this.view.show();
        clear();
    }

    public void loadAddData() {
        this.view.show();
        kiemTraMaKH();
        clear();
    }

    public void loadUpdateData() {
        this.view.show();
        handleUpdateKH();
        kiemTraMaKH();
        close();
    }

    public void handleAddKH() {
        khoaHocService = new KhoaHocService();
        this.view.addHvBtn.setOnAction(event -> {
            System.out.println("cÓ Nha.");
            if (checkForm() == 0) {
                this.view.getUpdateDataUI();
                int result = khoaHocService.insertKhoaHocMoi(this.view.model);
                if (result > 0) {
                    this.view.messageSuccess("Thành công", "Tạo khóa học mới thành công!");
                    this.view.statusLbl.setText("Khóa học mới đã được tạo thành công.");
                    this.view.statusLbl.setStyle("-fx-text-fill:green; -fx-font-size:16px");
                } else {
                    this.view.messageSuccess("Thất bại", "Khóa học mới chưa được tạo." +
                            "\nVui lòng kiểm tra lại.");
                    this.view.statusLbl.setText("Tạo khóa học mới không thành công.");
                    this.view.statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
                }
            }
        });
    }

    protected void handleUpdateKH() {
        this.view.addHvBtn.setOnAction(event -> {
            this.view.getUpdateDataUI();
            this.view.maKhTxt.isDisabled();

            khoaHocService = new KhoaHocService();
            if (checkForm() == 0) {
                int value = khoaHocService.updateKhoaHoc(this.view.model);

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


    protected void kiemTraMaKH() {
        this.view.maKhTxt.setOnKeyReleased(event -> {
            try {
                khoaHocService = new KhoaHocService();
                if (khoaHocService.kiemTraMaKhoaHoc(view.maKhTxt.getText()) > 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    this.view.messageWarning("Lỗi Mã Học Viên", "Mã HV này đã tồn tại. Vui lòng chọn một mã HV khác.");
                    this.view.maKhTxt.isFocused();
                } else {
                    System.out.println("Không hợp lệ.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
        khoaHocService = new KhoaHocService();
        int value = khoaHocService.deleteKhoaHoc(this.model.getMaKH());
        if (value != 0) {
            this.view.messageSuccess("Thành công", "Xóa Khóa học thành công.");
        } else {
            this.view.messageError("Thất bại", "Xóa Khóa học thất bại." +
                    "\nVui lòng kiểm tra lại. " +
                    "\nHoặc liên hệ: Mr.A" +
                    "\nSĐT:190067777");
        }
    }

    protected int checkForm() {
        this.view.statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
        this.view.statusLbl.setPadding(new Insets(15));
        this.view.statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra

        if (this.view.maKhTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Mã KH không được để trống.");
            this.view.maKhTxt.isFocused();
            return 1;
        } else if (this.view.tenKhTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Tên KH không được để trống.");
            this.view.tenKhTxt.isFocused();
            return 1;
        } else if (this.view.motaKhTextArea.getText().isEmpty()) {
            this.view.statusLbl.setText("Vui lòng thêm mô tả về khóa học.");
            this.view.motaKhTextArea.isFocused();
            return 1;
        } else if (this.view.thoiGianHocKhTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Vui lòng ghi rõ thời gian đào tạo của khóa học!");
            this.view.thoiGianHocKhTxt.isFocused();
            return 1;
        } else if (this.view.hocPhiKhTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Vui lòng ghi rõ học phí của khóa học");
            this.view.hocPhiKhTxt.isFocused();
            return 1;
        } else if (this.view.ngayBatDauKhDatePicker.getValue() == null) {
            this.view.statusLbl.setText("Ngày bắt đầu khóa học không được để trống!");
            this.view.ngayBatDauKhDatePicker.isFocused();
            return 1;
        } else if (this.view.ngayKetThucKhDatePicker.getValue() == null) {
            this.view.statusLbl.setText("Ngày kết thúc khóa học không được để trống!");
            this.view.ngayKetThucKhDatePicker.isFocused();
            return 1;
        } else if (this.view.soLuongHvToiDaTxt.getText().isEmpty()) {
            this.view.statusLbl.setText("Vui lòng ghi rõ số lượng học viên tối đa của khóa học.");
            this.view.soLuongHvToiDaTxt.isFocused();
            return 1;
        } else if (this.view.trangThaiKhComboBox.getValue() == null) {
            this.view.statusLbl.setText("Vui lòng chọn trạng thái của khóa học.");
            this.view.trangThaiKhComboBox.isFocused();
            return 1;
        }
        return 0;
    }

}
