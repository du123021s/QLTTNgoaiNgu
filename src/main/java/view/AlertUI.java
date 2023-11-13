package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertUI {
    public void showAlert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public int showConfirmAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Bạn có chắc chắn muốn tiếp tục không?");

        // Hiển thị và đợi cho người dùng xác nhận hoặc hủy bỏ
        Optional<ButtonType> result = alert.showAndWait();

        // Kiểm tra xem người dùng đã chọn OK hay Cancel
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Người dùng đã chọn OK");
            return 1;
        } else {
            System.out.println("Người dùng đã chọn Cancel");
            return 0;
        }
    }
}
