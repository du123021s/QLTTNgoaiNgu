/**
 * @ Author:
 * @ Since: 09/10/2023
 * Content: class này dùng để chạy phần giao diện chính. Là lớp được chạy đầu tiên của APP
*/

package app;


import service.ConnectMySQLServer;

public class TestUI {

    public static void main(String[] agrs){
        /** Dòng này gọi đến class để kết nối với database; Đây chỉ là dòng lệnh mẫu
         * để test thử việc kết nối đến database;
         */
        ConnectMySQLServer test = new ConnectMySQLServer();
    }
}
