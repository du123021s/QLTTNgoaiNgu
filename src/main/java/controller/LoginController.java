package controller;

import javafx.stage.Stage;
import service.LoginService;
import view.LoginForm;

import java.sql.SQLException;

public class LoginController extends LoginService {
    public void showLoginForm() throws Exception {
        LoginForm loginForm = new LoginForm();
        Stage stage = new Stage();

        loginForm.start(stage);
    }

    public int checkLogin(String username, String password){
        LoginService loginService = new LoginService();
        try {
            int rs = loginService.login(username,password);
            System.out.println(rs);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
