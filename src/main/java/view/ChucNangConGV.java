package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ChucNangConGV extends Application {
    private VBox chucNangHvVb;
    public TextField maGvTxt, tenGvTxt, gioiTinhGvTxt,  sdtGvTxt, emailGvTxt, chuyennganhGvTxt,kinhnghiemgdGvTxt,matkhauGvTxt,avatarGvTxt, trangThaiGvTxt;
    public Button addHvBtn;
    public TextArea diaChiGvTxt;
    public LocalDate ngaySinhGv ;
    public Button dangKyGvBtn;
    public DatePicker ngaySinhGvDatePicker, ngayBatDauGvDatePicker,ngayKetThucGvDatePicker;
    public CheckBox iad;
    private String font = "-fx-font-size:16px; ";
    private Integer size = 32;
    private Integer sizeWidth = 199;
    private Label statusLbl = new Label();

    private String format = "-fx-background-color: #030063; -fx-text-fill: #FFFFFF;";
    private String bg_red = "-fx-text-fill:#FFFFFF; -fx-background-color: #E20825;";

    private String titleFormat = "-fx-background-color: #030063; -fx-text-fill: #FFFFFF; -fx-font-weight:800;-fx-font-size:20px; ";

    public static void main(String[] agrs){launch(); }
    @Override
    public void start(Stage stage) throws Exception {
        showWindow();
    }

    void showWindow(){
        maGvTxt = new TextField();
        tenGvTxt = new TextField();
        sdtGvTxt = new TextField();
        emailGvTxt = new TextField();
        diaChiGvTxt = new TextArea();
        gioiTinhGvTxt = new TextField();
        trangThaiGvTxt = new TextField();
        final PasswordField matkhauGvTxt = new PasswordField();
        chuyennganhGvTxt = new TextField();
        kinhnghiemgdGvTxt = new TextField();
        avatarGvTxt = new TextField();
        CheckBox iad = new CheckBox();

        ngaySinhGvDatePicker = new DatePicker();
        ngaySinhGvDatePicker.setPromptText("dd/MM/YYYY");
        ngaySinhGvDatePicker.valueProperty().addListener((observable, oldValue, newValue)-> {
            LocalDate localDate = LocalDate.now();
            if(newValue != null && newValue.isBefore(localDate)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Date");
                alert.setHeaderText("Registration date expired. Please enter a valid registration date.");
                alert.showAndWait();
                ngaySinhGvDatePicker.setValue(null);
            }else ngaySinhGv = newValue;
        });


        ngayBatDauGvDatePicker = new DatePicker();
        ngayBatDauGvDatePicker.setPromptText("dd/MM/YYYY");
        ngayBatDauGvDatePicker.valueProperty().addListener((observable, oldValue, newValue)-> {
            LocalDate localDate = LocalDate.now();
            if(newValue != null && newValue.isBefore(localDate)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Date");
                alert.setHeaderText("Registration date expired. Please enter a valid registration date.");
                alert.showAndWait();
                ngayBatDauGvDatePicker.setValue(null);
            }else ngaySinhGv = newValue;
        });

        ngayKetThucGvDatePicker = new DatePicker();
        ngayKetThucGvDatePicker.setPromptText("dd/MM/YYYY");
        ngayKetThucGvDatePicker.valueProperty().addListener((observable, oldValue, newValue)-> {
            LocalDate localDate = LocalDate.now();
            if(newValue != null && newValue.isBefore(localDate)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Date");
                alert.setHeaderText("Registration date expired. Please enter a valid registration date.");
                alert.showAndWait();
                ngayKetThucGvDatePicker.setValue(null);
            }else ngaySinhGv = newValue;
        });


        Label sdtGvLbl = new Label("SĐT: "); sdtGvLbl.setStyle(font);
        Label maGvLbl = new Label("Mã GV: ");  maGvLbl.setStyle(font);
        Label emailGvLbl = new Label("Email"); emailGvLbl.setStyle(font);
        Label tenGvLbl = new Label("Họ Tên GV: "); tenGvLbl.setStyle(font);
        Label diaChiGvLbl = new Label("Địa chỉ: "); diaChiGvLbl.setStyle(font);
        Label ngaySinhGvLbl = new Label("Ngày Sinh"); ngaySinhGvLbl.setStyle(font);
        Label gioiTinhGvLbl = new Label("Giới Tính: "); gioiTinhGvLbl.setStyle(font);
        Label ngayBatDauGVLbl = new Label("Ngày BĐ: "); ngayBatDauGVLbl.setStyle(font);
        Label ngayKetThucGVLbl = new Label("Ngày KT: ");ngayKetThucGVLbl.setStyle(font);
        Label trangThaiGvLbl = new Label("Trạng thái: "); trangThaiGvLbl.setStyle(font);
        Label matKhauGvLbl = new Label("Mật Khẩu: "); matKhauGvLbl.setStyle(font);
        Label chuyennganhGvLbl = new Label("Ngành: "); chuyennganhGvLbl.setStyle(font);
        Label kinhnghiemgdGvLbl = new Label("Kinh Nghiệm: "); kinhnghiemgdGvLbl.setStyle(font);
        Label avatarGvLbl = new Label("Avatar: "); avatarGvLbl.setStyle(font);
        Label iadGvLbl = new Label("Admin: "); iadGvLbl.setStyle(font);

        diaChiGvTxt.setPrefRowCount(7);
        diaChiGvTxt.setPrefColumnCount(5);

        maGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        sdtGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        emailGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        diaChiGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        ngaySinhGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        gioiTinhGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        ngayBatDauGVLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        ngayKetThucGVLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        trangThaiGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        matKhauGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        chuyennganhGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        kinhnghiemgdGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        avatarGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        iadGvLbl.prefWidthProperty().bind(tenGvLbl.widthProperty());
        // ===== Căn chỉnh - Align ---------


        maGvTxt.setMinHeight(size); maGvTxt.setMinWidth(sizeWidth);
        tenGvTxt.setMinHeight(size); tenGvTxt.setMinWidth(sizeWidth);
        gioiTinhGvTxt.setMinHeight(size); gioiTinhGvTxt.setMinWidth(sizeWidth);
        ngaySinhGvDatePicker.setMinHeight(size); ngaySinhGvDatePicker.setMinWidth(sizeWidth);
        emailGvTxt.setMinHeight(size); emailGvTxt.setMinWidth(sizeWidth);
        sdtGvTxt.setMinHeight(size); sdtGvTxt.setMinWidth(sizeWidth);
        diaChiGvTxt.setMinHeight(size); diaChiGvTxt.setMinWidth(sizeWidth);
        ngayBatDauGvDatePicker.setMinHeight(size); ngayBatDauGvDatePicker.setMinWidth(sizeWidth);
        ngayKetThucGvDatePicker.setMinHeight(size); ngayKetThucGvDatePicker.setMinWidth(sizeWidth);
        trangThaiGvTxt.setMinHeight(size); trangThaiGvTxt.setMinWidth(sizeWidth);
        matkhauGvTxt.setMinHeight(size); matkhauGvTxt.setMinWidth(sizeWidth);
        chuyennganhGvTxt.setMinHeight(size);chuyennganhGvTxt.setMinWidth(sizeWidth);
        kinhnghiemgdGvTxt.setMinHeight(size);kinhnghiemgdGvTxt.setMinWidth(sizeWidth);
        avatarGvTxt.setMinHeight(size);kinhnghiemgdGvTxt.setMinWidth(sizeWidth);


        HBox maGvHb = new HBox();
        HBox tenGvHb = new HBox();
        HBox gioiTinhGvHb = new HBox();
        HBox ngaySinhGvHb = new HBox();
        HBox sdtGvHb = new HBox();
        HBox emailGvHb = new HBox();
        HBox diaChiGvHb = new HBox();
        HBox ngayBatDauGvHb = new HBox();
        HBox ngayKetThucGvHb = new HBox();
        HBox trangThaiGvHb = new HBox();
        HBox matKhauGvHb = new HBox();
        HBox chuyennganhGvHb = new HBox();
        HBox kinhnghiemGvHb = new HBox();
        HBox avatarGvHb = new HBox();
        HBox iadGvHb = new HBox();

        VBox leftVb = new VBox(10);
        VBox rightVb = new VBox(10);
        leftVb.setPadding(new Insets(25,0,10,10));
        rightVb.setPadding(new Insets(25, 0, 10,50));

        maGvHb.getChildren().addAll(maGvLbl,  maGvTxt);
        tenGvHb.getChildren().addAll(tenGvLbl, tenGvTxt);
        sdtGvHb.getChildren().addAll(sdtGvLbl, sdtGvTxt);
        emailGvHb.getChildren().addAll(emailGvLbl, emailGvTxt);
        diaChiGvHb.getChildren().addAll(diaChiGvLbl, diaChiGvTxt);
        gioiTinhGvHb.getChildren().addAll(gioiTinhGvLbl, gioiTinhGvTxt);
        ngaySinhGvHb.getChildren().addAll(ngaySinhGvLbl, ngaySinhGvDatePicker);
        trangThaiGvHb.getChildren().addAll(trangThaiGvLbl, trangThaiGvTxt);
        ngayBatDauGvHb.getChildren().addAll(ngayBatDauGVLbl, ngayBatDauGvDatePicker);
        ngayKetThucGvHb.getChildren().addAll(ngayKetThucGVLbl, ngayKetThucGvDatePicker);
        matKhauGvHb.getChildren().addAll(matKhauGvLbl, matkhauGvTxt);
        chuyennganhGvHb.getChildren().addAll(chuyennganhGvLbl, chuyennganhGvTxt);
        kinhnghiemGvHb.getChildren().addAll(kinhnghiemgdGvLbl, kinhnghiemgdGvTxt);
        avatarGvHb.getChildren().addAll(avatarGvLbl);
        iadGvHb.getChildren().addAll(iadGvLbl, iad);

        leftVb.getChildren().addAll(maGvHb,matKhauGvHb, tenGvHb, gioiTinhGvHb, ngaySinhGvHb, sdtGvHb,kinhnghiemGvHb);
        rightVb.getChildren().addAll(emailGvHb,ngayBatDauGvHb, ngayKetThucGvHb, trangThaiGvHb,chuyennganhGvHb, diaChiGvHb, iadGvHb);



        Button addHvBtn = new Button("Lưu"); addHvBtn.setOnAction(event -> handleAddHv());
        Button cancelBtn = new Button("Hủy"); cancelBtn.setOnAction(event -> handleCancel());
        addHvBtn.setStyle(format + font);
        cancelBtn.setStyle(bg_red + font);

        HBox btnHb = new HBox(30);
        btnHb.setAlignment(Pos.CENTER_RIGHT);
        btnHb.setPadding(new Insets(45, 50, 200,50));
        btnHb.getChildren().addAll(addHvBtn, cancelBtn);

        HBox.setHgrow(addHvBtn, Priority.ALWAYS);
        HBox.setHgrow(cancelBtn, Priority.ALWAYS);
        addHvBtn.setMaxWidth(Double.MAX_VALUE);
        cancelBtn.setMaxWidth(Double.MAX_VALUE);

        HBox contentHb = new HBox();
        contentHb.getChildren().addAll(leftVb, rightVb);

        Label titleChucNangLbl = new Label("Đăng Ký Giảng Viên Mới");
        titleChucNangLbl.setStyle(titleFormat);
        titleChucNangLbl.setMinHeight(45);
        titleChucNangLbl.setAlignment(Pos.CENTER);
        titleChucNangLbl.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        statusLbl.setMaxWidth(Double.MAX_VALUE);
        statusLbl.setWrapText(true);


        chucNangHvVb = new VBox();
        titleChucNangLbl.prefWidthProperty().bind(chucNangHvVb.widthProperty());
        chucNangHvVb.getChildren().addAll(titleChucNangLbl, contentHb, statusLbl, btnHb);
        chucNangHvVb.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        Scene scene = new Scene(chucNangHvVb,677,450);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    protected void handleCancel() {
    }

    protected void handleAddHv() {
        System.out.println("cÓ Nha.");
        statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
        statusLbl.setPadding(new Insets(15));
        statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra

        if (maGvTxt.getText().isEmpty()) {
            statusLbl.setText("Mã GV không được để trống.");
        }else if (tenGvTxt.getText().isEmpty()) {
            statusLbl.setText("Tên GV không được để trống.");
        }else if (matkhauGvTxt.getText().isEmpty()) {
                statusLbl.setText("Mật Khẩu GV không được để trống.");
        }else if (gioiTinhGvTxt.getText().isEmpty()) {
            statusLbl.setText("Giới tính không được để trống.");
        }else if (ngaySinhGvDatePicker.getValue() == null) {
            statusLbl.setText("Vui lòng lựa chọn ngày sinh.");
        }else if (sdtGvTxt.getText().isEmpty()) {
            statusLbl.setText("SĐT không được để trống.");
        }else if (emailGvTxt.getText().isEmpty()) {
            statusLbl.setText("Email không được để trống.");
        }else if (ngayBatDauGvDatePicker.getValue() == null) {
            statusLbl.setText("Ngày Bắt đầu không được để trống.");

        }else if (chuyennganhGvTxt.getText() == null) {
            statusLbl.setText("Chuyên Ngành không được để trống.");
        }else if (kinhnghiemgdGvTxt.getText() == null) {
                statusLbl.setText("Kinh nghiệm không được để trống.");
        }else if (trangThaiGvTxt.getText().isEmpty()) {
            statusLbl.setText("Trạng thái không được để trống.");
        }
    }



}