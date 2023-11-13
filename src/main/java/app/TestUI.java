/**
 * @ Author:
 * @ Since: 09/10/2023
 * Content: class này dùng để chạy phần giao diện chính. Là lớp được chạy đầu tiên của APP
*/

package app;

import controller.AdminController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.HocVien;
import view.AdminUI;

public class TestUI extends Application {
    public static void main(String[] args){launch();}
    @Override
    public void start(Stage primaryState) throws Exception {
        HocVien model = new HocVien();
        AdminUI view = new AdminUI(primaryState);
        AdminController controller = new AdminController(model, view);

        controller.loadData();
        view.show();
    }
}
