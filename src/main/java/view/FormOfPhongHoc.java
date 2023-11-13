package view;

import controller.LoaiTaiLieuController;
import controller.PhongHocController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.LoaiTaiLieu;
import model.PhongHoc;
import service.PhongHocService;

public class FormOfPhongHoc extends Application {
    private TextField maPhongtxt;
    private TextField trangthaitxt;
    private Label tilePhong;
    private PhongHoc phongHoc;
    private PhongHocController phongHocController;

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
        openCreateForm();
    }

    private void openCreateForm() {
        if(phongHoc==null){
            maPhongtxt = new TextField();
            trangthaitxt = new TextField();
        }else {
            maPhongtxt = new TextField(phongHoc.getMaPhong());
            maPhongtxt.setEditable(false);
            trangthaitxt = new TextField(phongHoc.getTrangthai());
        }


        Label maPhongLb = new Label("Mã Phòng: "); maPhongLb.setStyle(font);
        Label trangthaiLb = new Label("Trạng thái: "); trangthaiLb.setStyle(font);

        maPhongtxt.setMinHeight(size); maPhongtxt.setMinWidth(sizeWidth);
        trangthaitxt.setMinHeight(size); trangthaitxt.setMinWidth(sizeWidth);

        trangthaiLb.prefWidthProperty().bind(maPhongLb.widthProperty());

        HBox maHb = new HBox();
        maHb.getChildren().addAll(maPhongLb, maPhongtxt);
        maHb.setPadding(new Insets(25,0,10,10));
        HBox tenHb = new HBox();
        tenHb.getChildren().addAll(trangthaiLb, trangthaitxt);
        tenHb.setPadding(new Insets(25, 20, 10,30));
        HBox nd = new HBox();
        nd.getChildren().addAll(maHb,tenHb);

        Button create = new Button("Lưu");
        create.setOnAction(event->{
            saveHandler();
        });
        Button cancel = new Button();
        if(phongHoc==null){
            cancel.setText("Làm mới");
        }else {
            cancel.setText("Hủy bỏ");
        }
        cancel.setOnAction(event ->{
            cancelHander();
        });
        create.setStyle(format + font);
        cancel.setStyle(bg_red + font);

        HBox button = new HBox(30);
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setPadding(new Insets(45, 50, 200,50));
        button.getChildren().addAll(create,cancel);

        HBox.setHgrow(create, Priority.ALWAYS);
        HBox.setHgrow(cancel, Priority.ALWAYS);
        create.setMaxWidth(Double.MAX_VALUE);
        cancel.setMaxWidth(Double.MAX_VALUE);

        if(phongHoc==null){
            tilePhong = new Label("Thêm phòng học mới");
        }else {
            tilePhong =  new Label("Cập nhật phong học");
        }

        tilePhong.setStyle(titleFormat);
        tilePhong.setMinHeight(45);
        tilePhong.setAlignment(Pos.CENTER);
        tilePhong.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        statusLbl.setMaxWidth(Double.MAX_VALUE);
        statusLbl.setWrapText(true);

        VBox FoL = new VBox();
        tilePhong.prefWidthProperty().bind(FoL.widthProperty());
        FoL.getChildren().addAll(tilePhong, nd, statusLbl, button);
        FoL.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        Scene nscene = new Scene(FoL,677,400);

        Stage newStage = new Stage();
        newStage.setScene(nscene);
        newStage.show();

    }

    public void recivePhong(PhongHoc phong){
        phongHoc=phong;
    }

    private void cancelHander(){
        if(phongHoc==null){
            maPhongtxt.clear();
            trangthaitxt.clear();
        }else {
            Stage currentStage = (Stage) maPhongtxt.getScene().getWindow();
            currentStage.close();
        }
    }

    private void saveHandler(){
        System.out.println("cÓ Nha.");
        statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
        statusLbl.setPadding(new Insets(15));
        statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra

        PhongHocService phongHocService = new PhongHocService();
        if (maPhongtxt.getText().isEmpty()) {
            statusLbl.setText("Mã Phòng học không được để trống.");
        } else if (phongHocService.getPhongHocById(maPhongtxt.getText())!=0&&phongHoc==null) {
            statusLbl.setText("Mã Phòng học đã tồn tại.");
        } else if (trangthaitxt.getText().isEmpty()) {
            statusLbl.setText("Trạng thái Phòng học không được để trống.");
        }else {
            PhongHoc phongHoc1 = new PhongHoc();
            phongHoc1.setMaPhong(maPhongtxt.getText());
            phongHoc1.setTrangthai(trangthaitxt.getText());
            if(phongHoc ==null){
                phongHocController = new PhongHocController();
                phongHocController.create(phongHoc1);
            }else {
                phongHocController = new PhongHocController();
                phongHocController.update(phongHoc1);
            }
        }
    }
}
