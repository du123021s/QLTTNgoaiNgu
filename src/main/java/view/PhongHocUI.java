package view;

import controller.PhongHocController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.PhongHoc;

public class PhongHocUI extends BorderPane {

    private PhongHocController phongHocController = new PhongHocController();
    private PhongHoc phongHoc;
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

    public PhongHocUI(){
        search = new Label();
        search.setStyle(font18);
        searchTLTxt = new TextField();
        searchTLTxt.setStyle(font18);
        searchTLTxt.setPromptText("Enter your search key...");
        searchTLTxt.setPrefWidth(550);
        searchTLTxt.setOnKeyReleased(event ->{
            String searchValue = searchTLTxt.getText();
            if(searchValue.isEmpty()){
                ObservableList data = phongHocController.getAll();
                addData(data);
                search.setText("");
            }else {
                ObservableList data = phongHocController.findPhongHoc(searchValue);
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
                ObservableList data = phongHocController.getAll();
                addData(data);
            }else {
                ObservableList data = phongHocController.findPhongHoc(searchValue);
                addData(data);
            }
        });

        HBox searchTlHbox = new HBox();
        searchTlHbox.setPadding(new Insets(10,25,10,25));
        searchTlHbox.setAlignment(Pos.CENTER);
        searchTlHbox.getChildren().addAll(searchTLTxt, searchTlBtn);

        refreshTlBtn = new Button("Làm mới");
        refreshTlBtn.setOnAction(event ->{
            ObservableList data = phongHocController.getAll();
            addData(data);
        });
        addTlBtn = new Button("Thêm");
        addTlBtn.setOnAction(event ->{
            try {
                phongHocController.showCreateForm();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        deleteTlBtn = new Button("Xóa");
        deleteTlBtn.setOnAction(event ->{
            phongHocController.delete(phongHoc);
            resetData();
        });
        updateTlBtn = new Button("Cập nhật");
        updateTlBtn.setOnAction(event ->{
            try {
                phongHocController.showUpdateForm(phongHoc);

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

        ObservableList<PhongHoc> phongHocs = phongHocController.getAll();
        creatContent(phongHocs);
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

    private void addData(ObservableList<PhongHoc> list){
        content.getChildren().clear();
        creatContent(list);
    }

    public void resetData(){
        ObservableList data = phongHocController.getAll();
        addData(data);
    }

    private void creatContent(ObservableList<PhongHoc> list){
        HBox rowbox = new HBox();
        rowbox.setAlignment(Pos.CENTER_LEFT);
        rowbox.setSpacing(45);



        int i = 0;

        for (PhongHoc item : list) {
            Button selectedBox = new Button();
            selectedBox.setPadding(new Insets(10,20,10,0));

            VBox itembox = new VBox();
            itembox.setPadding(new Insets(20,10,20,30));
            itembox.setAlignment(Pos.CENTER);

            selectedBox.setStyle("-fx-border-radius: 5;");


            Text maP = new Text("Phòng: "+ item.getMaPhong()); maP.setStyle(fontItem);
            Text tenP = new Text(item.getTrangthai());

            itembox.getChildren().addAll(maP, tenP);
            selectedBox.setGraphic(itembox);
            selectedBox.setStyle("-fx-background-color: #5B9BD5;" +
            "-fx-text-fill: #FFFFFF;");
            selectedBox.setOnMouseEntered(event->{
                selectedBox.setStyle("-fx-background-color: #9DC3E6;" +
                        "-fx-text-fill: #FFFFFF;");
            });
            selectedBox.setOnMouseExited(event->{
                selectedBox.setStyle("-fx-background-color: #5B9BD5;" +
                        "-fx-text-fill: #FFFFFF;");
            });
            selectedBox.setOnAction(event ->{
                selectedBox.setStyle("-fx-background-color: #9DC3E6;" +
                        "-fx-text-fill: #FFFFFF; -fx-border-color: red; -fx-border-width: 2px;");
                phongHoc = new PhongHoc();
                phongHoc.setMaPhong(maP.getText().replace("Phòng: ",""));
                phongHoc.setTrangthai(tenP.getText());
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
