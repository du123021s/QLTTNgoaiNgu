package view;

import controller.LichHocController;
import controller.TaiLieuController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.LichHoc;
import model.Shift;
import model.TaiLieu;
import service.LoaiTLService;
import service.PhongHocService;
import service.ShiftService;
import service.ThuService;

import java.io.InputStream;

public class FormOfLichHoc extends Application {
    private LichHoc lichHoc;
    private LichHocController lichHocController;
    private VBox formofTL;
    private TextField trangthaitxt;
    Button cancelBtn;
    private ComboBox maLopCb, maPhongCb, thuCb, thoigianCb;


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

        if (lichHoc==null){
            trangthaitxt = new TextField();
            maLopCb = new ComboBox<>();
            thuCb = new ComboBox();
            maPhongCb = new ComboBox<>();
            thoigianCb = new ComboBox<>();
        }else {
            trangthaitxt = new TextField(lichHoc.getTrangthai());
            maLopCb = new ComboBox<>();
            thuCb = new ComboBox();
            maPhongCb = new ComboBox<>();
            thoigianCb = new ComboBox<>();
        }


        //====== Xử lý combo box======================================
        //====Combo box của lớp học===
        if(lichHoc!=null){
            maLopCb.setValue(lichHoc.getMaLop());
        }else {
            maLopCb.setPromptText("--Chọn lớp học--");
        }

        //============================================================

        //==============Combo Box của phòng học======================
        PhongHocService phongHocService = new PhongHocService();
        ObservableList phongHocs  = phongHocService.getOneLoaiPH("maPhongHoc");
        maPhongCb.getItems().addAll(phongHocs);
        if(lichHoc!=null){
            maPhongCb.setValue(lichHoc.getMaPhong());
        }else {
            maPhongCb.setPromptText("--Chọn phòng học--");
        }
        //============================================================

        //============Combbo box của thứ=============================
        ThuService thuService = new ThuService();
        ObservableList thus = thuService.getAll();
        thuCb.getItems().addAll(thus);
        if(lichHoc!=null){
            thuCb.setValue(lichHoc.getThuTrongTuan());
        }else {
            thuCb.setPromptText("--Chọn ngày học--");
        }
        //===========================================================
        //============Combo box của thời gian học===================
        ShiftService  shiftService = new ShiftService();
        ObservableList<Shift> shifts = shiftService.getAll();

        shifts.forEach(item ->{
            String id_shift = item.getId();
            String time = item.getTime();
            System.out.println(time);
            thoigianCb.getItems().add(id_shift + ": "+ time);
        });
        if(lichHoc!=null){
            String tg = shiftService.getShiftDetailById(lichHoc.getThoiGianHoc());
            thoigianCb.setValue(lichHoc.getThoiGianHoc()+": "+tg);
            trangthaitxt = new TextField(lichHoc.getTrangthai());
        }else {
            thoigianCb.setPromptText("--Chọn ca học--");
        }

        //===========================================================

        Label maLopLb = new Label("Mã lớp học: ");  maLopLb.setStyle(font);
        Label thuLb = new Label("Ngày học: "); thuLb.setStyle(font);
        Label maPhongLb = new Label("Mã phòng: "); maPhongLb.setStyle(font);
        Label thoigianLb = new Label("Thời gian học: "); thoigianLb.setStyle(font);
        Label trangthaiLb = new Label("Trạng thái: "); trangthaiLb.setStyle(font);

        maLopLb.prefWidthProperty().bind(thoigianLb.widthProperty());
        thuLb.prefWidthProperty().bind(thoigianLb.widthProperty());
        maPhongLb.prefWidthProperty().bind(thoigianLb.widthProperty());
        trangthaiLb.prefWidthProperty().bind(thoigianLb.widthProperty());



        // ===== Căn chỉnh - Align ---------


        trangthaitxt.setMinHeight(size); trangthaitxt.setMinWidth(sizeWidth);
        maLopCb.setMinHeight(size); maLopCb.setMinWidth(sizeWidth);
        thuCb.setMinHeight(size); thuCb.setMinWidth(sizeWidth);
        maPhongCb.setMinHeight(size); maPhongCb.setMinWidth(sizeWidth);
        thoigianCb.setMinHeight(size); thoigianCb.setMinWidth(sizeWidth);


        HBox maLopHb = new HBox();
        HBox thuHb = new HBox();
        HBox maPhonHb = new HBox();
        HBox thoigianHb = new HBox();
        HBox trangthaiHb = new HBox();


        VBox leftVb = new VBox(10);
        VBox rightVb = new VBox(10);
        leftVb.setPadding(new Insets(25,0,10,10));
        rightVb.setPadding(new Insets(25, 0, 10,50));

        maLopHb.getChildren().addAll(maLopLb,maLopCb);
        maPhonHb.getChildren().addAll(maPhongLb,maPhongCb);
        thuHb.getChildren().addAll(thuLb,thuCb);
        thoigianHb.getChildren().addAll(thoigianLb,thoigianCb);
        trangthaiHb.getChildren().addAll(trangthaiLb,trangthaitxt);



        leftVb.getChildren().addAll(maLopHb, thuHb, trangthaiHb);
        rightVb.getChildren().addAll(maPhonHb, thoigianHb);



        Button addHvBtn = new Button("Lưu"); addHvBtn.setOnAction(event -> handleAddTL());
        if(lichHoc==null){
            cancelBtn = new Button("Làm mới"); cancelBtn.setOnAction(event -> handleCancel());
        }else {
            cancelBtn = new Button("Hủy"); cancelBtn.setOnAction(event -> handleCancel());
        }

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

        Label titleChucNangLbl = new Label();
        if(lichHoc==null){
            titleChucNangLbl.setText("Thêm lịch học mới");
        }else {
            titleChucNangLbl.setText("Cập nhật lịch học");
        }
        titleChucNangLbl.setStyle(titleFormat);
        titleChucNangLbl.setMinHeight(45);
        titleChucNangLbl.setAlignment(Pos.CENTER);
        titleChucNangLbl.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        statusLbl.setMaxWidth(Double.MAX_VALUE);
        statusLbl.setWrapText(true);


        formofTL = new VBox();
        titleChucNangLbl.prefWidthProperty().bind(formofTL.widthProperty());
        formofTL.getChildren().addAll(titleChucNangLbl, contentHb, statusLbl, btnHb);
        formofTL.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        Scene scene = new Scene(formofTL,677,450);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    protected void handleCancel() {
        if(lichHoc!=null){
            Stage currentStage = (Stage) trangthaitxt.getScene().getWindow();
            currentStage.close();
        }else{
            maPhongCb.setValue(null);
            maLopCb.setValue(null);
            thuCb.setValue(null);
            thoigianCb.setValue(null);
            trangthaitxt.clear();
        }
    }

    private void openCreateForm() throws Exception {
        FormOfLoaiTL fol = new FormOfLoaiTL();
        Stage ns = new Stage();
        fol.start(ns);
    }
       protected void handleAddTL() {
        System.out.println("cÓ Nha.");
        statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
        statusLbl.setPadding(new Insets(15));
        statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra

        if (maLopCb.getValue().toString().isEmpty()) {
            statusLbl.setText("Mã lớp không được để trống.");
        }else if (maPhongCb.getValue().toString().isEmpty()) {
            statusLbl.setText("Mã phòng không được để trống.");
        }else if (thuCb.getValue().toString().isEmpty()) {
            statusLbl.setText("Ngày học trong tuần không được để trống.");
        }else if (thoigianCb.getValue().toString().isEmpty()) {
            statusLbl.setText("Ca học không được để trống.");
        }else {
            LichHoc lichHoc1 = new LichHoc();

            lichHoc1.setMaLop(maLopCb.getValue().toString());
            lichHoc1.setMaPhong(maPhongCb.getValue().toString());
            lichHoc1.setThuTrongTuan(thuCb.getValue().toString());
            lichHoc1.setThoiGianHoc(thoigianCb.getValue().toString().substring(0,2));
            lichHoc1.setTrangthai(trangthaitxt.getText());
            System.out.println(lichHoc1.getThoiGianHoc());

            lichHocController = new LichHocController();
            if (lichHoc!=null){
                lichHoc1.setSTT(lichHoc.getSTT());
                lichHocController.update(lichHoc1);
            }else {
                lichHocController.create(lichHoc1);
            }

        }
    }

    public void reciveLichHoc(LichHoc lh){
        lichHoc = new LichHoc();
        lichHoc = lh;
    }
}
