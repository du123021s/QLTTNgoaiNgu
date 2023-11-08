package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;

public class AdminUI extends Application {
    private BorderPane rootBp;
    private Scene scene;
    private Stage stage;
    private InputStream input;
    private Image img;
    private ImageView imgView;

    private Button hvBtn, lichHocBtn, taiLieuBtn, khoaHocBtn,
            giangvienBtn, lophocBtn, phonghocBtn, tlkhoahocBtn;

    private HocVienUI hocvien;


    private String menuFormat = "-fx-background-color:#F1EF90;-fx-text-fill:#000000;";
    private String menuEffect = "-fx-background-color:#F2EDBF;-fx-text-fill:#000000;";
    private String font18 = "-fx-font-size:18px";
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";
    public static void main(String[] agrs){
        launch(agrs);
    }

    @Override
    public void start(Stage stage) throws Exception {
        showWindow();
        changeColorBtn();
        moveToHocVienUI();
        moveToGiangVienUI();
        moveToKhoaHocUI();
        moveToTaiLieuUI();
        moveToPhongHocUI();
        moveToLopHoc();
        moveToLichHocUI();
        moveToTaiLieuKhoaHocUI();
    }
    protected void showWindow() {
        rootBp = new BorderPane();
        stage = new Stage();

        //=== Thiết lập menu thông qua: MenuBar <- Menu <- MenuItem
        MenuBar menubar = new MenuBar();
        Menu menu = new Menu("Trợ giúp");
        MenuItem item1 = new MenuItem("Đăng xuất");
        MenuItem item2 = new MenuItem("Về chúng tôi");
        MenuItem item3 = new MenuItem("Hướng dẫn sử dụng");
        menu.getItems().addAll(item1, item2, item3);



        menubar.getMenus().add(menu);

        // === Thiết lập phần biểu tượng app
        input = getClass().getResourceAsStream("/icons/logoRg3.png");
        img = new Image(input);
        stage.getIcons().add(img);



//        HBox logoChuHb = new HBox(getLogo());
//        logoChuHb.setPadding(new Insets(0,200,0,200));
//        logoChuHb.setAlignment(Pos.TOP_CENTER);


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
        stage.setScene(scene);
        stage.show();
    }


    // ========================= XU LY CHUYEN GIAO DIEN =======================================
    private void moveToTaiLieuKhoaHocUI() {
        tlkhoahocBtn.setOnAction(event -> {
            TaiLieuKhoaHocUI taiLieuKhoaHocUI = new TaiLieuKhoaHocUI();
            rootBp.setCenter(taiLieuKhoaHocUI);
        });
    }

    private void moveToLichHocUI() {
        lichHocBtn.setOnAction(event -> {
            LichHocUI lichHocUI = new LichHocUI();
            lichHocUI.setCenter(lichHocUI);
        });
    }

    private void moveToLopHoc() {
        lophocBtn.setOnAction(event -> {
            LopHocUI lopHocUI = new LopHocUI();
            lopHocUI.setCenter(lopHocUI);
        });

    }

    private void moveToPhongHocUI() {
        phonghocBtn.setOnAction(event -> {
            PhongHocUI phongHocUI = new PhongHocUI();
            rootBp.setCenter(phongHocUI);
        });
    }

    private void moveToTaiLieuUI() {
        taiLieuBtn.setOnAction(event -> {
            TaiLieuUI taiLieuUI = new TaiLieuUI();
            rootBp.setCenter(taiLieuUI);
        });
    }

    private void moveToKhoaHocUI() {
        khoaHocBtn.setOnAction(event -> {
            KhoaHocUI khoaHocUI = new KhoaHocUI();
            rootBp.setCenter(khoaHocUI);
        });
    }

    private void moveToGiangVienUI() {
        giangvienBtn.setOnAction(event -> {
            GiangVienUI giangVienUI = new GiangVienUI();
            rootBp.setCenter(giangVienUI);
        });
    }

    private void moveToHocVienUI() {
        hvBtn.setOnAction(event -> {
            hocvien = new HocVienUI();
            rootBp.setCenter(hocvien);
        });
    }

    protected void changeColorBtn(){
        hvBtn.setOnMouseEntered((event) -> {
            hvBtn.setStyle(menuEffect);
        });

        hvBtn.setOnMouseExited((event) -> {
            hvBtn.setStyle(menuFormat);
        });

        khoaHocBtn.setOnMouseEntered((event) -> {
            khoaHocBtn.setStyle(menuEffect);
        });

        khoaHocBtn.setOnMouseExited((event) -> {
            khoaHocBtn.setStyle(menuFormat);
        });

        lichHocBtn.setOnMouseEntered((event) -> {
            lichHocBtn.setStyle(menuEffect);
        });

        lichHocBtn.setOnMouseExited((event) -> {
            lichHocBtn.setStyle(menuFormat);
        });

        taiLieuBtn.setOnMouseEntered((event) -> {
            taiLieuBtn.setStyle(menuEffect);
        });

        taiLieuBtn.setOnMouseExited((event) -> {
            taiLieuBtn.setStyle(menuFormat);
        });

        giangvienBtn.setOnMouseEntered((event) -> {
            giangvienBtn.setStyle(menuEffect);
        });

        giangvienBtn.setOnMouseExited((event) -> {
            giangvienBtn.setStyle(menuFormat);
        });
        phonghocBtn.setOnMouseEntered((event) -> {
            phonghocBtn.setStyle(menuEffect);
        });

        phonghocBtn.setOnMouseExited((event) -> {
            phonghocBtn.setStyle(menuFormat);
        });
        lophocBtn.setOnMouseEntered((event) -> {
            lophocBtn.setStyle(menuEffect);
        });

        lophocBtn.setOnMouseExited((event) -> {
            lophocBtn.setStyle(menuFormat);
        });
        tlkhoahocBtn.setOnMouseEntered((event) -> {
            tlkhoahocBtn.setStyle(menuEffect);
        });

        tlkhoahocBtn.setOnMouseExited((event) -> {
            tlkhoahocBtn.setStyle(menuFormat);
        });
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
