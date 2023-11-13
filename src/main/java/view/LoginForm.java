package view;

import controller.AdminController;
import controller.LichHocController;
import controller.LoginController;
import controller.TaiLieuController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.CharacterStringConverter;
import model.HocVien;

import java.io.InputStream;

public class LoginForm extends Application {
    private LoginController loginController = new LoginController();
    private TextField username;
    private PasswordField password;
    private Label usernameError;
    private Label passwordError;
    private String titleFormat = "-fx-background-color: white; -fx-text-fill: black; -fx-font-weight:800;-fx-font-size:48px; ";

    private String font = "-fx-font-size:16px; ";

    private String format = "-fx-background-color: white; -fx-text-fill: black;";

    public static void main(String[] agrs) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        showLoginForm();
    }

    public void showLoginForm() {
        VBox formLogin = new VBox();
        formLogin.setStyle("-fx-background-color: #203864;");
        HBox header = new HBox();

        VBox content = new VBox();
        username = new TextField();
        password = new PasswordField();
        Button login = new Button("Login");

        Label titleLogin = new Label("Welcome to");
        titleLogin.setStyle(titleFormat);
        titleLogin.setMinHeight(45);
        titleLogin.setAlignment(Pos.CENTER);
        titleLogin.setBorder(new Border(new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        titleLogin.prefWidthProperty().bind(formLogin.widthProperty());

        header.getChildren().add(titleLogin);


        InputStream input = getClass().getResourceAsStream("/images/Title_new.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(500);
        HBox titleImage = new HBox(imageView);
        titleImage.prefHeight(200);
        titleImage.prefWidth(500);
        titleImage.setAlignment(Pos.CENTER);

        username.setPromptText("username");
        username.setMinWidth(300);
        username.setMinHeight(50);
        username.setStyle(font);
        usernameError = new Label();
        usernameError.setStyle("-fx-font-size:16px; -fx-text-fill: red;");

        password.setPromptText("password");
        password.setMinWidth(300);
        password.setMinHeight(50);
        password.setStyle(font);

        passwordError = new Label();
        passwordError.setStyle("-fx-font-size:16px; -fx-text-fill: red;");

        content.getChildren().addAll(username, usernameError, password, passwordError);
        content.prefWidth(300);
        content.prefHeight(400);
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setPadding(new Insets(50, 100, 50, 100));

        HBox foooter = new HBox();
        foooter.setAlignment(Pos.CENTER);

        login.setStyle(format + "-fx-font-size:32px; ");
        login.setMinHeight(75);
        login.setMinWidth(350);
        login.setOnAction(event -> {
            if (usernameError.getText().isEmpty() && passwordError.getText().isEmpty()) {
                Login(username.getText(), password.getText());
            } else {
                reset();
                Login(username.getText(), password.getText());
            }

        });
        foooter.getChildren().add(login);

        formLogin.getChildren().addAll(header, titleImage, content, foooter);


        Scene nscene = new Scene(formLogin, 550, 700);

        Stage newStage = new Stage();
        newStage.setScene(nscene);
        newStage.show();
    }

    private int validate() {
        if (username.getText().isEmpty()) {
            return -1;
        } else if (password.getText().isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    private void Login(String user, String pass) {
        int valid = validate();
        if (valid == -1) {
            usernameError.setText("*Username không được để trống");

        } else if (valid == 0) {
            passwordError.setText("*Mật khẩu không được để trống");

        } else {
            int check = loginController.checkLogin(user, pass);
            System.out.println(check);
            if (check == -1) {
                usernameError.setText("*Tài khoản không tồn tại");

            } else if (check == 0) {
                passwordError.setText("*Sai mật khẩu");

            } else {

                try {
                    Stage currentStage = (Stage) username.getScene().getWindow();
                    currentStage.close();
                    HocVien model = new HocVien();
                    AdminUI view = new AdminUI(new Stage());
                    AdminController controller = new AdminController(model, view);

                    controller.loadData();
                    view.show();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private void reset() {
        usernameError.setText("");
        passwordError.setText("");

    }

}
