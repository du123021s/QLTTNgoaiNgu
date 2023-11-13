package view;

import controller.LoaiTaiLieuController;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import model.TaiLieu;
import service.LoaiTLService;

import static javafx.application.Application.launch;

public class FormOfLoaiTL extends Application {
    private TextField maLoaiTxt;
    private TextField tenLoaitxt;
    private Label tileLoai;
    private LoaiTaiLieu loaiTaiLieu;
    private LoaiTaiLieuController loaiTaiLieuController;

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
        if(loaiTaiLieu==null){
            maLoaiTxt = new TextField();
            tenLoaitxt = new TextField();
        }else {
            maLoaiTxt = new TextField(loaiTaiLieu.getMaLoaiTL());
            maLoaiTxt.setEditable(false);
            tenLoaitxt = new TextField(loaiTaiLieu.getTen());
        }


        Label maLoaiLb = new Label("Mã loại tài liệu"); maLoaiLb.setStyle(font);
        Label tenLoaiLb = new Label("Tên loại tài liệu"); tenLoaiLb.setStyle(font);

        maLoaiTxt.setMinHeight(size); maLoaiTxt.setMinWidth(sizeWidth);
        tenLoaitxt.setMinHeight(size); tenLoaitxt.setMinWidth(sizeWidth);

        maLoaiLb.prefWidthProperty().bind(tenLoaiLb.widthProperty());

        HBox maHb = new HBox();
        maHb.getChildren().addAll(maLoaiLb, maLoaiTxt);
        maHb.setPadding(new Insets(25,0,10,10));
        HBox tenHb = new HBox();
        tenHb.getChildren().addAll(tenLoaiLb, tenLoaitxt);
        tenHb.setPadding(new Insets(25, 20, 10,30));
        HBox nd = new HBox();
        nd.getChildren().addAll(maHb,tenHb);

        Button create = new Button("Lưu");
        create.setOnAction(event->{
            saveHandler();
        });
        Button cancel = new Button("Hủy bỏ");
        if(loaiTaiLieu==null){
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

        if(loaiTaiLieu==null){
            tileLoai = new Label("Thêm loại tài liệu mới");
        }else {
            tileLoai =  new Label("Cập nhật loại tài liệu");
        }

        tileLoai.setStyle(titleFormat);
        tileLoai.setMinHeight(45);
        tileLoai.setAlignment(Pos.CENTER);
        tileLoai.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        statusLbl.setMaxWidth(Double.MAX_VALUE);
        statusLbl.setWrapText(true);

        VBox FoL = new VBox();
        tileLoai.prefWidthProperty().bind(FoL.widthProperty());
        FoL.getChildren().addAll(tileLoai, nd, statusLbl, button);
        FoL.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        Scene nscene = new Scene(FoL,677,400);

        Stage newStage = new Stage();
        newStage.setScene(nscene);
        newStage.show();

    }

    public void reciveLoaiTaiLieu(LoaiTaiLieu loai){
        loaiTaiLieu =new LoaiTaiLieu();
        loaiTaiLieu=loai;
    }

    private void cancelHander(){
        if(loaiTaiLieu==null){
            maLoaiTxt.clear();
            tenLoaitxt.clear();
        }else {
            Stage currentStage = (Stage) maLoaiTxt.getScene().getWindow();
            currentStage.close();
        }
    }

    private void saveHandler(){
        System.out.println("cÓ Nha.");
        statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
        statusLbl.setPadding(new Insets(15));
        statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra

        LoaiTLService loaiTLService = new LoaiTLService();
        if (maLoaiTxt.getText().isEmpty()) {
            statusLbl.setText("Mã Loại Tài liệu không được để trống.");
        } else if (loaiTLService.getLoaiById(maLoaiTxt.getText())!=0&&loaiTaiLieu==null) {
            statusLbl.setText("Mã Loại Tài liệu đã tồn tại.");
        } else if (tenLoaitxt.getText().isEmpty()) {
            statusLbl.setText("Tên Loại Tài liệu không được để trống.");
        }else {
            LoaiTaiLieu loaiTaiLieu1 = new LoaiTaiLieu();
            loaiTaiLieu1.setMaLoaiTL(maLoaiTxt.getText());
            loaiTaiLieu1.setTen(tenLoaitxt.getText());
            loaiTaiLieuController = new LoaiTaiLieuController();
            if(loaiTaiLieu ==null){
                loaiTaiLieuController.create(loaiTaiLieu1);
            }else {
                loaiTaiLieuController.update(loaiTaiLieu1);
            }
        }
    }
}
