package view;

import controller.HocVienController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.HocVien;
import model.LopHoc;
import service.HocVienService;

import java.nio.channels.NetworkChannel;
import java.time.LocalDate;

public class HocVienUI extends BorderPane {
    private HocVienController controller;
    public ObservableList<HocVien> observableList;
    private HocVien model;
    private ChucNangConHV view;
    public TableView hocVienTable;
    private HocVienService hocVienService;
    public Button searchHvBtn, addHvBtn, updateHvBtn, deleteHvBtn;
    public TextField searchHVTxt;
    private String font = "-fx-font-size:16px";
    private String format = "-fx-background-color: #030063;" +
            "-fx-text-fill: #FFFFFF;";

    public HocVienUI() {
        // ======= HOC VIEN -  RIGHT INTERFACE --------------------------------------

        // === Search bar
        searchHVTxt = new TextField();
        searchHVTxt.setStyle(font);
        searchHVTxt.setPromptText("Enter your search key...");
        searchHVTxt.setPrefWidth(550);

        searchHvBtn = new Button("Tìm Kiếm");
        searchHvBtn.setPadding(new Insets(5, 3, 5, 3));
        searchHvBtn.getStyleClass().add("searchHVBtn");

        HBox searchHvHbox = new HBox();
        searchHvHbox.setPadding(new Insets(10, 25, 10, 25));
        searchHvHbox.setAlignment(Pos.CENTER);
        searchHvHbox.getChildren().addAll(searchHVTxt, searchHvBtn);
        // -----------------------------------------------------------

        // === Add, delete, update btn -------------------
        addHvBtn = new Button("Thêm");
        deleteHvBtn = new Button("Xóa");
        updateHvBtn = new Button("Cập nhật");

        addHvBtn.setStyle(format + font);
        deleteHvBtn.setStyle(format + font);
        updateHvBtn.setStyle(format + font);


        HBox addUpDelHb = new HBox(5);
        addUpDelHb.setPadding(new Insets(0, 10, 0, 0));
        addUpDelHb.setAlignment(Pos.TOP_RIGHT);
        addUpDelHb.getChildren().addAll(addHvBtn, deleteHvBtn, updateHvBtn);
        // ----------------------------------------------------------------------------


        // === HocVien Table -----------------------
        hocVienTable = new TableView<>();

        TableColumn<HocVien, Integer> sttHV = new TableColumn<>("STT");
        TableColumn<HocVien, String> maHV = new TableColumn<>("Mã HV");
        TableColumn<HocVien, String> tenHV = new TableColumn<>("Họ Tên");
        TableColumn<HocVien, String> gioiTinhHV = new TableColumn<>("Giới Tính");
        TableColumn<HocVien, LocalDate> ngSinhHV = new TableColumn<>("Ngày Sinh");
        TableColumn<HocVien, String> sdtHV = new TableColumn<>("SĐT");
        TableColumn<HocVien, String> emailHV = new TableColumn<>("Email");
        TableColumn<HocVien, String> diaChiHV = new TableColumn<>("Địa chỉ");
        TableColumn<HocVien, LocalDate> ngayDangKyHV = new TableColumn<>("Ngày ĐK");
        TableColumn<HocVien, LopHoc> maLop = new TableColumn<>("Mã Lớp");
        TableColumn<HocVien, String> trangThaiHV = new TableColumn<>("Trạng Thái");

        hocVienTable.getColumns().addAll(sttHV, maHV, tenHV, gioiTinhHV, ngSinhHV, sdtHV, emailHV, diaChiHV, ngayDangKyHV, maLop, trangThaiHV);

        // set : thiết lập cách dữ liệu của một cột được hiển thị
        // cách mà một đối tượng dữ liệu trích xuất và hiển thị.
        sttHV.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HocVien, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<HocVien, Integer> param) {
                return new ReadOnlyObjectWrapper<>(hocVienTable.getItems().indexOf(param.getValue()) + 1);
            }
        });
        maHV.setCellValueFactory(new PropertyValueFactory<>("maHV"));
        tenHV.setCellValueFactory(new PropertyValueFactory<>("hoTenHV"));
        gioiTinhHV.setCellValueFactory(new PropertyValueFactory<>("gioiTinhHV"));
        ngSinhHV.setCellValueFactory(new PropertyValueFactory<>("ngaySinhHV"));
        sdtHV.setCellValueFactory(new PropertyValueFactory<>("sdtHV"));
        emailHV.setCellValueFactory(new PropertyValueFactory<>("emailHV"));
        diaChiHV.setCellValueFactory(new PropertyValueFactory<>("diaChiHV"));
        ngayDangKyHV.setCellValueFactory(new PropertyValueFactory<>("ngayDangKyHoc"));
        maLop.setCellValueFactory(new PropertyValueFactory<>("maLop"));
        trangThaiHV.setCellValueFactory(new PropertyValueFactory<>("trangThaiHV"));

        sttHV.setStyle(font);
        maHV.setStyle(font);
        tenHV.setStyle(font);
        gioiTinhHV.setStyle(font);
        ngSinhHV.setStyle(font);
        sdtHV.setStyle(font);
        emailHV.setStyle(font);
        diaChiHV.setStyle(font);
        ngayDangKyHV.setStyle(font);
        maLop.setStyle(font);
        trangThaiHV.setStyle(font);

        hocVienTable.getItems().clear();
        hocVienService = new HocVienService();
        observableList = hocVienService.getStudentList();
        hocVienTable.getItems().addAll(observableList);

        VBox rightVb = new VBox(10);
        rightVb.setAlignment(Pos.TOP_CENTER);
        rightVb.getChildren().addAll(searchHvHbox, addUpDelHb, hocVienTable);
        rightVb.getStyleClass().add(getClass().getResource("/css/HocVienUI.css").toExternalForm());
        rightVb.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        this.setCenter(rightVb);
        allEvent();
    }

    protected void allEvent() {
        updateHvBtn.setOnAction(event -> handleUpdateData());
        addHvBtn.setOnAction(event -> handleAddData());
        deleteHvBtn.setOnAction(event -> handleDeleteData());
        searchHvBtn.setOnAction(event -> handleSearchData());
        searchHVTxt.setOnKeyReleased(keyEvent -> handleSearchDateNow());
    }

    public void updateTableHV() {
        hocVienTable.getItems().clear();
        observableList = hocVienService.getStudentList();
        hocVienTable.getItems().addAll(observableList);
    }

    protected void handleSearchDateNow() {
        hocVienService = new HocVienService();
        observableList = hocVienService.searchHocVien(this.searchHVTxt.getText());
        updateTableHV();
        hocVienTable.refresh();
    }

    protected void handleSearchData() {
        hocVienService = new HocVienService();
        observableList = hocVienService.searchHocVien(this.searchHVTxt.getText());
        updateTableHV();
        hocVienTable.refresh();
    }


    protected void handleDeleteData() {
        if (hocVienTable.getSelectionModel().getSelectedItem() != null) {
            model = new HocVien();
            view = new ChucNangConHV();
            model = (HocVien) hocVienTable.getSelectionModel().getSelectedItem();
            controller = new HocVienController(model, view);
            controller.delete();
            updateTableHV();
            hocVienTable.refresh();
        } else {
            messageInformation("Thông báo", "Vui lòng click chọn học viên " +
                    "bạn muốn xóa trong bảng.");
        }
    }

    protected void handleAddData() {
        controller = new HocVienController();
        controller.loadAddData();
        HocVien hv = controller.handleAddHv();
        observableList.add(hv);
        updateTableHV();
        hocVienTable.refresh();
    }

    protected void handleUpdateData() {
        if (hocVienTable.getSelectionModel().getSelectedItem() != null) {
            model = new HocVien();
            view = new ChucNangConHV();
            model = (HocVien) hocVienTable.getSelectionModel().getSelectedItem();
            controller = new HocVienController(model, view);
            controller.setUpdateDataUI();
            controller.loadUpdateData();
            updateTableHV();
            hocVienTable.refresh();

        } else {
            messageInformation("Thông báo", "Vui lòng click chọn học viên bạn muốn cập nhật trong bảng.");
        }
    }


    public void messageInformation(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
