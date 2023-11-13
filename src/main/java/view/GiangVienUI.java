package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.GiangVien;
import service.GiangVienService;

import java.time.LocalDate;

public class GiangVienUI extends BorderPane {
    private VBox rightVb;
    private Button searchGvBtn, addGvBtn, updateGvBtn, deleteGvBtn ;
    private TextField searchGVTxt, hotenHVTxt;
    private  ChucNangConGV view;
    private GiangVien model;
    private ObservableList<GiangVien> observableList;
    private GiangVienService giangVienService;
    private TableView giangVienTable;
    private String font18 = "-fx-font-size:16px";
    private String font = "-fx-font-size:16px";
    private String format = "-fx-background-color: #030063;" +
            "-fx-text-fill: #FFFFFF;";

    public GiangVienUI() {
        // ======= HOC VIEN -  RIGHT INTERFACE --------------------------------------

        // === Search bar
        searchGVTxt = new TextField();
        searchGVTxt.setStyle(font18);
        searchGVTxt.setPromptText("Enter your search key...");
        searchGVTxt.setPrefWidth(550);

        searchGvBtn = new Button("Tìm Kiếm");
        searchGvBtn.setPadding(new Insets(6,3,5,3));
        searchGvBtn.getStyleClass().add("searchHVBtn");

        HBox searchHvHbox = new HBox();
        searchHvHbox.setPadding(new Insets(10,25,10,25));
        searchHvHbox.setAlignment(Pos.CENTER);
        searchHvHbox.getChildren().addAll(searchGVTxt, searchGvBtn);
        // -----------------------------------------------------------

        // === Add, delete, update btn -------------------
        addGvBtn = new Button("Thêm");
        deleteGvBtn = new Button("Xóa");
        updateGvBtn = new Button("Cập nhật");

        addGvBtn.setStyle(format+font);
        deleteGvBtn.setStyle(format+font);
        updateGvBtn.setStyle(format+font);

        // SK----------------------------------------------------------------------------

//        addGvBtn.setOnAction(event -> handleAddData());
//        updateGvBtn.setOnAction(event -> handleUpdateData());
//        deleteGvBtn.setOnAction(event -> handleDeleteData());
//        searchGvBtn.setOnAction(event -> handleSearchData());
//        searchGVTxt.setOnKeyReleased(keyEvent -> handleSearchDateNow());


        HBox addUpDelHb = new HBox(5);
        addUpDelHb.setPadding(new Insets(0,10,0,0));
        addUpDelHb.setAlignment(Pos.TOP_RIGHT);
        addUpDelHb.getChildren().addAll(addGvBtn, deleteGvBtn, updateGvBtn);



        // === GiangVien Table -----------------------
        TableView giangVienTable = new TableView<>();
        TableColumn<GiangVien, Integer> sttGV = new TableColumn<>("STT");
        TableColumn<GiangVien, String> maGV = new TableColumn<>("Mã GV");
        TableColumn<GiangVien, String> hotenGV = new TableColumn<>("Họ Tên");
        TableColumn<GiangVien,String> gioiTinhGV = new TableColumn<>("Giới Tính");
        TableColumn<GiangVien, LocalDate> ngaySinhGV = new TableColumn<>("Ngày sinh");
        TableColumn<GiangVien, String> diaChiGV = new TableColumn<>("Địa chỉ");
        TableColumn<GiangVien, String> sdtGV = new TableColumn<>("SĐT");
        TableColumn<GiangVien, String> emailGV = new TableColumn<>("Email");
        TableColumn<GiangVien, String> chuyenNganhGV = new TableColumn<>("Chuyên ngành");
        TableColumn<GiangVien, Integer> kinhNghiemgdGV = new TableColumn<>("Kinh Nghiệm");
        TableColumn<GiangVien, String> matKhauGV = new TableColumn<>("Mật Khẩu");
        TableColumn<GiangVien, LocalDate> ngayBDGV = new TableColumn<>("Ngày bắt đầu");
        TableColumn<GiangVien, LocalDate> ngayKTGV = new TableColumn<>("Ngày kết thúc");
        TableColumn<GiangVien, String> avatarGV = new TableColumn<>("Avatar");
        TableColumn<GiangVien, Integer> adminGV = new TableColumn<>("Admin");
        TableColumn<GiangVien, String> trangThaiGV = new TableColumn<>("Trạng Thái");


        giangVienTable.getColumns().addAll(sttGV, maGV, hotenGV, gioiTinhGV, ngaySinhGV, diaChiGV, sdtGV, emailGV, chuyenNganhGV, kinhNghiemgdGV, matKhauGV, ngayBDGV, ngayKTGV, avatarGV, adminGV, trangThaiGV);


        // set : thiết lập cách dữ liệu của một cột được hiển thị
        // cách mà một đối tượng dữ liệu trích xuất và hiển thị.
        sttGV.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GiangVien, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<GiangVien, Integer> param) {
                return new ReadOnlyObjectWrapper<>(giangVienTable.getItems().indexOf(param.getValue()) + 1);
            }
        });
        maGV.setCellValueFactory(new PropertyValueFactory<>("maGV"));
        hotenGV.setCellValueFactory(new PropertyValueFactory<>("hoTenGV"));
        gioiTinhGV.setCellValueFactory(new PropertyValueFactory<>("gioiTinhGV"));
        ngaySinhGV.setCellValueFactory(new PropertyValueFactory<>("ngaySinhGV"));
        diaChiGV.setCellValueFactory(new PropertyValueFactory<>("diaChiGV"));
        sdtGV.setCellValueFactory(new PropertyValueFactory<>("sdtGV"));
        emailGV.setCellValueFactory(new PropertyValueFactory<>("emailGV"));
        chuyenNganhGV.setCellValueFactory(new PropertyValueFactory<>("chuyenNganhGV"));
        kinhNghiemgdGV.setCellValueFactory(new PropertyValueFactory<>("kinhNghiemgdGV"));
        matKhauGV.setCellValueFactory(new PropertyValueFactory<>("matKhauGV"));
        ngayBDGV.setCellValueFactory(new PropertyValueFactory<>("ngayBDGV"));
        ngayKTGV.setCellValueFactory(new PropertyValueFactory<>("ngạyKTGV"));
        avatarGV.setCellValueFactory(new PropertyValueFactory<>("avatarGV"));
        adminGV.setCellValueFactory(new PropertyValueFactory<>("adminGV"));
        trangThaiGV.setCellValueFactory(new PropertyValueFactory<>("trangthaiGV"));

        sttGV.setStyle(font);
        maGV.setStyle(font);
        hotenGV.setStyle(font);
        gioiTinhGV.setStyle(font);
        ngaySinhGV.setStyle(font);
        diaChiGV.setStyle(font);
        sdtGV.setStyle(font);
        emailGV.setStyle(font);
        chuyenNganhGV.setStyle(font);
        kinhNghiemgdGV.setStyle(font);
        matKhauGV.setStyle(font);
        ngayBDGV.setStyle(font);
        ngayKTGV.setStyle(font);
        avatarGV.setStyle(font);
        adminGV.setStyle(font);
        trangThaiGV.setStyle(font);

//        giangVienTable.getItems().clear();
//        giangVienService = new GiangVienService();
//        observableList = giangVienService.getGiangVienList();
//        giangVienTable.getItems().addAll(observableList);



        VBox rightVb = new VBox(10);
        rightVb.setAlignment(Pos.TOP_CENTER);
        rightVb.getChildren().addAll(searchHvHbox, addUpDelHb, giangVienTable);
        rightVb.getStyleClass().add(getClass().getResource("/css/HocVienUI.css").toExternalForm());
        rightVb.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        this.setCenter(rightVb);
    }
//    protected void handleSearchDateNow() {
//        giangVienService = new GiangVienService();
//        observableList = giangVienService.searchGiangVien(this.searchGVTxt.getText());
//        giangVienTable.getItems().clear();
//        giangVienTable.getItems().addAll(observableList);
//    }
//    protected void handleSearchData() {
//        giangVienService = new GiangVienService();
//        observableList = giangVienService.searchGiangVien(this.searchGVTxt.getText());
//        giangVienTable.getItems().clear();
//        giangVienTable.getItems().addAll(observableList);
//    }
//    protected void updateTable(){
//        observableList = giangVienService.getGiangVienList();
//        giangVienTable.getItems().clear();
//        giangVienTable.getItems().addAll(observableList);
//    }
//    protected void handleDeleteData() {
//        if(giangVienTable.getSelectionModel().getSelectedItem() != null){
//            model = new GiangVien();
//            view = new ChucNangConGV();
//            model = (GiangVien) giangVienTable.getSelectionModel().getSelectedItem();
//            controller = new GiangVienController(model, view);
//            controller.delete();
//            updateTable();
//            giangVienTable.refresh();
//        }else{
//            messageInformation("Thông báo", "Vui lòng click chọn giảng viên " +
//                    "bạn muốn xóa trong bảng.");
//        }
//    }
//    protected void handleAddData() {
//        controller = new GiangVienController();
//        controller.loadAddData();
//        GiangVien gv = controller.handleAddGv();
//        observableList.add(gv);
//        updateTable();
//        giangVienTable.refresh();
//    }
//    protected void handleUpdateData() {
//        if(giangVienTable.getSelectionModel().getSelectedItem() != null){
//            model = new GiangVien();
//            view = new ChucNangConGV();
//            model = (GiangVien) giangVienTable.getSelectionModel().getSelectedItem();
//            controller = new GiangVienController(model, view);
//            controller.setUpdateDataUI();
//            controller.loadUpdateData();
//            updateTable();
//            giangVienTable.refresh();
//        }else{
//            messageInformation("Thông báo", "Vui lòng click chọn học viên bạn muốn cập nhật trong bảng.");
//        }
//    }
//    public void messageInformation(String title, String headerText){
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(headerText);
//        alert.showAndWait();
//    }
}
