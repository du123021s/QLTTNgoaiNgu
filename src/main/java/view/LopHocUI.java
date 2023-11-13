package view;

import controller.LopHocController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

import model.GiangVien;
import model.KhoaHoc;
import model.LopHoc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

public class LopHocUI extends BorderPane {
    private LopHocController lopHocController = new LopHocController();
    private LopHoc lopHoc;
    private KhoaHoc khoaHoc;
    private GiangVien giangVien;
    private VBox rightVb;
    private VBox content;
    ScrollPane scrollPane;
    private Button searchTlBtn, addTlBtn, updateTlBtn, deleteTlBtn, refreshTlBtn ;
    private TextField searchTLTxt, tenTLTxt;
    private Label search;
    private String font18 = "-fx-font-size:16px";
    private String fontItem = "-fx-font-size:22px";
    private String format = "-fx-background-color: #030063;" +
            "-fx-text-fill: #FFFFFF;";

    public LopHocUI(){
        search = new Label();
        search.setStyle(font18);
        searchTLTxt = new TextField();
        searchTLTxt.setStyle(font18);
        searchTLTxt.setPromptText("Enter your search key...");
        searchTLTxt.setPrefWidth(550);
        searchTLTxt.setOnKeyReleased(event ->{
            String searchValue = searchTLTxt.getText();
            if(searchValue.isEmpty()){
                ObservableList data = lopHocController.getAll();
                addData(data);
                search.setText("");
            }else {
                ObservableList data = lopHocController.findLopHoc(searchValue);
                if(data.isEmpty()){
                    search.setText("Không tìm thấy dữ liệu");
                }else {
                    search.setText("");
                }
                addData(data);
            }
        });

        searchTlBtn = new Button("Tìm Kiếm");
        searchTlBtn.setPadding(new Insets(6,3,5,3));
        searchTlBtn.getStyleClass().add("searchHVBtn");
        searchTlBtn.setOnAction(event ->{
            String searchValue = searchTLTxt.getText();
            if(searchValue.isEmpty()){
                ObservableList data = lopHocController.getAll();
                addData(data);
            }else {
                ObservableList data = lopHocController.findLopHoc(searchValue);
                addData(data);
            }
        });

        HBox searchTlHbox = new HBox();
        searchTlHbox.setPadding(new Insets(10,25,10,25));
        searchTlHbox.setAlignment(Pos.CENTER);
        searchTlHbox.getChildren().addAll(searchTLTxt, searchTlBtn);

        refreshTlBtn = new Button("Làm mới");
        refreshTlBtn.setOnAction(event ->{
            ObservableList data = lopHocController.getAll();
            addData(data);
        });
        addTlBtn = new Button("Thêm");
        addTlBtn.setOnAction(event ->{
            try {
                lopHocController.showCreateForm();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        deleteTlBtn = new Button("Xóa");
        deleteTlBtn.setOnAction(event ->{
            lopHocController.delete(lopHoc);
            resetData();
        });
        updateTlBtn = new Button("Cập nhật");
        updateTlBtn.setOnAction(event ->{
            try {
                lopHocController.showUpdateForm(lopHoc);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        refreshTlBtn.setStyle(format+font18);
        addTlBtn.setStyle(format+font18);
        deleteTlBtn.setStyle(format+font18);
        updateTlBtn.setStyle(format+font18);

        HBox addUpDelHb = new HBox(5);
        addUpDelHb.setPadding(new Insets(0,10,0,0));
        addUpDelHb.setAlignment(Pos.TOP_RIGHT);
        addUpDelHb.getChildren().addAll(addTlBtn, deleteTlBtn, updateTlBtn,refreshTlBtn);

        //======================Content========================================================//
        content = new VBox(10);
        content.setPadding(new Insets(10,0,20,10));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: white");

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToHeight(true); // Đảm bảo ScrollPane lấy toàn bộ chiều cao của VBox

        ObservableList<LopHoc> lopHocs = lopHocController.getAll();
        creatContent(lopHocs);
//        HBox rowbox = new HBox();
//        rowbox.setAlignment(Pos.CENTER_LEFT);
//        rowbox.setSpacing(45);
//
//        ObservableList<PhongHoc> phongHocs = phongHocController.getAll();
//
//        int i = 0;
//
//        for (PhongHoc item : phongHocs) {
//            Button selectedBox = new Button();
//            selectedBox.setPadding(new Insets(10,20,10,0));
//            VBox itembox = new VBox();
//            itembox.setPadding(new Insets(20,10,20,30));
//            itembox.setAlignment(Pos.CENTER);
//            selectedBox.setStyle("-fx-border-radius: 5;");
//            Text maP = new Text("Phòng: "+item.getMaPhong()); maP.setStyle(fontItem);
//            Text tenP = new Text("<"+item.getTrangthai()+">");
//            itembox.getChildren().addAll(maP, tenP);
//            selectedBox.setGraphic(itembox);
//            rowbox.getChildren().add(selectedBox);
//            i++;
//
//            if (i == 3) {
//                content.getChildren().add(rowbox);
//                rowbox = new HBox(); // Tạo một rowbox mới
//                i = 0;
//            }
//        }
//
//        // Kiểm tra nếu còn dư một số phần tử trong rowbox
//        if (!rowbox.getChildren().isEmpty()) {
//            content.getChildren().add(rowbox);
//        }

        //====================================================================================//
        rightVb = new VBox(10);
        rightVb.setAlignment(Pos.TOP_CENTER);
        rightVb.getChildren().addAll(searchTlHbox, addUpDelHb,search, content,scrollPane);
        rightVb.getStyleClass().add(getClass().getResource("/css/TaiLieu.css").toExternalForm());
        rightVb.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        this.setCenter(rightVb);
    }

    public void addData(ObservableList<LopHoc> list){
        content.getChildren().clear();
        creatContent(list);
    }

    public void resetData(){
        ObservableList data = lopHocController.getAll();
        addData(data);
    }

    private void creatContent(ObservableList<LopHoc> list){
        HBox rowbox = new HBox();
        rowbox.setAlignment(Pos.CENTER_LEFT);
        rowbox.setSpacing(45);



        int i = 0;

        for (LopHoc item : list) {
            Button selectedBox = new Button();
            selectedBox.setPadding(new Insets(10,20,10,0));

            VBox itembox = new VBox();
            itembox.setPadding(new Insets(20,10,20,30));
            itembox.setAlignment(Pos.CENTER);

            selectedBox.setStyle("-fx-border-radius: 5;");


            Text maLop = new Text("Mã: "+ item.getMaLop()); maLop.setStyle(fontItem);
            Text trangThai = new Text(item.getTrangThaiLop());trangThai.setStyle(fontItem);
            DatePicker taoNgay = new DatePicker(item.getLopTaoNgay());
            Text maGV = new Text(item.getMaGV());
            Text maKH = new Text(item.getMaKH());

            AtomicBoolean allow = new AtomicBoolean(true);
            itembox.getChildren().addAll(maLop, trangThai, taoNgay, maGV, maKH);
            selectedBox.setGraphic(itembox);
            selectedBox.setStyle("-fx-background-color: #5B9BD5;" +
                    "-fx-text-fill: #FFFFFF;");
            selectedBox.setOnMouseEntered(event->{
                if (allow.get()){
                    selectedBox.setStyle("-fx-background-color: #9DC3E6;" +
                            "-fx-text-fill: #FFFFFF;");
                }else {
                    selectedBox.setStyle("-fx-background-color: #9DC3E6;" +
                            "-fx-text-fill: #FFFFFF; -fx-border-color: red; -fx-border-width: 2px;");
                }

            });
            selectedBox.setOnMouseExited(event->{
                if(allow.get()){
                    selectedBox.setStyle("-fx-background-color: #5B9BD5;" +
                            "-fx-text-fill: #FFFFFF;");
                }

            });
            selectedBox.setOnMouseClicked(event ->{
                if(allow.get()){
                    allow.set(false);
                    selectedBox.setStyle("-fx-background-color: #9DC3E6;" +
                            "-fx-text-fill: #FFFFFF; -fx-border-color: red; -fx-border-width: 2px;");
                    lopHoc = new LopHoc();
                    giangVien = new GiangVien();
                    khoaHoc = new KhoaHoc();

                    lopHoc.setMaLop(maLop.getText().replace("Phòng: ",""));
                    lopHoc.setTrangThaiLop(trangThai.getText());
                    lopHoc.setLopTaoNgay(Date.valueOf(item.getLopTaoNgay()).toLocalDate());
                    giangVien.setMaGiangVien(maGV.getText());
                    lopHoc.setGiangVien(giangVien);
                    khoaHoc.setMaKH(maKH.getText());
                    lopHoc.setKhoaHoc(khoaHoc);
                }else {
                    allow.set(true);
                    lopHoc = null;
                    giangVien = null;
                    khoaHoc = null;
                }

            });

            rowbox.getChildren().add(selectedBox);
            i++;

            if (i == 3) {
                content.getChildren().add(rowbox);
                rowbox = new HBox();
                rowbox.setAlignment(Pos.CENTER_LEFT);
                rowbox.setSpacing(45);// Tạo một rowbox mới
                i = 0;
            }
        }

        // Kiểm tra nếu còn dư một số phần tử trong rowbox
        if (!rowbox.getChildren().isEmpty()) {
            content.getChildren().add(rowbox);
        }

    }



}
