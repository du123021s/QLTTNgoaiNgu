package view;

import controller.HocVienController;
import controller.KhoaHocController;
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
import model.HocVien;
import model.KhoaHoc;
import model.KhoaHoc;
import model.LopHoc;
import service.HocVienService;
import service.KhoaHocService;

import java.time.LocalDate;

public class KhoaHocUI extends BorderPane {
    private VBox rightVb;
    private KhoaHocService khoaHocService;
    private ObservableList<KhoaHoc> observableList;
    private KhoaHoc model;
    private ChucNangConKH view;
    private KhoaHocController controller;
    private TableView khoaHocTable;

    public Button searchHvBtn, addHvBtn, updateHvBtn, deleteHvBtn;
    public TextField searchHVTxt;
    private String font = "-fx-font-size:16px";
    private String format = "-fx-background-color: #030063;" +
            "-fx-text-fill: #FFFFFF;";

    public KhoaHocUI(){
        // ======= HOC VIEN -  RIGHT INTERFACE --------------------------------------

        // === Search bar
        searchHVTxt = new TextField();
        searchHVTxt.setStyle(font);
        searchHVTxt.setPromptText("Enter your search key...");
        searchHVTxt.setPrefWidth(550);

        searchHvBtn = new Button("Tìm Kiếm");
        searchHvBtn.setPadding(new Insets(5,3,5,3));
        searchHvBtn.getStyleClass().add("searchHVBtn");

        HBox searchHvHbox = new HBox();
        searchHvHbox.setPadding(new Insets(10,25,10,25));
        searchHvHbox.setAlignment(Pos.CENTER);
        searchHvHbox.getChildren().addAll(searchHVTxt, searchHvBtn);
        // -----------------------------------------------------------

        // === Add, delete, update btn -------------------
        addHvBtn = new Button("Thêm");
        deleteHvBtn = new Button("Xóa");
        updateHvBtn = new Button("Cập nhật");

        addHvBtn.setStyle(format+font);
        deleteHvBtn.setStyle(format+font);
        updateHvBtn.setStyle(format+font);

        // sk

        addHvBtn.setOnAction(event -> handleAddData());
        updateHvBtn.setOnAction(event -> handleUpdateData());
        deleteHvBtn.setOnAction(event -> handleDeleteData());
        searchHvBtn.setOnAction(event -> handleSearchData());
        searchHVTxt.setOnKeyReleased(keyEvent -> handleSearchDateNow());

        HBox addUpDelHb = new HBox(5);
        addUpDelHb.setPadding(new Insets(0,10,0,0));
        addUpDelHb.setAlignment(Pos.TOP_RIGHT);
        addUpDelHb.getChildren().addAll(addHvBtn, deleteHvBtn, updateHvBtn);
        // ----------------------------------------------------------------------------


        // === HocVien Table -----------------------
        khoaHocTable = new TableView<>();

        TableColumn<KhoaHoc, Integer> sttKH = new TableColumn<>("STT");
        TableColumn<KhoaHoc, String> maKH = new TableColumn<>("Mã KH");
        TableColumn<KhoaHoc, String> tenHV = new TableColumn<>("Tên KH");
        TableColumn<KhoaHoc, String> moTaKH = new TableColumn<>("Mô Tả");
        TableColumn<KhoaHoc, Integer> thoiGianHocKH = new TableColumn<>("Thời Gian (Tháng)");
        TableColumn<KhoaHoc, String> hocPhiKH = new TableColumn<>("Học Phí");
        TableColumn<KhoaHoc, LocalDate> ngayBatDauKH = new TableColumn<>("Bắt Đầu Ngày");
        TableColumn<KhoaHoc, LocalDate> ngayKetThucKH = new TableColumn<>("Kết Thúc Ngày");
        TableColumn<KhoaHoc, Integer> soLuongHienTaiKH = new TableColumn<>("Số Lượng Còn Lại");
        TableColumn<KhoaHoc, Integer> soLuongToiDaKH = new TableColumn<>("Số Lượng Tối Đa");
        TableColumn<KhoaHoc, String> trangThaiKH = new TableColumn<>("Trạng Thái");

        khoaHocTable.getColumns().addAll(sttKH, maKH, tenHV, moTaKH, thoiGianHocKH, hocPhiKH,
                ngayBatDauKH, ngayKetThucKH, soLuongHienTaiKH, soLuongToiDaKH, trangThaiKH);

        // set : thiết lập cách dữ liệu của một cột được hiển thị
        // cách mà một đối tượng dữ liệu trích xuất và hiển thị.
        sttKH.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<KhoaHoc, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<KhoaHoc, Integer> param) {
                return new ReadOnlyObjectWrapper<>(khoaHocTable.getItems().indexOf(param.getValue()) + 1);
            }
        });

        maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        tenHV.setCellValueFactory(new PropertyValueFactory<>("tenKH"));
        moTaKH.setCellValueFactory(new PropertyValueFactory<>("motaKH"));
        thoiGianHocKH.setCellValueFactory(new PropertyValueFactory<>("thoiGianHocKH"));
        hocPhiKH.setCellValueFactory(new PropertyValueFactory<>("hocPhiKH"));
        ngayBatDauKH.setCellValueFactory(new PropertyValueFactory<>("ngayBatDauKH"));
        ngayKetThucKH.setCellValueFactory(new PropertyValueFactory<>("ngayKetThucKH"));
        soLuongToiDaKH.setCellValueFactory(new PropertyValueFactory<>("soLuongHVToiDa"));
        soLuongHienTaiKH.setCellValueFactory(new PropertyValueFactory<>("soLuongHVHienTai"));
        trangThaiKH.setCellValueFactory(new PropertyValueFactory<>("trangThaiKH"));

        sttKH.setStyle(font);
        maKH.setStyle(font);
        tenHV.setStyle(font);
        moTaKH.setStyle(font);
        thoiGianHocKH.setStyle(font);
        hocPhiKH.setStyle(font);
        ngayBatDauKH.setStyle(font);
        ngayKetThucKH.setStyle(font);
        soLuongHienTaiKH.setStyle(font);
        soLuongToiDaKH.setStyle(font);
        trangThaiKH.setStyle(font);

        khoaHocService = new KhoaHocService();
        observableList = khoaHocService.getKhoaHocList();
        khoaHocTable.getItems().addAll(observableList);

        VBox rightVb = new VBox(10);
        rightVb.setAlignment(Pos.TOP_CENTER);
        rightVb.getChildren().addAll(searchHvHbox, addUpDelHb, khoaHocTable);
        rightVb.getStyleClass().add(getClass().getResource("/css/HocVienUI.css").toExternalForm());
        rightVb.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        this.setCenter(rightVb);
    }

    protected void handleSearchDateNow() {
        observableList = khoaHocService.searchKhoaHoc(this.searchHVTxt.getText());
        khoaHocTable.getItems().clear();
        khoaHocTable.getItems().addAll(observableList);
    }

    protected void handleSearchData() {
        observableList = khoaHocService.searchKhoaHoc(this.searchHVTxt.getText());
        khoaHocTable.getItems().clear();
        khoaHocTable.getItems().addAll(observableList);
    }

    protected void updateTable(){
        observableList = khoaHocService.getKhoaHocList();
        khoaHocTable.getItems().clear();
        khoaHocTable.getItems().addAll(observableList);
    }

    protected void handleDeleteData() {
        if(khoaHocTable.getSelectionModel().getSelectedItem() != null){
            model = new KhoaHoc();
            view = new ChucNangConKH();
            model = (KhoaHoc) khoaHocTable.getSelectionModel().getSelectedItem();
            controller = new KhoaHocController(model, view);
            controller.delete();
            updateTable();
            khoaHocTable.refresh();
        }else{
            messageInformation("Thông báo", "Vui lòng click chọn khóa học " +
                    "bạn muốn xóa trong bảng.");
        }
    }

    protected void handleAddData() {
        controller = new KhoaHocController();
        controller.loadAddData();
        controller.handleAddKH();
        updateTable();
        khoaHocTable.refresh();
    }

    protected void handleUpdateData() {
        if(khoaHocTable.getSelectionModel().getSelectedItem() != null){
            model = new KhoaHoc();
            view = new ChucNangConKH();
            model = (KhoaHoc) khoaHocTable.getSelectionModel().getSelectedItem();
            controller = new KhoaHocController(model, view);
            controller.setUpdateDataUI();
            controller.loadUpdateData();
            updateTable();
            khoaHocTable.refresh();
        }else{
            messageInformation("Thông báo", "Vui lòng click chọn khóa học bạn muốn cập nhật trong bảng.");
        }

    }

    public void messageInformation(String title, String headerText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
