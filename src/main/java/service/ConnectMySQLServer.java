/**
 * @Auther:
 * @Since: 9/10/2023
 * @Content: Class này dùng để kết nối đến database.
 * @Detail: url: đường dẫn cục bộ truy cập đến database TrungTamNgoaiNgu
 *          user: root (loại tải khoản trong database)
 *          password: mật khẩu truy cập vào database (hãy sửa lại cho đúng với pass của bạn.
 *          TH: Nếu bạn không thiết lập password khi truy cap vào DB thì để trống trường
 *           password phía dưới nhé.
 * @Note: Hãy đảm bảo rằng bạn đã mở MySQL và tạo database TrungTamNgoaiNgu trong MySQL.
 */

package service;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQLServer {
    public Connection connectDB = null ;
    private final String url = "jdbc:mysql://localhost:3306/qlttnn";
    private final String user = "root";
    private final String password = "Nhansu123@";

    public ConnectMySQLServer(){
        try {
            connectDB = DriverManager.getConnection(url, user, password);

//            if(connectDB != null){
//                JOptionPane.showMessageDialog(null, "Connection successful.");

            if(connectDB != null){
                System.out.println("Successful");
            }
        } catch (SQLException e) {
            System.out.println("Failed");
        }
    }
}
