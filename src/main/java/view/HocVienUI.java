package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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

import java.time.LocalDate;

public class HocVienUI extends BorderPane {
    private VBox rightVb;
    private Button searchHvBtn, addHvBtn, updateHvBtn, deleteHvBtn ;
    private TextField searchHVTxt, hotenHVTxt;
    private String font18 = "-fx-font-size:16px";
    private String format = "-fx-background-color: #030063;" +
                            "-fx-text-fill: #FFFFFF;";

    public HocVienUI() {
        // ======= HOC VIEN -  RIGHT INTERFACE --------------------------------------

        // === Search bar
        searchHVTxt = new TextField();
        searchHVTxt.setStyle(font18);
        searchHVTxt.setPromptText("Enter your search key...");
        searchHVTxt.setPrefWidth(550);

        searchHvBtn = new Button("Tìm Kiếm");
        searchHvBtn.setPadding(new Insets(6,3,5,3));
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

        addHvBtn.setStyle(format+font18);
        deleteHvBtn.setStyle(format+font18);
        updateHvBtn.setStyle(format+font18);

        HBox addUpDelHb = new HBox(5);
        addUpDelHb.setPadding(new Insets(0,10,0,0));
        addUpDelHb.setAlignment(Pos.TOP_RIGHT);
        addUpDelHb.getChildren().addAll(addHvBtn, deleteHvBtn, updateHvBtn);
        // ----------------------------------------------------------------------------


        // === HocVien Table -----------------------
        TableView hocVienTable = new TableView<>();
        TableColumn<HocVien, Integer> sttHV = new TableColumn<>("STT");
        TableColumn<HocVien, String> maHV = new TableColumn<>("Mã HV");
        TableColumn<HocVien, String> tenHV = new TableColumn<>("Họ Tên");
        TableColumn<HocVien, String> gioiTinhHV = new TableColumn<>("Giới Tính");
        TableColumn<HocVien, LocalDate> ngSinhHV = new TableColumn<>("Ngày Sinh");
        TableColumn<HocVien, String> sdtHV = new TableColumn<>("SĐT");
        TableColumn<HocVien, String> emailHV = new TableColumn<>("Email");
        TableColumn<HocVien, String> diaChiHV = new TableColumn<>("Địa chỉ");
        TableColumn<HocVien, LocalDate> ngayDangKyHV = new TableColumn<>("Ngày ĐK");
        TableColumn<HocVien, String> trangThaiHV = new TableColumn<>("Trạng thái");

        hocVienTable.getColumns().addAll(sttHV, maHV, tenHV, gioiTinhHV, ngSinhHV, sdtHV, emailHV, diaChiHV, ngayDangKyHV, trangThaiHV);

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
        trangThaiHV.setCellValueFactory(new PropertyValueFactory<>("trangThaiHV"));



        VBox rightVb = new VBox(10);
        rightVb.setAlignment(Pos.TOP_CENTER);
        rightVb.getChildren().addAll(searchHvHbox, addUpDelHb, hocVienTable);
        rightVb.getStyleClass().add(getClass().getResource("/css/HocVienUI.css").toExternalForm());
        rightVb.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        this.setCenter(rightVb);
    }
}
