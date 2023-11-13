package view;

import controller.LoaiTaiLieuController;
import controller.TaiLieuController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.LoaiTaiLieu;
import model.TaiLieu;
import service.LoaiTLService;
import service.TaiLieuService;

public class PopupUI {
    private LoaiTaiLieuController loaiTaiLieuController;
    TableView LoaitaiLieuTable;
    private String font18 = "-fx-font-size:14px";
    private String format = "-fx-background-color: #030063;" +
            "-fx-text-fill: #FFFFFF;";

    public void showPopup() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.setResizable(false);

        LoaitaiLieuTable = new TableView<>();
        TableColumn<LoaiTaiLieu, Integer> sttLTL = new TableColumn<>("STT");
        TableColumn<LoaiTaiLieu, String> maLTL = new TableColumn<>("Mã Loại Tài liệu");
        TableColumn<LoaiTaiLieu, String> ten = new TableColumn<>("Tên loại tài liệu");


        LoaitaiLieuTable.getColumns().addAll(sttLTL, maLTL, ten);

        // set : thiết lập cách dữ liệu của một cột được hiển thị
        // cách mà một đối tượng dữ liệu trích xuất và hiển thị.
        sttLTL.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoaiTaiLieu, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<LoaiTaiLieu, Integer> param) {
                return new ReadOnlyObjectWrapper<>(LoaitaiLieuTable.getItems().indexOf(param.getValue()) + 1);
            }
        });
        maLTL.setCellValueFactory(new PropertyValueFactory<>("maLoaiTL"));
        ten.setCellValueFactory(new PropertyValueFactory<>("ten"));

        LoaiTLService loaiTLService = new LoaiTLService();
        ObservableList LoaitailieuList = loaiTLService.getLoaiTLlist();
        LoaitaiLieuTable.getItems().addAll(LoaitailieuList);

        Button addLTlBtn = new Button("Thêm");
        addLTlBtn.setOnAction(event ->{
            loaiTaiLieuController = new LoaiTaiLieuController();
            try {
                loaiTaiLieuController.showCreateForm();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button deleteLTlBtn = new Button("Xóa");
        deleteLTlBtn.setOnAction(event ->{
            ObservableList list = LoaitaiLieuTable.getSelectionModel().getSelectedItems();
            loaiTaiLieuController = new LoaiTaiLieuController();
            loaiTaiLieuController.delete(list);
        });
        Button updateLTlBtn = new Button("Cập nhật");
        updateLTlBtn.setOnAction(event ->{
            LoaiTaiLieu loai =new LoaiTaiLieu();
            loai = (LoaiTaiLieu) LoaitaiLieuTable.getSelectionModel().getSelectedItem();
            try {
                loaiTaiLieuController = new LoaiTaiLieuController();
                loaiTaiLieuController.showUpdateForm(loai);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Button refreshLTLBtn = new Button("Làm mới");
        refreshLTLBtn.setOnAction(actionEvent -> {
            loaiTaiLieuController = new LoaiTaiLieuController();
            ObservableList data = loaiTaiLieuController.getAll();
            addTableData(data);
        });

        HBox buttonBox = new HBox(5);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addLTlBtn,deleteLTlBtn,updateLTlBtn,refreshLTLBtn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(LoaitaiLieuTable,buttonBox);

        addLTlBtn.setStyle(format+font18);
        deleteLTlBtn.setStyle(format+font18);
        updateLTlBtn.setStyle(format+font18);
        refreshLTLBtn.setStyle(format+font18);

        vBox.setSpacing(10);

        Scene scene = new Scene(vBox, 400, 450);

        popupStage.setScene(scene);
        popupStage.setTitle("Loại tài liệu");

        popupStage.show();
    }

    private void addTableData(ObservableList<LoaiTaiLieu> list){
        LoaitaiLieuTable.getItems().clear();
        LoaitaiLieuTable.getItems().addAll(list);
    }
}
