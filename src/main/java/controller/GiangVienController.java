package controller;

import model.GiangVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils;
import java.security.NoSuchAlgorithmException;

public class GiangVienController extends GiangVien {
    public GiangVien getGiangVien() throws SQLException {
        GiangVien gv = new GiangVien();
        String sql = "SELECT * FROM giangvien;";
        PreparedStatement ps = con.connectDB.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            gv.maGiangVien = rs.getString("maGiangVien");
            gv.hoten = rs.getString("hoten");
            gv.gioitinh = rs.getString("gioitinh");
            gv.ngaysinh = rs.getDate("ngaysinh");
            gv.sdt = rs.getString("sdt");
            gv.email = rs.getString("email");
            gv.chuyennganh = rs.getString("chuyennganh");
            gv.kinhnghiemgd = rs.getInt("kinhnghiemgd");
            gv.matkhau = rs.getString("matkhau");
            gv.ngaybatdau = rs.getDate("ngaybatdau");
            gv.ngayketthuc = rs.getDate("ngayketthuc");
            gv.avatar = rs.getString("avatar");
            gv.is_admin = rs.getInt("is_admin");
            gv.trangthai = rs.getString("trangthai");
        }
        rs.close();
        return gv;
    }

    public String login(String username, String password) throws SQLException {
        String sql = "SELECT maGiangVien, hoten, matkhau FROM giangvien WHERE maGiangVien = ?";
        PreparedStatement ps = con.connectDB.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        String name = rs.getString("hoten");
        String pass = rs.getString("matkhau");
        String passHash = DigestUtils.sha256Hex(password);
        if (name=="")
            return "Tài khoản không tồn tại";
        else if (pass == passHash)
            return username;
        else
            return "Sai mat khau";
    }
}
