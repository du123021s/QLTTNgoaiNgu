package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.HocVien;
import model.TaiLieu;
import service.TaiLieuService;

import java.time.LocalDate;

public class TaiLieuUI extends BorderPane {
    private VBox rightVb;
    private Button searchTlBtn, addTlBtn, updateTlBtn, deleteTlBtn ;
    private TextField searchTLTxt, tenTLTxt;
    private String font18 = "-fx-font-size:16px";
    private String format = "-fx-background-color: #030063;" +
            "-fx-text-fill: #FFFFFF;";

    public TaiLieuUI() {
        // ======= HOC VIEN -  RIGHT INTERFACE --------------------------------------

        // === Search bar
        searchTLTxt = new TextField();
        searchTLTxt.setStyle(font18);
        searchTLTxt.setPromptText("Enter your search key...");
        searchTLTxt.setPrefWidth(550);

        searchTlBtn = new Button("Tìm Kiếm");
        searchTlBtn.setPadding(new Insets(6,3,5,3));
        searchTlBtn.getStyleClass().add("searchHVBtn");

        HBox searchTlHbox = new HBox();
        searchTlHbox.setPadding(new Insets(10,25,10,25));
        searchTlHbox.setAlignment(Pos.CENTER);
        searchTlHbox.getChildren().addAll(searchTLTxt, searchTlBtn);
        // -----------------------------------------------------------

        // === Add, delete, update btn -------------------
        addTlBtn = new Button("Thêm");
        deleteTlBtn = new Button("Xóa");
        updateTlBtn = new Button("Cập nhật");

        addTlBtn.setStyle(format+font18);
        deleteTlBtn.setStyle(format+font18);
        updateTlBtn.setStyle(format+font18);

        HBox addUpDelHb = new HBox(5);
        addUpDelHb.setPadding(new Insets(0,10,0,0));
        addUpDelHb.setAlignment(Pos.TOP_RIGHT);
        addUpDelHb.getChildren().addAll(addTlBtn, deleteTlBtn, updateTlBtn);
        // ----------------------------------------------------------------------------


        // === TaiLieu Table -----------------------
        TableView taiLieuTable = new TableView<>();
        TableColumn<TaiLieu, Integer> sttTL = new TableColumn<>("STT");
        TableColumn<TaiLieu, String> maTL = new TableColumn<>("Mã TL");
        TableColumn<TaiLieu, String> tenTL = new TableColumn<>("Tên tài liệu");
        TableColumn<TaiLieu, Float> gia = new TableColumn<>("Giá");
        TableColumn<TaiLieu, String> nguon = new TableColumn<>("Nguồn");
        TableColumn<TaiLieu, String> mota = new TableColumn<>("Mô tả");
        TableColumn<TaiLieu, String> maLTL = new TableColumn<>("Mã loại tài liệu");


        taiLieuTable.getColumns().addAll(sttTL, maTL, tenTL, gia, nguon, mota,maLTL);

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
        nguon.setCellValueFactory(new PropertyValueFactory<>("nguon"));
        mota.setCellValueFactory(new PropertyValueFactory<>("mota"));
        maLTL.setCellValueFactory(new PropertyValueFactory<>("maLoaiTL"));

        TaiLieuService taiLieuService = new TaiLieuService();
        ObservableList tailieuList = taiLieuService.getTLlist();
        taiLieuTable.getItems().addAll(tailieuList);

        PopupUI pop =new PopupUI();
        addTlBtn = new Button("Loại tài liệu");
        addTlBtn.setStyle(format+font18);
        addTlBtn.setOnAction(event -> {

            pop.showPopup();
        });

        rightVb = new VBox(10);
        rightVb.setAlignment(Pos.TOP_CENTER);
        rightVb.getChildren().addAll(searchTlHbox, addUpDelHb, taiLieuTable,addTlBtn);
        rightVb.getStyleClass().add(getClass().getResource("/css/TaiLieu.css").toExternalForm());
        rightVb.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        this.setCenter(rightVb);
    }
}
