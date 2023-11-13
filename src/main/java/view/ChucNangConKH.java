package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.KhoaHoc;
import service.KhoaHocService;

import java.time.LocalDate;

public class ChucNangConKH {
    private Stage stage;
    public KhoaHoc model;
    private VBox chucNangHvVb;
    public Label statusLbl;
    public LocalDate valueNgayBatDauKh, valueNgayKetThucKh;
    private KhoaHocService khoaHocService;

    // -------------------------------------------------
    public int size = 32;
    public int sizeWidth = 199;
    public String font = "-fx-font-size:16px; ";
    public String format = "-fx-background-color: #030063; -fx-text-fill: #FFFFFF;";
    public String bg_red = "-fx-text-fill:#FFFFFF; -fx-background-color:#B1008C;";
    public String titleFormat = "-fx-background-color:#030063; -fx-text-fill:#FFFFFF;-fx-font-weight:800;-fx-font-size:20px; ";
    public String btnEffect1 = "-fx-background-color:#0000B4;-fx-text-fill:#FFFFFF;";
    public String btnEffect2 = "-fx-background-color:#C80475;-fx-text-fill:#FFFFFF;";

    public Label titleChucNangLbl;
    public Button addHvBtn, clearBtn;
    public TextArea motaKhTextArea;
    public TextField maKhTxt, tenKhTxt,thoiGianHocKhTxt, hocPhiKhTxt, soLuongHvToiDaTxt;
    public DatePicker ngayBatDauKhDatePicker, ngayKetThucKhDatePicker;
    public ComboBox<String> trangThaiKhComboBox;

    public String trangThaiKh;
    public String titleForm = "Tạo Khóa Học";
    public String valueAdd = "Lưu";
    public String valueClear = "Làm sạch";
    public String promtTxtTrangThaiCbb = "Chọn trạng thái KH";

    Label maKhLbl, tenKhLbl, motaKhLbl, thoiGianHocKhLbl, hocPhiKhLbl, ngayBatDauKhLbl,
            ngayKetThucKhLbl, soLuongHvToiDaKhLbl, trangThaiKhLbl;

    public ChucNangConKH() {
        this.stage = stage;
        this.model = new KhoaHoc();

        titleChucNangLbl = new Label(titleForm);

        maKhTxt = new TextField();
        tenKhTxt = new TextField();
        motaKhTextArea = new TextArea();
        thoiGianHocKhTxt = new TextField();
        hocPhiKhTxt = new TextField();
        ngayBatDauKhDatePicker = new DatePicker();
        ngayKetThucKhDatePicker = new DatePicker();
        soLuongHvToiDaTxt = new TextField();
        trangThaiKhComboBox = new ComboBox<>();

        maKhLbl = new Label("Mã KH:"); maKhLbl.setStyle(font);
        tenKhLbl = new Label("Tên KH:"); tenKhLbl.setStyle(font);
        motaKhLbl = new Label("Mô Tả: "); motaKhLbl.setStyle(font);
        thoiGianHocKhLbl = new Label("Thời Gian Học (Tháng):"); thoiGianHocKhLbl.setStyle(font);
        hocPhiKhLbl = new Label("Học Phí: "); hocPhiKhLbl.setStyle(font);
        ngayBatDauKhLbl = new Label("Bắt Đầu Ngày: "); ngayBatDauKhLbl.setStyle(font);
        ngayKetThucKhLbl = new Label("Kết Thúc Ngày: "); ngayKetThucKhLbl.setStyle(font);
        soLuongHvToiDaKhLbl = new Label("Số lượng HV: "); soLuongHvToiDaKhLbl.setStyle(font);
        trangThaiKhLbl = new Label("Trạng Thái: "); trangThaiKhLbl.setStyle(font);

        /**
         * Set status of Course - Course ComboBox
         */
        trangThaiKhComboBox.setPromptText(promtTxtTrangThaiCbb);
        trangThaiKhComboBox.getItems().addAll("Hoạt động","Kết thúc");
        trangThaiKhComboBox.setOnAction(event -> {
            trangThaiKh = trangThaiKhComboBox.getValue();
        });
        trangThaiKhComboBox.setStyle(font);


        maKhLbl.prefWidthProperty().bind(thoiGianHocKhLbl.widthProperty());
        tenKhLbl.prefWidthProperty().bind(thoiGianHocKhLbl.widthProperty());
        motaKhLbl.prefWidthProperty().bind(thoiGianHocKhLbl.widthProperty());
        hocPhiKhLbl.prefWidthProperty().bind(thoiGianHocKhLbl.widthProperty());
        ngayBatDauKhLbl.prefWidthProperty().bind(thoiGianHocKhLbl.widthProperty());
        ngayKetThucKhLbl.prefWidthProperty().bind(thoiGianHocKhLbl.widthProperty());
        soLuongHvToiDaKhLbl.prefWidthProperty().bind(thoiGianHocKhLbl.widthProperty());
        trangThaiKhLbl.prefWidthProperty().bind(thoiGianHocKhLbl.widthProperty());

        // ===== Align - font - size ---------
        motaKhTextArea.setPrefColumnCount(7);
        motaKhTextArea.setPrefRowCount(7);

        maKhTxt.setMinHeight(size); maKhTxt.setMinWidth(sizeWidth);
        tenKhTxt.setMinHeight(size); tenKhTxt.setMinWidth(sizeWidth);
        motaKhTextArea.setMinWidth(sizeWidth);
        thoiGianHocKhTxt.setMinHeight(size); thoiGianHocKhTxt.setMinWidth(sizeWidth);
        hocPhiKhTxt.setMinHeight(size); hocPhiKhTxt.setMinHeight(size);
        ngayBatDauKhDatePicker.setMinHeight(size); ngayBatDauKhDatePicker.setMinWidth(sizeWidth);
        ngayKetThucKhDatePicker.setMinHeight(size); ngayKetThucKhDatePicker.setMinWidth(sizeWidth);
        soLuongHvToiDaTxt.setMinHeight(size); soLuongHvToiDaTxt.setMinWidth(sizeWidth);
        trangThaiKhComboBox.setMinHeight(size); trangThaiKhComboBox.setMinWidth(sizeWidth);


        // === DatePicker - ngaySinh
        ngayBatDauKhDatePicker.setPromptText("dd/MM/YYYY");
        ngayBatDauKhDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                statusLbl.setText("Vui lòng lựa chọn ngày bắt đầu khóa học.");
            } else {
                valueNgayBatDauKh = newValue;
            }
        });

        ngayKetThucKhDatePicker.setPromptText("dd/MM/YYYY");
        ngayKetThucKhDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                statusLbl.setText("Vui lòng lựa chọn ngày kết thúc khóa học.");
            } else {
                valueNgayKetThucKh = newValue;
            }
        });


        // === HBOX ------------------------------
        HBox maKhHb = new HBox();
        HBox tenKhHb = new HBox();
        HBox motaKhHb = new HBox();
        HBox thoiGianHocKhHb = new HBox();
        HBox hocPhiKhHb = new HBox();
        HBox ngayBatDauKhHb = new HBox();
        HBox ngayKetThucKhHb = new HBox();
        HBox soLuongKhHb = new HBox();
        HBox trangThaiKhHb = new HBox();

        maKhHb.getChildren().addAll(maKhLbl, maKhTxt);
        tenKhHb.getChildren().addAll(tenKhLbl, tenKhTxt);
        motaKhHb.getChildren().addAll(motaKhLbl, motaKhTextArea);
        thoiGianHocKhHb.getChildren().addAll(thoiGianHocKhLbl, thoiGianHocKhTxt);
        hocPhiKhHb.getChildren().addAll(hocPhiKhLbl, hocPhiKhTxt);
        ngayBatDauKhHb.getChildren().addAll(ngayBatDauKhLbl, ngayBatDauKhDatePicker);
        ngayKetThucKhHb.getChildren().addAll(ngayKetThucKhLbl, ngayKetThucKhDatePicker);
        soLuongKhHb.getChildren().addAll(soLuongHvToiDaKhLbl, soLuongHvToiDaTxt);
        trangThaiKhHb.getChildren().addAll(trangThaiKhLbl, trangThaiKhComboBox);

        // === B1. VBox ---------------------
        VBox leftVb = new VBox(10);
        VBox rightVb = new VBox(10);
        leftVb.setPadding(new Insets(25, 0, 10, 10));
        rightVb.setPadding(new Insets(25, 0, 10, 50));

        leftVb.getChildren().addAll(maKhHb, tenKhHb, motaKhHb, thoiGianHocKhHb, hocPhiKhHb);
        rightVb.getChildren().addAll(ngayBatDauKhHb, ngayKetThucKhHb, soLuongKhHb, trangThaiKhHb);

        // === B2. Set VBox into HBox
        HBox contentHb = new HBox();
        contentHb.getChildren().addAll(leftVb, rightVb);
        // -------------------------------------------------------------------------------------


        // === B1. Button --------------------------------------------
        addHvBtn = new Button(valueAdd);
        clearBtn = new Button(valueClear);
        addHvBtn.setStyle(format + font);
        clearBtn.setStyle(bg_red + font);

        // === B2. Set Button into HBox
        HBox btnHb = new HBox(30);
        btnHb.setAlignment(Pos.CENTER_RIGHT);
        btnHb.setPadding(new Insets(45, 50, 200, 50));
        btnHb.getChildren().addAll(addHvBtn, clearBtn);

        HBox.setHgrow(addHvBtn, Priority.ALWAYS);
        HBox.setHgrow(clearBtn, Priority.ALWAYS);
        addHvBtn.setMaxWidth(Double.MAX_VALUE);
        clearBtn.setMaxWidth(Double.MAX_VALUE);
        // ----------------------------------------------------------------------------------------


        // === Set title for interface --------------
        titleChucNangLbl.setStyle(titleFormat);
        titleChucNangLbl.setMinHeight(45);
        titleChucNangLbl.setAlignment(Pos.CENTER);
        titleChucNangLbl.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        // ---------------------------------------------------------------------------------------


        // === Error Message --------------------
        statusLbl = new Label();
        statusLbl.setMaxWidth(Double.MAX_VALUE);
        statusLbl.setWrapText(true);
        // ---------------------------------------------------------------------------------------

        // =============== Root layout -------------------
        chucNangHvVb = new VBox();
        chucNangHvVb.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        chucNangHvVb.getChildren().addAll(titleChucNangLbl, contentHb, statusLbl, btnHb);
        // ----------------------------------------------------------------------------------------

        // === Set title equal root layout
        titleChucNangLbl.prefWidthProperty().bind(chucNangHvVb.widthProperty());
        // ----------------------------------------------------------------------------------------

        // === Window ------------------------------------------------------------------------------
        Scene scene = new Scene(chucNangHvVb, 677, 450);
        stage = new Stage();
        stage.setScene(scene);
    }

    public void show() {
        changeColorBtn();
        stage.show();
    }


    public void getUpdateDataUI() {
        this.model.setMaKH(this.maKhTxt.getText());
        this.model.setTenKH(this.tenKhTxt.getText());
        this.model.setThoiGianHocKH(Integer.valueOf(this.thoiGianHocKhTxt.getText()));
        this.model.setHocPhiKH(Double.valueOf(this.hocPhiKhTxt.getText()));
        this.model.setNgayKetThucKH(this.valueNgayBatDauKh);
        this.model.setNgayBatDauKH(this.valueNgayBatDauKh);
        this.model.setSoLuongHVToiDa(Integer.valueOf(this.soLuongHvToiDaTxt.getText()));
        if (this.trangThaiKhComboBox.getValue() == null) {
            this.model.setTrangThaiKH(promtTxtTrangThaiCbb);
        } else {
            this.model.setTrangThaiKH(this.trangThaiKhComboBox.getValue());
        }
    }

    public void setUpdataDataUI(String titleForm, String newValueAddBtn, String newValueCancelBtn, KhoaHoc khoaHoc) {
        this.titleChucNangLbl.setText(titleForm);
        this.addHvBtn.setText(newValueAddBtn);
        this.clearBtn.setText(newValueCancelBtn);
        this.maKhTxt.setText(khoaHoc.getMaKH());
        this.tenKhTxt.setText(khoaHoc.getTenKH());
        this.motaKhTextArea.setText(khoaHoc.getMotaKH());
        this.thoiGianHocKhTxt.setText(khoaHoc.getThoiGianHocKH()+"");
        this.hocPhiKhTxt.setText(khoaHoc.getHocPhiKH()+"");
        this.ngayBatDauKhDatePicker.setValue(khoaHoc.getNgayBatDauKH());
        this.ngayKetThucKhDatePicker.setValue(khoaHoc.getNgayKetThucKH());
        this.soLuongHvToiDaTxt.setText(khoaHoc.getSoLuongHVToiDa()+"");
        this.trangThaiKhComboBox.setPromptText(khoaHoc.getTrangThaiKH());
    }

    public String setPromtTextDefault(){
        return "Chọn trạng thái KH";
    }
    public void clearData() {
        this.maKhTxt.clear();
        this.tenKhTxt.clear();
        this.motaKhTextArea.clear();
        this.thoiGianHocKhTxt.clear();
        this.hocPhiKhTxt.clear();
        this.ngayBatDauKhDatePicker.setValue(null);
        this.ngayKetThucKhDatePicker.setValue(null);
        this.soLuongHvToiDaTxt.clear();
        this.trangThaiKhComboBox.setPromptText(promtTxtTrangThaiCbb);
    }

    public void messageSuccess(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void messageError(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void messageWarning(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    protected void changeColorBtn() {
        this.addHvBtn.setOnMouseEntered((event) -> {
            this.addHvBtn.setStyle(this.btnEffect1 + this.font);
        });

        this.addHvBtn.setOnMouseExited((event) -> {
            this.addHvBtn.setStyle(this.format + this.font);
        });

        this.clearBtn.setOnMouseEntered((event) -> {
            this.clearBtn.setStyle(this.btnEffect2 + this.font);
        });

        this.clearBtn.setOnMouseExited((event) -> {
            this.clearBtn.setStyle(this.bg_red + this.font);
        });
    }
}

