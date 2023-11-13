package view;

import controller.TaiLieuController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.HocVien;
import model.TaiLieu;
import service.TaiLieuService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class TaiLieuUI extends BorderPane {
    private TaiLieuController taiLieuController;
    private TableView taiLieuTable;
    private VBox rightVb;
    private Button searchTlBtn, addTlBtn, updateTlBtn, deleteTlBtn, addLTlBtn, refreshTlBtn ;
    private Label search;
    private TextField searchTLTxt, tenTLTxt;
    private String font18 = "-fx-font-size:16px";
    private String format = "-fx-background-color: #030063;" +
            "-fx-text-fill: #FFFFFF;";

    public TaiLieuUI() {
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
                ObservableList data = taiLieuController.getAll();
                addTableData(data);
                search.setText("");
            }else {
                ObservableList data = taiLieuController.findTaiLieu(searchValue);
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
            taiLieuController = new TaiLieuController();
            if(searchValue.isEmpty()){
                ObservableList data = taiLieuController.getAll();
                addTableData(data);
            }else {
                ObservableList data = taiLieuController.find(searchValue);
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
            taiLieuController = new TaiLieuController();
            ObservableList data = taiLieuController.getAll();
            addTableData(data);
        });
        addTlBtn = new Button("Thêm");
        addTlBtn.setOnAction(event ->{
            taiLieuController = new TaiLieuController();
            try {
                taiLieuController.showCreateForm();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        deleteTlBtn = new Button("Xóa");
        deleteTlBtn.setOnAction(event ->{
            ObservableList list = taiLieuTable.getSelectionModel().getSelectedItems();
            taiLieuController.delete(list);
        });
        updateTlBtn = new Button("Cập nhật");
        updateTlBtn.setOnAction(event ->{
            TaiLieu tl = new TaiLieu();
            tl = (TaiLieu) taiLieuTable.getSelectionModel().getSelectedItem();
            try {
                taiLieuController.showUpdateForm(tl);
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
        taiLieuTable = new TableView<>();
        TableColumn<TaiLieu, Integer> sttTL = new TableColumn<>("STT");
        TableColumn<TaiLieu, String> maTL = new TableColumn<>("Mã TL");
        TableColumn<TaiLieu, String> tenTL = new TableColumn<>("Tên tài liệu");
        TableColumn<TaiLieu, BigDecimal> gia = new TableColumn<>("Giá");
        TableColumn<TaiLieu, String> nguon = new TableColumn<>("Nguồn");
        TableColumn<TaiLieu, String> mota = new TableColumn<>("Mô tả");
        TableColumn<TaiLieu, String> maLTL = new TableColumn<>("Mã loại tài liệu");
        TableColumn<TaiLieu, String> trangthai = new TableColumn<>("Trạng thái");

        sttTL.setStyle(font18);
        maTL.setStyle(font18);
        tenTL.setStyle(font18);
        gia.setStyle(font18);
        nguon.setStyle(font18);
        mota.setStyle(font18);
        maLTL.setStyle(font18);
        trangthai.setStyle(font18);

        taiLieuController = new TaiLieuController();
        ObservableList data = taiLieuController.getAll();
        addTableData(data);

        taiLieuTable.getColumns().addAll(sttTL, maTL, tenTL, gia, nguon, mota,maLTL, trangthai);

        // set : thiết lập cách dữ liệu của một cột được hiển thị
        // cách mà một đối tượng dữ liệu trích xuất và hiển thị.
        sttTL.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TaiLieu, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<TaiLieu, Integer> param) {
                return new ReadOnlyObjectWrapper<>(taiLieuTable.getItems().indexOf(param.getValue()) + 1);
            }
        });
        maTL.setCellValueFactory(new PropertyValueFactory<>("maTaiLieu"));
        tenTL.setCellValueFactory(new PropertyValueFactory<>("ten"));
        gia.setCellValueFactory(new PropertyValueFactory<>("gia"));
        gia.setCellFactory(new Callback<TableColumn<TaiLieu, BigDecimal>, TableCell<TaiLieu, BigDecimal>>() {
            @Override
            public TableCell<TaiLieu, BigDecimal> call(TableColumn<TaiLieu, BigDecimal> param) {
                return new TableCell<TaiLieu, BigDecimal>() {
                    private final DecimalFormat format = new DecimalFormat("0");

                    @Override
                    protected void updateItem(BigDecimal item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
            }
        });

        nguon.setCellValueFactory(new PropertyValueFactory<>("nguon"));
        mota.setCellValueFactory(new PropertyValueFactory<>("mota"));
        maLTL.setCellValueFactory(new PropertyValueFactory<>("maLoaiTL"));
        trangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));



        PopupUI pop =new PopupUI();
        addLTlBtn = new Button("Loại tài liệu");
        addLTlBtn.setStyle(format+font18);
        addLTlBtn.setOnAction(event -> {
            pop.showPopup();
        });

        rightVb = new VBox(10);
        rightVb.setAlignment(Pos.TOP_CENTER);
        rightVb.getChildren().addAll(searchTlHbox, addUpDelHb,search, taiLieuTable,addLTlBtn);
        rightVb.getStyleClass().add(getClass().getResource("/css/TaiLieu.css").toExternalForm());
        rightVb.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        this.setCenter(rightVb);


    }
    private void addTableData(ObservableList<TaiLieu> list){
        taiLieuTable.getItems().clear();
        taiLieuTable.getItems().addAll(list);
    }

}
