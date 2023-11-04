package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

public class GiangVienUI extends Application {
    private BorderPane rootBp;
    private Scene scene;
    private Stage stage;

    private Button hvBtn, lichHocBtn, taiLieuBtn, khoaHocBtn,
                dangSuatBtn, giangvienBtn, lophocBtn, phonghocBtn, tlkhoahocBtn ;
    private InputStream input;
    private Image img;
    private ImageView imgView;

    private String menuFormat = "-fx-background-color:#21618C;-fx-text-fill:#FFFFFF;";
    private String menuEffect = "-fx-background-color:#F1C40F;-fx-text-fill:#000000;";
    public static void main(String[] agrs){
        launch(agrs);
    }

    @Override
    public void start(Stage stage) throws Exception {
        showWindow();
        changeColorBtn();
    }

    protected void showWindow() {
        rootBp = new BorderPane();
        stage = new Stage();

        //=== Thiết lập menu thông qua: MenuBar <- Menu <- MenuItem
        MenuBar menubar = new MenuBar();
        Menu menu = new Menu("File");
        MenuItem item1 = new MenuItem("Item 1");
        MenuItem item2 = new MenuItem("Item 2");
        MenuItem item3 = new MenuItem("Item 3");
        menu.getItems().addAll(item1, item2, item3);



        menubar.getMenus().add(menu);

        // === Thiết lập phần biểu tượng app
        input = getClass().getResourceAsStream("/icons/logoRg3.png");
        img = new Image(input);
        stage.getIcons().add(img);


        // === Thiết lập layout các chức năng cho Giang viên sử dụng (bên trái app)
        // === Logo icon
        Text logoTitle = new Text("BLUEDREAM");
        logoTitle.setFont(Font.font("SansSerif", FontWeight.BOLD, 47));
        HBox logoTitleHb = new HBox(10);
        logoTitleHb.setPadding(new Insets(15,0,15,0));
        logoTitleHb.getChildren().addAll(getlogoIcon(), logoTitle);
        logoTitleHb.getStyleClass().add("logoTitleHb");

        VBox banner = new VBox();
        banner.getChildren().addAll(menubar, logoTitleHb);


        // === Chức năng
        Text titleQuanLy = new Text("QUẢN LÝ TRUNG TÂM");
        HBox titleQuanLyHb = new HBox();
        titleQuanLyHb.setPadding(new Insets(25));
        titleQuanLyHb.getStyleClass().add("titleQuanLy");
        titleQuanLyHb.getChildren().add(titleQuanLy);

        hvBtn = new Button("Quản Lý Học Viên");
        giangvienBtn = new Button("Quản Lý Giảng Viên");
        khoaHocBtn = new Button("Quản Lý Khóa Học");
        phonghocBtn = new Button("Quản Lý Phòng Học");
        lophocBtn = new Button("Quản Lý Lớp Học");
        lichHocBtn = new Button("Sắp Xếp Lịch Học");
        taiLieuBtn = new Button("Quản Lý Tài Liệu");
        tlkhoahocBtn = new Button("Tài Liệu Cho Khóa Học");


        hvBtn.setGraphic(getStudentIcon()); hvBtn.setAlignment(Pos.CENTER_LEFT);
        khoaHocBtn.setGraphic(getCourseIcon()); khoaHocBtn.setAlignment(Pos.CENTER_LEFT);
        lichHocBtn.setGraphic(getTimeTableIcon()); lichHocBtn.setAlignment(Pos.CENTER_LEFT);
        taiLieuBtn.setGraphic(getFileIcon()); taiLieuBtn.setAlignment(Pos.CENTER_LEFT);
        giangvienBtn.setGraphic(getLogoutIcon()); giangvienBtn.setAlignment(Pos.CENTER_LEFT);
        phonghocBtn.setGraphic(getLogoutIcon()); phonghocBtn.setAlignment(Pos.CENTER_LEFT);
        lophocBtn.setGraphic(getLogoutIcon()); lophocBtn.setAlignment(Pos.CENTER_LEFT);
        tlkhoahocBtn.setGraphic(getStudentIcon()); tlkhoahocBtn.setAlignment(Pos.CENTER_LEFT);

        hvBtn.setStyle(menuFormat);
        khoaHocBtn.setStyle(menuFormat);
        lichHocBtn.setStyle(menuFormat);
        taiLieuBtn.setStyle(menuFormat);
        giangvienBtn.setStyle(menuFormat);
        phonghocBtn.setStyle(menuFormat);
        lophocBtn.setStyle(menuFormat);
        tlkhoahocBtn.setStyle(menuFormat);

        // === Thiếp lập nội dung cụ thể cho từng chức năng (bên phải app)

        VBox leftVb = new VBox(10);
        leftVb.setPrefWidth(280);
        leftVb.setPadding(new Insets(10));
        leftVb.setAlignment(Pos.TOP_LEFT);
        leftVb.getStyleClass().add("leftVb");

        hvBtn.prefWidthProperty().bind(leftVb.widthProperty());
        khoaHocBtn.prefWidthProperty().bind(leftVb.widthProperty());
        lichHocBtn.prefWidthProperty().bind(leftVb.widthProperty());
        taiLieuBtn.prefWidthProperty().bind(leftVb.widthProperty());
        giangvienBtn.prefWidthProperty().bind(leftVb.widthProperty());
        phonghocBtn.prefWidthProperty().bind(leftVb.widthProperty());
        lophocBtn.prefWidthProperty().bind(leftVb.widthProperty());
        tlkhoahocBtn.prefWidthProperty().bind(leftVb.widthProperty());

        leftVb.getChildren().addAll(titleQuanLyHb, hvBtn, giangvienBtn, khoaHocBtn,phonghocBtn, lophocBtn, lichHocBtn, taiLieuBtn, tlkhoahocBtn);

//        BorderPane borderPane = new BorderPane();
//        borderPane.setLeft(leftVb);

        rootBp.setTop(banner);
        rootBp.setLeft(leftVb);
        scene = new Scene(rootBp, 900, 750);
        scene.getStylesheets().add(getClass().getResource("/css/GiangVienUI.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
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
}
