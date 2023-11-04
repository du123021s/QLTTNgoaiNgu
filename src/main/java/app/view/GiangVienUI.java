package app.view;

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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

public class GiangVienUI extends Application {
    private BorderPane rootBp;
    private Scene scene;
    private Stage stage;

    private Button hvBtn, lichHocBtn, taiLieuBtn, khoaHocBtn, dangSuatBtn;
    private InputStream input;
    private Image img;
    private ImageView imgView;

    private String menuFormat = "-fx-background-color:#005864 ;-fx-text-fill:#FFFFFF;";
    private String menuEffect = "-fx-background-color:#F7FA5A;-fx-text-fill:#000000;";
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


        rootBp.setTop(menubar);
        menubar.getMenus().add(menu);

        // === Thiết lập phần biểu tượng app
        input = getClass().getResourceAsStream("/qlttnn/icons/logoRg3.png");
        img = new Image(input);
        stage.getIcons().add(img);


        // === Thiết lập layout các chức năng cho Giang viên sử dụng (bên trái app)
        // === Logo icon
        Text logoTitle = new Text("ABC");
        logoTitle.setFont(Font.font("SansSerif", FontWeight.BOLD, 47));
        HBox logoTitleHb = new HBox(10);
        logoTitleHb.setPadding(new Insets(15,0,15,0));
        logoTitleHb.getChildren().addAll(getlogoIcon(), logoTitle);
        logoTitleHb.getStyleClass().add("logoTitleHb");

        // === Tạo đường kẻ ngang
        Line horizontalLine = new Line(0, 1, 255, 1);
        horizontalLine.setStroke(Color.GRAY);
            /*Tạo một Pane để đặt đường kẻ vào */
        Pane paneLine = new Pane(horizontalLine);
        paneLine.setPadding(new Insets(10,0,15,0));

        // === Chức năng
        hvBtn = new Button("Quản Lý Học Viên");
        khoaHocBtn = new Button("Xem Khóa Học");
        lichHocBtn = new Button("Xem Lịch Dạy");
        taiLieuBtn = new Button("Xem Tài Liệu");
        dangSuatBtn = new Button("Đăng Suất");

        hvBtn.setGraphic(getStudentIcon()); hvBtn.setAlignment(Pos.CENTER_LEFT);
        khoaHocBtn.setGraphic(getCourseIcon()); khoaHocBtn.setAlignment(Pos.CENTER_LEFT);
        lichHocBtn.setGraphic(getTimeTableIcon()); lichHocBtn.setAlignment(Pos.CENTER_LEFT);
        taiLieuBtn.setGraphic(getFileIcon()); taiLieuBtn.setAlignment(Pos.CENTER_LEFT);
        dangSuatBtn.setGraphic(getLogoutIcon()); dangSuatBtn.setAlignment(Pos.CENTER_LEFT);

        hvBtn.setStyle(menuFormat);
        khoaHocBtn.setStyle(menuFormat);
        lichHocBtn.setStyle(menuFormat);
        taiLieuBtn.setStyle(menuFormat);
        dangSuatBtn.setStyle(menuFormat);
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
        dangSuatBtn.prefWidthProperty().bind(leftVb.widthProperty());

        leftVb.getChildren().addAll(logoTitleHb, paneLine,  hvBtn, khoaHocBtn, lichHocBtn, taiLieuBtn, dangSuatBtn);

        rootBp.setLeft(leftVb);
        scene = new Scene(rootBp, 900, 750);
        scene.getStylesheets().add(getClass().getResource("/qlttnn/css/GiangVienUI.css").toExternalForm());
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

        dangSuatBtn.setOnMouseEntered((event) -> {
            dangSuatBtn.setStyle(menuEffect);
        });

        dangSuatBtn.setOnMouseExited((event) -> {
            dangSuatBtn.setStyle(menuFormat);
        });
    }

    public ImageView getlogoIcon(){
        input = getClass().getResourceAsStream("/qlttnn/icons/logoRg3.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(62);
        imgView.setFitWidth(132);
        return imgView;
    }

    public ImageView getStudentIcon(){
        input = getClass().getResourceAsStream("/qlttnn/icons/student.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getFileIcon(){
        input = getClass().getResourceAsStream("/qlttnn/icons/file.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getCourseIcon(){
        input = getClass().getResourceAsStream("/qlttnn/icons/course.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getTimeTableIcon(){
        input = getClass().getResourceAsStream("/qlttnn/icons/timetable.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }

    public ImageView getLogoutIcon(){
        input = getClass().getResourceAsStream("/qlttnn/icons/log-out.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(35);
        imgView.setFitWidth(35);
        return imgView;
    }
}
