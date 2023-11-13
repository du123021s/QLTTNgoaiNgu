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

import java.awt.*;
import java.time.LocalDate;

public class ChucNangConHV extends Application {
    private VBox chucNangHvVb;
    private TextField maHvTxt, tenHvTxt, gioiTinhHvTxt,  sdtHvTxt, emailHvTxt, trangThaiHvTxt;
    private TextArea diaChiHvTxt;
    private LocalDate ngaySinhHv ;
    private Button dangKyHvBtn;
    DatePicker ngaySinhHvDatePicker, ngayDangKyHvDatePicker;

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
        maHvTxt = new TextField();
        tenHvTxt = new TextField();
        sdtHvTxt = new TextField();
        emailHvTxt = new TextField();
        diaChiHvTxt = new TextArea();
        gioiTinhHvTxt = new TextField();
        trangThaiHvTxt = new TextField();

        ngaySinhHvDatePicker = new DatePicker();
        ngaySinhHvDatePicker.setPromptText("dd/MM/YYYY");
        ngaySinhHvDatePicker.valueProperty().addListener((observable, oldValue, newValue)-> {
            LocalDate localDate = LocalDate.now();
            if(newValue != null && newValue.isBefore(localDate)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Date");
                alert.setHeaderText("Registration date expired. Please enter a valid registration date.");
                alert.showAndWait();
                ngaySinhHvDatePicker.setValue(null);
            }else ngaySinhHv = newValue;
        });


        ngayDangKyHvDatePicker = new DatePicker();
        ngayDangKyHvDatePicker.setPromptText("dd/MM/YYYY");
        ngayDangKyHvDatePicker.valueProperty().addListener((observable, oldValue, newValue)-> {
            LocalDate localDate = LocalDate.now();
            if(newValue != null && newValue.isBefore(localDate)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Date");
                alert.setHeaderText("Registration date expired. Please enter a valid registration date.");
                alert.showAndWait();
                ngayDangKyHvDatePicker.setValue(null);
            }else ngaySinhHv = newValue;
        });


        Label sdtHvLbl = new Label("SĐT: "); sdtHvLbl.setStyle(font);
        Label maHvLbl = new Label("Mã HV: ");  maHvLbl.setStyle(font);
        Label emailHvLbl = new Label("Email"); emailHvLbl.setStyle(font);
        Label tenHvLbl = new Label("Họ Tên HV: "); tenHvLbl.setStyle(font);
        Label diaChiHvLbl = new Label("Địa chỉ: "); diaChiHvLbl.setStyle(font);
        Label ngaySinhHvLbl = new Label("Ngày Sinh"); ngaySinhHvLbl.setStyle(font);
        Label gioiTinhHvLbl = new Label("Giới Tính: "); gioiTinhHvLbl.setStyle(font);
        Label ngayDangKyHVLbl = new Label("Ngày ĐK: "); ngayDangKyHVLbl.setStyle(font);
        Label trangThaiHvLbl = new Label("Trạng thái: "); trangThaiHvLbl.setStyle(font);

        diaChiHvTxt.setPrefRowCount(7);
        diaChiHvTxt.setPrefColumnCount(5);

        maHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        sdtHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        emailHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        diaChiHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        ngaySinhHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        gioiTinhHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        ngayDangKyHVLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        trangThaiHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());

        // ===== Căn chỉnh - Align ---------


        maHvTxt.setMinHeight(size); maHvTxt.setMinWidth(sizeWidth);
        tenHvTxt.setMinHeight(size); tenHvTxt.setMinWidth(sizeWidth);
        gioiTinhHvTxt.setMinHeight(size); gioiTinhHvTxt.setMinWidth(sizeWidth);
        ngaySinhHvDatePicker.setMinHeight(size); ngaySinhHvDatePicker.setMinWidth(sizeWidth);
        emailHvTxt.setMinHeight(size); emailHvTxt.setMinWidth(sizeWidth);
        sdtHvTxt.setMinHeight(size); sdtHvTxt.setMinWidth(sizeWidth);
        diaChiHvTxt.setMinHeight(size); diaChiHvTxt.setMinWidth(sizeWidth);
        ngayDangKyHvDatePicker.setMinHeight(size); ngayDangKyHvDatePicker.setMinWidth(sizeWidth);
        trangThaiHvTxt.setMinHeight(size); trangThaiHvTxt.setMinWidth(sizeWidth);


        HBox maHvHb = new HBox();
        HBox tenHvHb = new HBox();
        HBox gioiTinhHvHb = new HBox();
        HBox ngaySinhHvHb = new HBox();
        HBox sdtHvHb = new HBox();
        HBox emailHvHb = new HBox();
        HBox diaChiHvHb = new HBox();
        HBox ngayDangKyHvHb = new HBox();
        HBox trangThaiHvHb = new HBox();

        VBox leftVb = new VBox(10);
        VBox rightVb = new VBox(10);
        leftVb.setPadding(new Insets(25,0,10,10));
        rightVb.setPadding(new Insets(25, 0, 10,50));

        maHvHb.getChildren().addAll(maHvLbl,  maHvTxt);
        tenHvHb.getChildren().addAll(tenHvLbl, tenHvTxt);
        sdtHvHb.getChildren().addAll(sdtHvLbl, sdtHvTxt);
        emailHvHb.getChildren().addAll(emailHvLbl, emailHvTxt);
        diaChiHvHb.getChildren().addAll(diaChiHvLbl, diaChiHvTxt);
        gioiTinhHvHb.getChildren().addAll(gioiTinhHvLbl, gioiTinhHvTxt);
        ngaySinhHvHb.getChildren().addAll(ngaySinhHvLbl, ngaySinhHvDatePicker);
        trangThaiHvHb.getChildren().addAll(trangThaiHvLbl, trangThaiHvTxt);
        ngayDangKyHvHb.getChildren().addAll(ngayDangKyHVLbl, ngayDangKyHvDatePicker);


        leftVb.getChildren().addAll(maHvHb, tenHvHb, gioiTinhHvHb, ngaySinhHvHb, sdtHvHb);
        rightVb.getChildren().addAll(emailHvHb,ngayDangKyHvHb, trangThaiHvHb, diaChiHvHb);



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

        Label titleChucNangLbl = new Label("Đăng Ký Học Viên Mới");
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

        if (maHvTxt.getText().isEmpty()) {
            statusLbl.setText("Mã HV không được để trống.");
        }else if (tenHvTxt.getText().isEmpty()) {
            statusLbl.setText("Tên HV không được để trống.");
        }else if (gioiTinhHvTxt.getText().isEmpty()) {
            statusLbl.setText("Giới tính không được để trống.");
        }else if (ngaySinhHvDatePicker.getValue() == null) {
            statusLbl.setText("Vui lòng lựa chọn ngày sinh.");
        }else if (sdtHvTxt.getText().isEmpty()) {
            statusLbl.setText("SĐT không được để trống.");
        }else if (emailHvTxt.getText().isEmpty()) {
            statusLbl.setText("Email không được để trống.");
        }else if (ngayDangKyHvDatePicker.getValue() == null) {
            statusLbl.setText("Ngày đăng ký không được để trống.");
        }else if (trangThaiHvTxt.getText().isEmpty()) {
            statusLbl.setText("Trạng thái không được để trống.");
        }
    }



}