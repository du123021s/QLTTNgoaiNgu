package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginService extends ConnectMySQLServer{

    public int login (String  username, String password) throws SQLException {
        String sql = "SELECT matkhau FROM giangvien WHERE maGiangVien=?;";
        String hashedPassword;
        boolean passwordmatch;
        try {
            PreparedStatement ps = connectDB.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                hashedPassword = rs.getString(1);
                passwordmatch = BCrypt.checkpw(password, hashedPassword);

            }else {
                return -1;
            }
            if(!passwordmatch){
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
