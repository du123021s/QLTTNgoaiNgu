package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.HocVien;
import model.LopHoc;
import service.HocVienService;
import service.LopHocService;
import java.time.LocalDate;

public class ChucNangConHV{
    private Stage stage;
    public HocVien model;
    public LopHoc lopHoc;
    private VBox chucNangHvVb;
    public Label statusLbl;
    public LocalDate ngaySinhHv;
    private HocVienService hocVienService;

    // -------------------------------------------------
    public Integer size = 32;
    public Integer sizeWidth = 199;
    public String font = "-fx-font-size:16px; ";
    public String format = "-fx-background-color: #030063; -fx-text-fill: #FFFFFF;";
    public String bg_red = "-fx-text-fill:#FFFFFF; -fx-background-color:#B1008C;";
    public String titleFormat = "-fx-background-color:#030063; -fx-text-fill:#FFFFFF;-fx-font-weight:800;-fx-font-size:20px; ";
    public String btnEffect1 = "-fx-background-color:#0000B4;-fx-text-fill:#FFFFFF;";
    public String btnEffect2 = "-fx-background-color:#C80475;-fx-text-fill:#FFFFFF;";

    public Label titleChucNangLbl;
    public Button addHvBtn, clearBtn ;
    public TextField maHvTxt, tenHvTxt, sdtHvTxt, emailHvTxt;
    public TextArea diaChiHvTxt;
    public ToggleGroup toggleGroup ;
    public DatePicker ngaySinhHvDatePicker;
    public RadioButton maleRadioBtn;
    public RadioButton femaleRadioBtn;
    public ComboBox<LopHoc> lopHocComboBox;
    public ComboBox<String> trangThaiHvCbb;

    public String trangThaiHv;
    public String selectedValue;
    public String valueAdd = "Lưu";
    public String valueClear = "Làm sạch";
    public String valueLopHocCbb = "Chọn lớp";
    public String valueTrangThaiHvCbb = "Chọn trạng thái";

    Label maLopLbl, sdtHvLbl, maHvLbl, emailHvLbl, tenHvLbl, diaChiHvLbl,
                                            ngaySinhHvLbl, gioiTinhHvLbl;

    public ChucNangConHV(){
        this.stage = stage;
        this.model = new HocVien();
        this.lopHoc = new LopHoc();

        titleChucNangLbl = new Label("Đăng Ký Học Viên Mới");

        maHvTxt = new TextField();
        tenHvTxt = new TextField();
        sdtHvTxt = new TextField();
        emailHvTxt = new TextField();
        diaChiHvTxt = new TextArea();
        trangThaiHvCbb = new ComboBox<>();
        lopHocComboBox = new ComboBox<>();
        ngaySinhHvDatePicker = new DatePicker();
        maleRadioBtn = new RadioButton("Nam");
        femaleRadioBtn = new RadioButton("Nữ");


        toggleGroup = new ToggleGroup();
        System.out.println("Radio Button: " + maleRadioBtn.getText() );
        femaleRadioBtn.setToggleGroup(toggleGroup);
        maleRadioBtn.setToggleGroup(toggleGroup);

        System.out.println("Radio Button 2: " + maleRadioBtn.getText() );
        maLopLbl = new Label("Chọn Lớp: "); maLopLbl.setStyle(font);
        sdtHvLbl = new Label("SĐT: "); sdtHvLbl.setStyle(font);
        maHvLbl = new Label("Mã HV: ");  maHvLbl.setStyle(font);
        emailHvLbl = new Label("Email: "); emailHvLbl.setStyle(font);
        tenHvLbl = new Label("Họ Tên HV: "); tenHvLbl.setStyle(font);
        diaChiHvLbl = new Label("Địa Chỉ: "); diaChiHvLbl.setStyle(font);
        ngaySinhHvLbl = new Label("Ngày Sinh"); ngaySinhHvLbl.setStyle(font);
        gioiTinhHvLbl = new Label("Giới Tính: "); gioiTinhHvLbl.setStyle(font);
        Label trangThaiHvLbl = new Label("Trạng Thái: "); trangThaiHvLbl.setStyle(font);

        // === ComboBox - LopHoc
        LopHocService lopHocService = new LopHocService();
        lopHocComboBox.getItems().addAll(lopHocService.hienThiDanhSachLop());
        if(lopHocService.hienThiDanhSachLop() != null){
            System.out.println("Co nha: " + lopHocService.hienThiDanhSachLop());
        }else{
            System.out.println("LOP CBB null");
        }
        lopHocComboBox.setPromptText(valueLopHocCbb);
        lopHocComboBox.setOnAction((et)->{
            lopHoc = lopHocComboBox.getValue();
            System.out.println("LOP HOC: " + lopHoc.getMaLop() + " , " + lopHoc.getDsHocVien());
        });
        lopHocComboBox.setStyle(font);
        lopHocComboBox.setConverter(new StringConverter<LopHoc>() {
            @Override
            public String toString(LopHoc lopHoc) {
                return lopHoc.toString();
            }
            @Override
            public LopHoc fromString(String string) {
                // Nếu bạn muốn chuyển đổi từ chuỗi ngược lại thành đối tượng lớp học, bạn có thể triển khai phương thức này.
                return null;
            }
        });

        /*
         * Set status of student - HocVien ComboBox
         */
        trangThaiHvCbb.setPromptText(valueTrangThaiHvCbb);
        trangThaiHvCbb.getItems().addAll("Hoạt động", "Tạm nghỉ", "Chưa đóng học phí");
        trangThaiHvCbb.setOnAction(event -> {
            trangThaiHv = trangThaiHvCbb.getValue();
        });
        trangThaiHvCbb.setStyle(font);


        maHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        sdtHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        maLopLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        emailHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        diaChiHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        ngaySinhHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        gioiTinhHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());
        trangThaiHvLbl.prefWidthProperty().bind(tenHvLbl.widthProperty());

        // ===== Align - font - size ---------
        maleRadioBtn.setStyle(font);
        femaleRadioBtn.setStyle(font);
        diaChiHvTxt.setPrefRowCount(7);
        diaChiHvTxt.setPrefColumnCount(5);
        maHvTxt.setMinHeight(size); maHvTxt.setMinWidth(sizeWidth);
        sdtHvTxt.setMinHeight(size); sdtHvTxt.setMinWidth(sizeWidth);
        tenHvTxt.setMinHeight(size); tenHvTxt.setMinWidth(sizeWidth);
        emailHvTxt.setMinHeight(size); emailHvTxt.setMinWidth(sizeWidth);
        diaChiHvTxt.setMinHeight(size); diaChiHvTxt.setMinWidth(sizeWidth);
        trangThaiHvCbb.setMinHeight(size); trangThaiHvCbb.setMinWidth(sizeWidth);
        lopHocComboBox.setMinHeight(size); lopHocComboBox.setMinWidth(sizeWidth);
        ngaySinhHvDatePicker.setMinHeight(size); ngaySinhHvDatePicker.setMinWidth(sizeWidth);


        // === DatePicker - ngaySinh
        ngaySinhHvDatePicker.setPromptText("dd/MM/YYYY");
        ngaySinhHvDatePicker.valueProperty().addListener((observable, oldValue, newValue)-> {
            LocalDate localDate = LocalDate.now();
            if(newValue == null){
                statusLbl.setText("Vui lòng lựa chọn ngày sinh.");
            }else if (newValue.isBefore(localDate)){
                ngaySinhHv = newValue;
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ngày sinh không hợp lệ.");
                alert.setHeaderText("Năm sinh phải nhỏ hơn năm hiện tại.");
                alert.showAndWait();
                ngaySinhHvDatePicker.setValue(null);
            }

        });

        // Đặt sự kiện khi RadioButton được chọn
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, selectedRadioButton) -> {
            if (selectedRadioButton != null) {
                RadioButton selected = (RadioButton) selectedRadioButton;
                selectedValue = selected.getText();
                System.out.println("RadioButton đã được chọn: " + selectedValue);
            }
        });


        // === HBOX ------------------------------
        HBox maHvHb = new HBox();
        HBox tenHvHb = new HBox();
        HBox sdtHvHb = new HBox();
        HBox maLopHvHb = new HBox();
        HBox emailHvHb = new HBox();
        HBox diaChiHvHb = new HBox();
        HBox gioiTinhHvHb = new HBox(10);
        HBox ngaySinhHvHb = new HBox();
        HBox trangThaiHvHb = new HBox();
        HBox ngayDangKyHvHb = new HBox();

        maHvHb.getChildren().addAll(maHvLbl,  maHvTxt);
        tenHvHb.getChildren().addAll(tenHvLbl, tenHvTxt);
        sdtHvHb.getChildren().addAll(sdtHvLbl, sdtHvTxt);
        emailHvHb.getChildren().addAll(emailHvLbl, emailHvTxt);
        maLopHvHb.getChildren().addAll(maLopLbl, lopHocComboBox);
        diaChiHvHb.getChildren().addAll(diaChiHvLbl, diaChiHvTxt);
        gioiTinhHvHb.getChildren().addAll(gioiTinhHvLbl, maleRadioBtn, femaleRadioBtn);
        trangThaiHvHb.getChildren().addAll(trangThaiHvLbl, trangThaiHvCbb);
        ngaySinhHvHb.getChildren().addAll(ngaySinhHvLbl, ngaySinhHvDatePicker);

        // === B1. VBox ---------------------
        VBox leftVb = new VBox(10);
        VBox rightVb = new VBox(10);
        leftVb.setPadding(new Insets(25,0,10,10));
        rightVb.setPadding(new Insets(25, 0, 10,50));

        leftVb.getChildren().addAll(maHvHb, tenHvHb, gioiTinhHvHb, ngaySinhHvHb, sdtHvHb);
        rightVb.getChildren().addAll(emailHvHb, ngayDangKyHvHb, maLopHvHb, trangThaiHvHb, diaChiHvHb);

        // === B2. Set VBox into HBox
        HBox contentHb = new HBox();
        contentHb.getChildren().addAll(leftVb, rightVb);
        // -------------------------------------------------------------------------------------


        // === B1. Button --------------------------------------------
        addHvBtn = new Button(valueAdd);
        clearBtn = new Button(valueClear);
        addHvBtn.setStyle(format + font);
        clearBtn.setStyle(bg_red + font);

        // === Khoi tao sk
//        addHvBtn.setOnAction(event -> {
//            if(onLoadDataListener != null){
//                onLoadDataListener.onLoadData();
//            }
//        });


        // === B2. Set Button into HBox
        HBox btnHb = new HBox(30);
        btnHb.setAlignment(Pos.CENTER_RIGHT);
        btnHb.setPadding(new Insets(45, 50, 200,50));
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
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));
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
        Scene scene = new Scene(chucNangHvVb,677,450);
        stage = new Stage();
        stage.setScene(scene);
    }

    public void show(){
        changeColorBtn();
        stage.show();
    }


    public void getUpdateDataUI(){
        String maLop = this.lopHocComboBox.getPromptText();
        LopHoc lop = new LopHoc();
        lop.setMaLop(maLop);

        String trangThai = this.trangThaiHvCbb.getPromptText();

        if("Nữ".equals(this.selectedValue)){
            this.model.setGioiTinhHV("F");
        }else{
            this.model.setGioiTinhHV("M");
        }
        this.model.setMaHV(this.maHvTxt.getText());
        this.model.setHoTenHV(this.tenHvTxt.getText());
        this.model.setNgaySinhHV(ngaySinhHvDatePicker.getValue());
        this.model.setSdtHV(this.sdtHvTxt.getText());
        this.model.setEmailHV(this.emailHvTxt.getText());
        this.model.setDiaChiHV(this.diaChiHvTxt.getText());

        if(this.lopHocComboBox.getValue() == null){
            this.model.setMaLop(lop);
        }else{
            this.model.setMaLop(this.lopHoc);
        }

        if(this.trangThaiHvCbb.getValue() == null){
            this.model.setTrangThaiHV(trangThai);
        }else{
            this.model.setTrangThaiHV(this.trangThaiHvCbb.getValue());
        }
    }
    public void setUpdataDataUI(String newTitle, String newValueAddBtn, String newValueCancelBtn, HocVien hocVien){
        this.titleChucNangLbl.setText(newTitle);
        this.addHvBtn.setText(newValueAddBtn);
        this.clearBtn.setText(newValueCancelBtn);
        this.maHvTxt.setText(hocVien.getMaHV());
        this.tenHvTxt.setText(hocVien.getHoTenHV());
        this.ngaySinhHvDatePicker.setValue(hocVien.getNgaySinhHV());
        this.emailHvTxt.setText(hocVien.getEmailHV());
        this.sdtHvTxt.setText(hocVien.getSdtHV());
        this.diaChiHvTxt.setText(hocVien.getDiaChiHV());
        this.lopHocComboBox.setPromptText(hocVien.getMaLop().getMaLop());
        this.trangThaiHvCbb.setPromptText(hocVien.getTrangThaiHV());
        if("Nam".equals(hocVien.getGioiTinhHV())){
            this.femaleRadioBtn.setSelected(false);
            this.maleRadioBtn.setSelected(true);
        }else{
            this.femaleRadioBtn.setSelected(true);
            this.maleRadioBtn.setSelected(false);
        }
    }


    public void clearData(){
        this.maHvTxt.clear();
        this.tenHvTxt.clear();
        this.sdtHvTxt.clear();
        this.emailHvTxt.clear();
        this.diaChiHvTxt.clear();
        this.lopHocComboBox.setPromptText(valueLopHocCbb);
        this.trangThaiHvCbb.setPromptText(valueTrangThaiHvCbb);
        this.maleRadioBtn.setSelected(false);
        this.femaleRadioBtn.setSelected(false);
        this.ngaySinhHvDatePicker.setValue(null);
    }

    /** Interface dùng để lắng nghe sự kiện click */
    public interface OnLoadDataListener{
        void onLoadData();
    }

    private OnLoadDataListener onLoadDataListener;
    public void setOnLoadDataListener(OnLoadDataListener listener){
        this.onLoadDataListener = listener;
    }

    public void messageSuccess(String title, String headerText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void messageError(String title, String headerText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void messageWarning(String title, String headerText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    protected void changeColorBtn() {
        this.addHvBtn.setOnMouseEntered((event) -> {
            this.addHvBtn.setStyle(this.btnEffect1+this.font);
        });

        this.addHvBtn.setOnMouseExited((event) -> {
            this.addHvBtn.setStyle(this.format+this.font);
        });

        this.clearBtn.setOnMouseEntered((event) -> {
            this.clearBtn.setStyle(this.btnEffect2+this.font);
        });

        this.clearBtn.setOnMouseExited((event) -> {
            this.clearBtn.setStyle(this.bg_red+this.font);
        });
    }
}
