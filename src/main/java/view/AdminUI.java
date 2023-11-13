package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.HocVien;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AdminUI{
    private Stage stage;
    public BorderPane rootBp;
    public Scene scene;
    public InputStream input;
    public Image img;
    public ImageView imgView;

    public Button hvBtn, lichHocBtn, taiLieuBtn, khoaHocBtn,
            giangvienBtn, lophocBtn, phonghocBtn, tlkhoahocBtn;

    public HocVienUI hocvien;


    public String menuFormat = "-fx-background-color:#F1EF90;-fx-text-fill:#000000;";
    public String menuEffect = "-fx-background-color:#F2EDBF;-fx-text-fill:#000000;";
    public String font18 = "-fx-font-size:18px";
    public String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";

    public AdminUI(Stage state){
        this.stage = state;
        rootBp = new BorderPane();
        //=== Thiết lập menu thông qua: MenuBar <- Menu <- MenuItem
        MenuBar menubar = new MenuBar();
        Menu menu = new Menu("Trợ giúp");
        MenuItem item1 = new MenuItem("Đăng xuất");
        MenuItem item2 = new MenuItem("Về chúng tôi");
        MenuItem item3 = new MenuItem("Hướng dẫn sử dụng");
        menu.getItems().addAll(item1, item2, item3);

        item2.setOnAction(event -> handleAboutUs());
        item3.setOnAction(event -> handleInstruction());

        menubar.getMenus().add(menu);

        // === Thiết lập phần biểu tượng app
        input = getClass().getResourceAsStream("/icons/logoRg3.png");
        img = new Image(input);
        state.getIcons().add(img);

        HBox logoTitleHb = new HBox(getLogo());
        logoTitleHb.setAlignment(Pos.CENTER);
        logoTitleHb.getStyleClass().add("logoTitleHb");

        VBox banner = new VBox();
        banner.getChildren().addAll(menubar, logoTitleHb);


        // === Chức năng
        Label titleQuanLy = new Label("QUẢN LÝ TRUNG TÂM");
        HBox titleQuanLyHb = new HBox();
        HBox.setHgrow(titleQuanLyHb, Priority.ALWAYS);
        titleQuanLyHb.setPadding(new Insets(18,3,18,8));
        titleQuanLyHb.getStyleClass().add("titleQuanLy");
        titleQuanLyHb.getChildren().add(titleQuanLy);

        hvBtn = new Button("Học Viên");
        giangvienBtn = new Button("Giảng Viên");
        khoaHocBtn = new Button("Khóa Học");
        taiLieuBtn = new Button("Tài Liệu");
        phonghocBtn = new Button("Phòng Học");
        lophocBtn = new Button("Lớp Học");
        lichHocBtn = new Button("Sắp Xếp Lịch Học");
        tlkhoahocBtn = new Button("Tài Liệu Cho Khóa Học");


        hvBtn.setGraphic(getStudentIcon()); hvBtn.setAlignment(Pos.CENTER_LEFT);
        khoaHocBtn.setGraphic(getCourseIcon()); khoaHocBtn.setAlignment(Pos.CENTER_LEFT);
        lichHocBtn.setGraphic(getTimeTableIcon()); lichHocBtn.setAlignment(Pos.CENTER_LEFT);
        taiLieuBtn.setGraphic(getFileIcon()); taiLieuBtn.setAlignment(Pos.CENTER_LEFT);
        giangvienBtn.setGraphic(getGVIcon()); giangvienBtn.setAlignment(Pos.CENTER_LEFT);
        phonghocBtn.setGraphic(getPhongHocIcon()); phonghocBtn.setAlignment(Pos.CENTER_LEFT);
        lophocBtn.setGraphic(getClassRoomIcon()); lophocBtn.setAlignment(Pos.CENTER_LEFT);
        tlkhoahocBtn.setGraphic(getTaiLieuKHIcon()); tlkhoahocBtn.setAlignment(Pos.CENTER_LEFT);


        hvBtn.setStyle(menuFormat);
        khoaHocBtn.setStyle(menuFormat);
        lichHocBtn.setStyle(menuFormat);
        taiLieuBtn.setStyle(menuFormat);
        giangvienBtn.setStyle(menuFormat);
        phonghocBtn.setStyle(menuFormat);
        lophocBtn.setStyle(menuFormat);
        tlkhoahocBtn.setStyle(menuFormat);

        // === Thiếp lập nội dung cụ thể cho từng chức năng (bên phải app)
        VBox rootLeftVb = new VBox();
        rootLeftVb.setPadding(new Insets(5,0,5,0));
        rootLeftVb.getStyleClass().add("leftVb");


        VBox leftVb = new VBox(5);
        leftVb.setPrefWidth(290);
        leftVb.setAlignment(Pos.TOP_LEFT);


        hvBtn.prefWidthProperty().bind(leftVb.widthProperty());
        khoaHocBtn.prefWidthProperty().bind(leftVb.widthProperty());
        lichHocBtn.prefWidthProperty().bind(leftVb.widthProperty());
        taiLieuBtn.prefWidthProperty().bind(leftVb.widthProperty());
        giangvienBtn.prefWidthProperty().bind(leftVb.widthProperty());
        phonghocBtn.prefWidthProperty().bind(leftVb.widthProperty());
        lophocBtn.prefWidthProperty().bind(leftVb.widthProperty());
        tlkhoahocBtn.prefWidthProperty().bind(leftVb.widthProperty());

        leftVb.getChildren().addAll( hvBtn, giangvienBtn, khoaHocBtn, taiLieuBtn, phonghocBtn, lophocBtn, lichHocBtn, tlkhoahocBtn);
        rootLeftVb.getChildren().addAll(titleQuanLyHb, leftVb);

        // -------------------------- RIGHT INTERFACE --------------------------------------
        hocvien = new HocVienUI();

        // -------------------------- ROOT INTERFACE ---------------------------------------

        rootBp.setTop(banner);
        rootBp.setLeft(rootLeftVb);
        rootBp.setCenter(hocvien);
        scene = new Scene(rootBp, 990, 750);
        scene.getStylesheets().add(getClass().getResource("/css/AdminUI.css").toExternalForm());
        state.setScene(scene);
        state.show();
    }

    protected void handleInstruction() {
        String path = "html/instruction.html";
        handleFileHtml(path);
    }

    protected void handleAboutUs() {
        String path = "html/about.html";
        handleFileHtml(path);
    }

    protected void handleFileHtml(String path){
        File htmlFile = new File(path);
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show(){
        stage.show();
    }



    // ============ HÀM LẤY ẢNH ========================================================
    public ImageView getlogoIcon(){
        input = getClass().getResourceAsStream("/icons/logoRg3.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(62);
        imgView.setFitWidth(132);
        return imgView;
    }

    public ImageView getLogo(){
        input = getClass().getResourceAsStream("/images/logoTA2.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(90);
        imgView.setFitWidth(509);
        return imgView;
    }

    public ImageView getIconLogo(){
        input = getClass().getResourceAsStream("/images/logoApp.jpg");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(90);
        imgView.setFitWidth(65);
        return imgView;
    }

    public ImageView getStudentIcon(){
        input = getClass().getResourceAsStream("/icons/student.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getFileIcon(){
        input = getClass().getResourceAsStream("/icons/file.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getCourseIcon(){
        input = getClass().getResourceAsStream("/icons/course.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getTimeTableIcon(){
        input = getClass().getResourceAsStream("/icons/timetable.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getLogoutIcon(){
        input = getClass().getResourceAsStream("/icons/log-out.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getGVIcon(){
        input = getClass().getResourceAsStream("/icons/teacher.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getClassRoomIcon(){
        input = getClass().getResourceAsStream("/icons/classroom.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getPhongHocIcon(){
        input = getClass().getResourceAsStream("/icons/room.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getTaiLieuKHIcon(){
        input = getClass().getResourceAsStream("/icons/tailieuKH.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }
}
