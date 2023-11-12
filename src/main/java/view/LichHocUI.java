package view;

import controller.LichHocController;
import controller.TaiLieuController;
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
import model.LichHoc;
import model.TaiLieu;
import service.ShiftService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class LichHocUI extends BorderPane {
    private VBox rightVb;
    private TableView lichHocTable;
    private ShiftService shiftService;
    private LichHocController lichHocController;
    private Button searchTlBtn, addTlBtn, updateTlBtn, deleteTlBtn, addLTlBtn, refreshTlBtn ;
    private Label search;
    private TextField searchTLTxt, tenTLTxt;
    private String font18 = "-fx-font-size:16px";
    private String format = "-fx-background-color: #030063;" +
            "-fx-text-fill: #FFFFFF;";

    public LichHocUI(){
        // ======= HOC VIEN -  RIGHT INTERFACE --------------------------------------

        // === Search bar
        search = new Label();
        search.setStyle(font18);
        searchTLTxt = new TextField();
        searchTLTxt.setStyle(font18);
        searchTLTxt.setPromptText("Enter your search key...");
        searchTLTxt.setPrefWidth(550);
        searchTLTxt.setOnKeyReleased(event ->{
            String searchValue = searchTLTxt.getText();
            if(searchValue.isEmpty()){
                ObservableList data = lichHocController.getAll();
                addTableData(data);
                search.setText("");
            }else {
                ObservableList data = lichHocController.findLichHoc(searchValue);
                if(data.isEmpty()){
                    search.setText("Không tìm thấy dữ liệu");
                }else {
                    search.setText("");
                }
                addTableData(data);
            }
        });
        searchTlBtn = new Button("Tìm Kiếm");
        searchTlBtn.setPadding(new Insets(6,3,5,3));
        searchTlBtn.getStyleClass().add("searchHVBtn");
        searchTlBtn.setOnAction(event ->{
            String searchValue = searchTLTxt.getText();
            lichHocController = new LichHocController();
            if(searchValue.isEmpty()){
                ObservableList data = lichHocController.getAll();
                addTableData(data);
            }else {
                ObservableList data = lichHocController.find(searchValue);
                addTableData(data);
            }
        });

        HBox searchTlHbox = new HBox();
        searchTlHbox.setPadding(new Insets(10,25,10,25));
        searchTlHbox.setAlignment(Pos.CENTER);
        searchTlHbox.getChildren().addAll(searchTLTxt, searchTlBtn);
        // -----------------------------------------------------------

        // === Add, delete, update btn -------------------
        refreshTlBtn = new Button("Làm mới");
        refreshTlBtn.setOnAction(event ->{
            lichHocController =new LichHocController();
            ObservableList data = lichHocController.getAll();
            addTableData(data);
        });
        addTlBtn = new Button("Thêm");
        addTlBtn.setOnAction(event ->{
            lichHocController  = new LichHocController();
            try {
                lichHocController.showCreateForm();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        deleteTlBtn = new Button("Xóa");
        deleteTlBtn.setOnAction(event ->{
            ObservableList list = lichHocTable.getSelectionModel().getSelectedItems();
            lichHocController.delete(list);
        });
        updateTlBtn = new Button("Cập nhật");
        updateTlBtn.setOnAction(event ->{
            LichHoc lh = new LichHoc();
            lh = (LichHoc) lichHocTable.getSelectionModel().getSelectedItem();
            try {
                lichHocController.showUpdateForm(lh);
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
        // ----------------------------------------------------------------------------


        // === TaiLieu Table -----------------------
        lichHocTable = new TableView<>();
        TableColumn<LichHoc, Integer> sttLH = new TableColumn<>("STT");
        TableColumn<LichHoc, String> maLop = new TableColumn<>("Mã Lớp");
        TableColumn<LichHoc, String> thu = new TableColumn<>("Thứ");
        TableColumn<LichHoc, String> maPhong = new TableColumn<>("Mã phòng học");
        TableColumn<LichHoc, String> thoigian = new TableColumn<>("Ca học");
        TableColumn<LichHoc, String> thoigianct = new TableColumn<>("Thời gian");
        TableColumn<LichHoc, String> trangthai = new TableColumn<>("Trạng thái");


        lichHocController = new LichHocController();
        ObservableList data = lichHocController.getAll();
        addTableData(data);

        lichHocTable.getColumns().addAll(sttLH, maLop, thu, maPhong, thoigian, thoigianct, trangthai);

        // set : thiết lập cách dữ liệu của một cột được hiển thị
        // cách mà một đối tượng dữ liệu trích xuất và hiển thị.
        sttLH.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LichHoc, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<LichHoc, Integer> param) {
                return new ReadOnlyObjectWrapper<>(lichHocTable.getItems().indexOf(param.getValue()) + 1);
            }
        });
        maLop.setCellValueFactory(new PropertyValueFactory<>("maLop"));
        maPhong.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        thu.setCellValueFactory(new PropertyValueFactory<>("thuTrongTuan"));
        thoigian.setCellValueFactory(new PropertyValueFactory<>("thoiGianHoc"));
        thoigianct.setCellValueFactory(new PropertyValueFactory<>("thoiGianHoc"));
        thoigianct.setCellValueFactory(cellData -> {
            LichHoc lichHoc = cellData.getValue();
            String idShift = lichHoc.getThoiGianHoc(); // Giả sử idshift được lưu trong trường thoiGianHoc
            shiftService = new ShiftService();
            String thoiGianChiTiet = shiftService.getShiftDetailById(idShift); // Thay shiftService bằng dịch vụ thích hợp

            return new ReadOnlyObjectWrapper<>(thoiGianChiTiet);
        });
        trangthai.setCellValueFactory(new PropertyValueFactory<>("trangthai"));

        sttLH.setStyle(font18);
        maLop.setStyle(font18);
        maPhong.setStyle(font18);
        thu.setStyle(font18);
        thoigian.setStyle(font18);
        thoigianct.setStyle(font18);
        trangthai.setStyle(font18);


        rightVb = new VBox(10);
        rightVb.setAlignment(Pos.TOP_CENTER);
        rightVb.getChildren().addAll(searchTlHbox, addUpDelHb,search, lichHocTable);
        rightVb.getStyleClass().add(getClass().getResource("/css/TaiLieu.css").toExternalForm());
        rightVb.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        this.setCenter(rightVb);
    }

    private void addTableData(ObservableList<LichHoc> list){
        lichHocTable.getItems().clear();
        lichHocTable.getItems().addAll(list);
    }
}
