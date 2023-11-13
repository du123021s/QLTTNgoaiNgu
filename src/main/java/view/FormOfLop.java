package view;

import controller.LopHocController;
import controller.PhongHocController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.LopHoc;
import service.LopHocService;

import java.sql.Date;

public class FormOfLop extends Application {
    private TextField maLoptxt,trangthaitxt, maGVtxt,maKHtxt;
    private DatePicker ngayTaotxt;

    private Label tilePhong;
    private LopHoc lopHoc;
    private LopHocController lopHocController;

    private String font = "-fx-font-size:20px; ";
    private Integer size = 30;
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
        if(lopHoc==null){
            maLoptxt = new TextField();
            trangthaitxt = new TextField();
            ngayTaotxt = new DatePicker();
            maGVtxt = new TextField();
            maKHtxt = new TextField();


        }else {
            maLoptxt = new TextField(lopHoc.getMaLop());
            maLoptxt.setEditable(false);
            trangthaitxt = new TextField(lopHoc.getTrangThaiLop());
            ngayTaotxt = new DatePicker();
            maGVtxt = new TextField(lopHoc.getMaGV());
            maKHtxt = new TextField(lopHoc.getMaKH());

        }


        Label maLopLb = new Label("Mã Lớp: "); maLopLb.setStyle(font);
        Label trangthaiLb = new Label("Status: "); trangthaiLb.setStyle(font);
        Label ngayTaoLb = new Label("Ngày: "); ngayTaoLb.setStyle(font);
        Label maGVLb = new Label("Mã GV: "); maGVLb.setStyle(font);
        Label maKHLb = new Label("Mã KH: "); maKHLb.setStyle(font);

        maLoptxt.setMinHeight(size); maLoptxt.setMinWidth(sizeWidth);
        trangthaitxt.setMinHeight(size); trangthaitxt.setMinWidth(sizeWidth);
        ngayTaotxt.setMinHeight(size); ngayTaotxt.setMinWidth(sizeWidth);
        maGVtxt.setMinHeight(size); maGVtxt.setMinWidth(sizeWidth);
        maKHtxt.setMinHeight(size); maKHtxt.setMinWidth(sizeWidth);

        trangthaiLb.prefWidthProperty().bind(maLopLb.widthProperty());
        ngayTaoLb.prefWidthProperty().bind(maLopLb.widthProperty());
        maGVLb.prefWidthProperty().bind(maLopLb.widthProperty());
        maKHLb.prefWidthProperty().bind(maLopLb.widthProperty());

        HBox maLopHb = new HBox();
        HBox trangthaiHb = new HBox();
        HBox ngayTaoHb = new HBox();
        HBox maGVHb = new HBox();
        HBox maKHHb = new HBox();



        VBox rightVb = new VBox(10);

        rightVb.setPadding(new Insets(25, 0, 10,180));

        maLopHb.getChildren().addAll(maLopLb, maLoptxt);
        trangthaiHb.getChildren().addAll(trangthaiLb, trangthaitxt);
        ngayTaoHb.getChildren().addAll(ngayTaoLb, ngayTaotxt);
        maGVHb.getChildren().addAll(maGVLb, maGVtxt);
        maKHHb.getChildren().addAll(maKHLb, maKHtxt);


        rightVb.getChildren().addAll(maLopHb, trangthaiHb,ngayTaoHb, maGVHb, maKHHb);

        Button create = new Button("Lưu");
        create.setOnAction(event->{
            //saveHandler();
        });
        Button cancel = new Button();
        if(lopHoc==null){
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

        if(lopHoc==null){
            tilePhong = new Label("Thêm lớp học mới");
        }else {
            tilePhong =  new Label("Cập nhật lớp học");
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

        FoL.getChildren().addAll(tilePhong, rightVb, statusLbl, button);
        FoL.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        Scene nscene = new Scene(FoL,677,400);

        Stage newStage = new Stage();
        newStage.setScene(nscene);
        newStage.show();

    }

    public void reciveLop(LopHoc lop){
        lopHoc=lop;
    }

    private void cancelHander(){
        if(lopHoc==null){
            maLoptxt.clear();
            trangthaitxt.clear();
            maGVtxt.clear();
            maKHtxt.clear();

        }else {
            Stage currentStage = (Stage) maLoptxt.getScene().getWindow();
            currentStage.close();
        }
    }

    private void saveHandler(){
        System.out.println("cÓ Nha.");
        statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
        statusLbl.setPadding(new Insets(15));
        statusLbl.setText(""); // Đặt lại nội dung của statusLbl trước khi kiểm tra

        LopHocService lopHocService = new LopHocService();
        if (maLoptxt.getText().isEmpty()) {
            statusLbl.setText("Mã Lớp học không được để trống.");
        } else if (lopHocService.getLopHocById(maLoptxt.getText())!=0&&lopHoc==null) {
            statusLbl.setText("Mã Lớp học đã tồn tại.");
        } else if (trangthaitxt.getText().isEmpty()) {
            statusLbl.setText("Trạng thái Lớp học không được để trống.");
        }else {
            LopHoc lopHoc1 = new LopHoc();
            lopHoc1.setMaLop(maLoptxt.getText());
            lopHoc1.setTrangThaiLop(trangthaitxt.getText());
            if(lopHoc ==null){
                lopHocController = new LopHocController();
                lopHocController.create(lopHoc1);
            }else {
                lopHocController = new LopHocController();
                lopHocController.update(lopHoc1);
            }
        }
    }
}
