package view;

import controller.TaiLieuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.TaiLieu;
import service.LoaiTLService;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javafx.application.Application.launch;

public class FormOfTaiLieu extends Application {
    private TaiLieuController taiLieuController;
    private TaiLieu taiLieu;
    private VBox formofTL;
    private TextField maTLTxt, tenTLTxt, giaTxt,  nguonTxt;
    private Button cancelBtn;

    private TextArea  motaTxt;

    private Button taoTLBtn;


    private String font = "-fx-font-size:16px; ";
    private Integer size = 32;
    private Integer sizeWidth = 199;
    private Label statusLbl = new Label();
    private  ComboBox LTLcbb;

    private String format = "-fx-background-color: #030063; -fx-text-fill: #FFFFFF;";
    private String bg_red = "-fx-text-fill:#FFFFFF; -fx-background-color: #E20825;";

    private String titleFormat = "-fx-background-color: #030063; -fx-text-fill: #FFFFFF; -fx-font-weight:800;-fx-font-size:20px; ";

    public static void main(String[] agrs){launch(); }
    @Override
    public void start(Stage stage) throws Exception {
        showWindow();
    }

    void showWindow(){
        if (taiLieu==null){
            maTLTxt = new TextField();
            tenTLTxt = new TextField();
            giaTxt = new TextField();
            nguonTxt = new TextField();
            motaTxt = new TextArea();

        }else {
            maTLTxt = new TextField(taiLieu.getMaTaiLieu());
            maTLTxt.setEditable(false);
            tenTLTxt = new TextField(taiLieu.getTen());
            giaTxt = new TextField(String.format("%.0f",taiLieu.getGia()));
            nguonTxt = new TextField(taiLieu.getNguon());
            motaTxt = new TextArea(taiLieu.getMota());

        }


        //====== Xử lý combo box======================================
        LTLcbb = new ComboBox<>();
        LoaiTLService ltl = new LoaiTLService();
        ObservableList ltlList = ltl.getOneLoaiTL("maLoaiTaiLieu");
        LTLcbb = new ComboBox();
        LTLcbb.getItems().addAll(ltlList);
        LTLcbb.getItems().add("Thêm loại mới");
        if(taiLieu==null){
            LTLcbb.setPromptText("--Chọn loại tài liệu--");
        }else{
            LTLcbb.setValue(taiLieu.getMaLoaiTL());
        }


        ComboBox finalLTLcbb = LTLcbb;
        LTLcbb.setOnAction(event ->{
            Object selectedItem = finalLTLcbb.getSelectionModel().getSelectedItem();
            if("Thêm loại mới".equals(selectedItem)){
                try {
                    openCreateForm();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //============================================================

        Label maTlLbl = new Label("Mã Tài liệu: ");  maTlLbl.setStyle(font);
        Label tenTlLbl = new Label("Tên tài liệu: "); tenTlLbl.setStyle(font);
        Label giaLbl = new Label("Giá: "); giaLbl.setStyle(font);
        Label nguonLbl = new Label("Nguồn: "); nguonLbl.setStyle(font);
        Label motaLbl = new Label("Mô tả: "); motaLbl.setStyle(font);
        Label LTLLbl = new Label("Loại tài liệu: "); LTLLbl.setStyle(font);

        motaTxt.setPrefRowCount(7);
        motaTxt.setPrefColumnCount(5);

        maTlLbl.prefWidthProperty().bind(LTLLbl.widthProperty());
        tenTlLbl.prefWidthProperty().bind(LTLLbl.widthProperty());
        giaLbl.prefWidthProperty().bind(LTLLbl.widthProperty());
        nguonLbl.prefWidthProperty().bind(LTLLbl.widthProperty());
        motaLbl.prefWidthProperty().bind(LTLLbl.widthProperty());



        // ===== Căn chỉnh - Align ---------


        maTLTxt.setMinHeight(size); maTLTxt.setMinWidth(sizeWidth);
        tenTLTxt.setMinHeight(size); tenTLTxt.setMinWidth(sizeWidth);
        nguonTxt.setMinHeight(size); nguonTxt.setMinWidth(sizeWidth);

        motaTxt.setMinHeight(size); motaTxt.setMinWidth(sizeWidth);
        giaTxt.setMinHeight(size); giaTxt.setMinWidth(sizeWidth);
        LTLcbb.setMinHeight(size); LTLcbb.setMinWidth(sizeWidth);


        HBox maTlHb = new HBox();
        HBox tenTlHb = new HBox();
        HBox giaHb = new HBox();
        HBox nguonHb = new HBox();
        HBox motaHb = new HBox();
        HBox LtlHb = new HBox();

        VBox leftVb = new VBox(10);
        VBox rightVb = new VBox(10);
        leftVb.setPadding(new Insets(25,0,10,10));
        rightVb.setPadding(new Insets(25, 0, 10,50));

        maTlHb.getChildren().addAll(maTlLbl,  maTLTxt);
        tenTlHb.getChildren().addAll(tenTlLbl, tenTLTxt);
        giaHb.getChildren().addAll(giaLbl, giaTxt);
        nguonHb.getChildren().addAll(nguonLbl, nguonTxt);
        motaHb.getChildren().addAll(motaLbl, motaTxt);
        LtlHb.getChildren().addAll(LTLLbl, LTLcbb);



        leftVb.getChildren().addAll(maTlHb, tenTlHb, nguonHb, giaHb);
        rightVb.getChildren().addAll(motaHb,LtlHb);



        Button addHvBtn = new Button("Lưu"); addHvBtn.setOnAction(event -> handleAddTL());
        if (taiLieu==null){
            cancelBtn = new Button("Làm mới");
        } else {
            cancelBtn = new Button("Hủy");
        }


        cancelBtn.setOnAction(event -> handleCancel());
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
        if(taiLieu==null){
            titleChucNangLbl.setText("Thêm tài liệu mới");
        }else {
            titleChucNangLbl.setText("Cập nhật tài liệu");
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
        if(taiLieu==null){
            maTLTxt.clear();
            tenTLTxt.clear();
            nguonTxt.clear();
            giaTxt.clear();
            motaTxt.clear();
            LTLcbb.setValue(null);
            LTLcbb.setPromptText("--Chọn loại  tìa liệu--");
        }else {
            Stage currentStage = (Stage) maTLTxt.getScene().getWindow();
            currentStage.close();
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

        if (maTLTxt.getText().isEmpty()) {
            statusLbl.setText("Mã Tài liệu không được để trống.");
        }else if (tenTLTxt.getText().isEmpty()) {
            statusLbl.setText("Tên Tài liệu không được để trống.");
        }else if (giaTxt.getText().isEmpty()) {
            statusLbl.setText("Giá tài liệu không được để trống.");
        }else if (nguonTxt.getText().isEmpty()) {
            statusLbl.setText("Nguồn tài liệu không được để trống.");
        }else if (LTLcbb.getValue()==null) {
            statusLbl.setText("Hãy chọn mã tài liệu trước khi thêm.");
        } else {
            TaiLieu taiLieu1 = new TaiLieu();
            taiLieu1.setMaTaiLieu(maTLTxt.getText());
            taiLieu1.setTen(tenTLTxt.getText());
            taiLieu1.setNguon(nguonTxt.getText());
            taiLieu1.setMota(motaTxt.getText());
            taiLieu1.setGia(Float.parseFloat(giaTxt.getText()));
            taiLieu1.setMaLoaiTL(LTLcbb.getValue().toString());

            taiLieuController = new TaiLieuController();
            if(taiLieu==null) {
                taiLieuController.create(taiLieu1);
            }else {
                taiLieuController.update(taiLieu1);
            }
        }
    }

    public void reciveTaiLieu(TaiLieu tl){
        taiLieu =new TaiLieu();
        taiLieu=tl;
    }
}
