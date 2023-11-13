//package controller;
//
//
//
//import model.GiangVien;
//import view.ChucNangConGV;
//
//import javafx.geometry.Insets;
//import javafx.scene.control.Alert;
//import javafx.stage.Stage;
//
//
//import service.GiangVienService;
//import view.GiangVienUI;
//
//import static com.sun.beans.introspect.ClassInfo.clear;
//
//public class GiangVienController  {
//    private GiangVien model;
//    private ChucNangConGV view;
//    private GiangVienService giangVienService;
//
//    public GiangVienController{
//
//
//        public  GiangVienController(){
//            this.model = new GiangVien();
//            this.view = new ChucNangConGV();
//
//        }
//
//        public GiangVienController(GiangVien model, ChucNangConGV view){
//            this.model = model;
//            this.view = view;
//        }
//
//        public void setUpdateDataUI(){
//            String valueTitleForm = "Cập Nhật Giảng Viên";
//            String valueBtn = "Cập Nhật";
//            String valueOtherBtn = "Hủy";
//            this.view.setUpdateDataUI(valueTitleForm , valueBtn, valueOtherBtn, model);
//            this,view.show();
//            clear();
//        }
//
//        public void loadAddData(){
//            this.view.show();
//            kiemTraMaGV();
//            clear();
//        }
//        public void loadUpdateData(){
//            this.view.show();
//            handleUpdateGV();
//            kiemTraMaGV();
//            close();
//        }
//        public GiangVien handleAddGv() {
//            GiangVien giangVienMoi = new GiangVien();
//            this.view.addHvBtn.setOnAction(event -> {
//                System.out.println("cÓ Nha.");
//                if (checkForm() == 0) {
//                    giangVienMoi.setHoten(this.view.tenGvTxt);
//                    giangVienMoi.setNgaysinh(this.view.ngaySinhGv);
//                    giangVienMoi.setMaGiangVien(this.view.maGvTxt.getText());
//                    giangVienMoi.setTrangthai(this.view.trangThaiGvTxt);
//                    giangVienMoi.setSdt(this.view.sdtGvTxt.getText());
//                    if ("Nữ".equals(this.view.gioiTinhGvTxt)) {
//                       giangVienMoi.setGioitinh("F");
//                    } else if ("Nam".equals(this.view.gioiTinhGvTxt)) {
//                        giangVienMoi.setGioitinh("M");
//                    }
//
//                    giangVienMoi.setDiachi(this.view.diaChiGvTxt.getText());
//                    giangVienMoi.setEmail(this.view.emailGvTxt.getText());
//                    giangVienMoi.setChuyennganh(this.view.chuyennganhGvTxt.getText());
//                    giangVienMoi.setKinhnghiemgd(this.view.chuyennganhGvTxt.getText());
//                    giangVienMoi.setMatkhau(this.view.matkhauGvTxt.getText());
//                    giangVienMoi.setNgaybatdau(this.view.ngayBatDauGvDatePicker.getValue());
//                    giangVienMoi.setNgayketthuc(this.view.ngayKetThucGvDatePicker.getValue());
//                    giangVienMoi.setAvatar(this.view.avatarGvTxt.getText());
//                    giangVienMoi.setIs_admin(this.view.iad.getText());
//                    giangVienMoi.setTrangthai(this.view.trangThaiGvTxt.getText());
//
//
//
//                    GiangVienService giangVienService = new GiangVienService();
//                    int result = giangVienService.dangKyGiangVienMoi(giangVienMoi);
//                    if (result > 0) {
//                        this.view.messageSuccess("Thành công", "Đăng ký học viên mới thành công!");
//                        this.view.statusLbl.setText("Học Viên mới đã được đăng ký thành công.");
//                        this.view.statusLbl.setStyle("-fx-text-fill:green; -fx-font-size:16px");
//                    } else {
//                        this.view.messageSuccess("Thất bại", "Đăng ký học viên mới không thành công. " +
//                                "\nHọc viên này có thể đã đăng ký trước đó.");
//                        this.view.statusLbl.setText("Đăng ký thất bại.");
//                        this.view.statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
//                    }
//                }
//            });
//            return giangVienMoi;
//        }
//        protected void kiemTraMaGV() {
//            this.view.maGvTxt.setOnKeyReleased(event -> {
//                GiangVienService giangVienService = new GiangVienService();
//                if (giangVienService.kiemTraGiangVien(view.maGvTxt.getText()) > 0) {
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("Lỗi Mã Học Viên");
//                    alert.setHeaderText("Mã HV này đã tồn tại. Vui lòng chọn một mã HV khác.");
//                    alert.showAndWait();
//                    this.view.maGvTxt.isFocused();
//                } else {
//                    System.out.println("Không hợp lệ.");
//                }
//            });
//        }
//        protected void close() {
//            this.view.clearBtn.setOnAction(event -> {
//                Stage stage = (Stage) this.view.clearBtn.getScene().getWindow();
//                stage.close();
//            });
//        }
//
//    }
//
//
//}
