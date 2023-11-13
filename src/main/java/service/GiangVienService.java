package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GiangVien;
import model.LopHoc;

import java.sql.*;
import java.time.LocalDate;

public class GiangVienService extends ConnectMySQLServer {

    public ObservableList<GiangVien> getGiangVienList(){
        String gioitinh;
        ObservableList<GiangVien> giangVienList = FXCollections.observableArrayList();
        try {
            String sql = "Select maGiangVien, hoten, gioitinh, ngaysinh, diachi, sdt, email, chuyennganh, kinhnghiemgd, matkhau, "
                    + "ngaybatdau, ngayketthuc, avatar, is_admin, trangthai FROM GiangVien;";
            PreparedStatement preState = connectDB.prepareStatement(sql);
            ResultSet resultSet = preState.executeQuery();
            while(resultSet.next()) {
                GiangVien giangVien = new GiangVien();
                LopHoc lopHoc = new LopHoc();
                giangVien.setMaGiangVien(resultSet.getString(1));
                giangVien.setHoten(resultSet.getString(2));
                gioitinh = resultSet.getString(3);
                if ("F".equals(gioitinh)) {
                    giangVien.setGioitinh("Nữ");
                } else {
                    giangVien.setGioitinh("Nam");
                }
                giangVien.setNgaysinh(resultSet.getDate(4).toLocalDate());
                giangVien.setDiachi(resultSet.getString(5));
                giangVien.setSdt(resultSet.getString(6));
                giangVien.setEmail(resultSet.getString(7));
                giangVien.setChuyennganh(resultSet.getString(8));
                giangVien.setKinhnghiemgd(resultSet.getByte(9));
                giangVien.setMatkhau(resultSet.getString(10));
                giangVien.setNgaybatdau(resultSet.getDate(11).toLocalDate());
                giangVien.setNgayketthuc(resultSet.getDate(12).toLocalDate());
                giangVien.setAvatar(resultSet.getString(13));
                giangVien.setIs_admin(resultSet.getByte(14));
                giangVien.setTrangthai(resultSet.getString(15));

                giangVienList.add(giangVien);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return giangVienList;

    }
    public int kiemTraGiangVien(String maGV) {
        try {
            String sql = "SELECT maGiangVien From GiangVien WHERE maGiangVien = ?;";
            PreparedStatement preState = connectDB.prepareStatement(sql);

            ResultSet result = preState.executeQuery();
            if(result.next()) {
                return 1;
            }else {
                System.out.println("Không tìm thấy");
                return 0;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int dangKyGiangVienMoi(GiangVien giangVien){
        String maGV = giangVien.getMaGiangVien();
        String hotenGV = giangVien.getHoten();
        String gioiTinh = giangVien.getGioitinh();
        LocalDate ngaySinhGV = giangVien.getNgaysinh();
        String diaChiGV = giangVien.getDiachi();
        String sdt = giangVien.getSdt();
        String email = giangVien.getEmail();
        String chuyenNganh = giangVien.getChuyennganh();
        Integer kinhNghiem = giangVien.getKinhnghiemgd();
        String matkhau = giangVien.getMatkhau();
        LocalDate ngayBD = giangVien.getNgaybatdau();
        LocalDate ngayKT = giangVien.getNgayketthuc();
        String avatar = giangVien.getAvatar();
        Integer is_admin = giangVien.getIs_admin();
        String status = giangVien.getTrangthai();
        PreparedStatement preState = null;

        System.out.println("DIA CHI: " + diaChiGV);
        try{
            connectDB.setAutoCommit(false);
            String sql = "INSERT INTO GiangVien(maGiangVien, hoten, gioitinh, ngaysinh, diachi," +
                    " sdt, email, chuyennganh, kinhnghiemgd, matkhau, ngaybatdau, ngayketthuc, avatar, is_admin, trangthai) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            preState.setString(1, maGV);
            preState.setString(2, hotenGV);
            preState.setString(3,gioiTinh);
            preState.setDate(4,Date.valueOf(ngaySinhGV));
            preState.setString(5,diaChiGV);
            preState.setString(6,sdt);
            preState.setString(7,email);
            preState.setString(8,chuyenNganh);
            preState.setInt(9,kinhNghiem);
            preState.setString(10, matkhau);
            preState.setDate(11,Date.valueOf(ngayBD));
            preState.setDate(12,Date.valueOf(ngayKT));
            preState.setString(13,avatar);
            preState.setInt(14,is_admin);
            preState.setString(15,status);


        } catch ( SQLException e){
            if (connectDB != null){
                try {
                    connectDB.rollback();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connectDB!= null){
                try {
                    preState.close();
                    connectDB.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        return 0;
    }
    public int updateGiangVien(GiangVien giangVien) {
        String sql = "{CALL UpdateHocVien(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try (CallableStatement callState = connectDB.prepareCall(sql)) {
            connectDB.setAutoCommit(false);

            // Truyền giá trị vào stored procedure
            callState.setString(1, giangVien.getMaGiangVien());
            callState.setString(2, giangVien.getHoten());
            callState.setString(3, giangVien.getGioitinh());
            callState.setDate(4, Date.valueOf(giangVien.getNgaysinh()));
            callState.setString(5, giangVien.getDiachi());
            callState.setString(6, giangVien.getSdt());
            callState.setString(7, giangVien.getEmail());
            callState.setString(8, giangVien.getChuyennganh());
            callState.setInt(9, giangVien.getKinhnghiemgd());
            callState.setString(10, giangVien.getMatkhau());
            callState.setDate(11, Date.valueOf(giangVien.getNgaybatdau()));
            callState.setDate(12, Date.valueOf(giangVien.getNgayketthuc()));
            callState.setString(13, giangVien.getAvatar());
            callState.setInt(14, giangVien.getIs_admin());
            callState.setString(15, giangVien.getTrangthai());

            // Thực thi stored procedure
            callState.executeUpdate();

            // Commit transaction
            connectDB.commit();
            connectDB.setAutoCommit(true);

            return 1;
        } catch (SQLException e) {
            if (connectDB != null) {
                try {
                    // Rollback transaction nếu có lỗi
                    connectDB.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        } finally {
            try {
                // Đặt lại trạng thái tự động commit
                connectDB.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    public int deleteGiangVien(String maGV){
        String sql = "DELETE FROM GiangVien WHERE maGiangVien = ?;";
        try(PreparedStatement call = connectDB.prepareStatement(sql)){
            call.setString(1, maGV);

            call.executeUpdate();
            connectDB.setAutoCommit(false);
            connectDB.commit() ;
            return 1;

        }catch (SQLException e){
            if(connectDB != null){
                try {
                    connectDB.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        }
        return 0;
    }
    public ObservableList<GiangVien> searchHocVien(String content){
        String gioitinh;
        ObservableList<GiangVien> giangVienList = FXCollections.observableArrayList();
        String addContent = '%'+content+'%';
        String sql = "SELECT maGiangVien, hoten, gioitinh, ngaysinh, diachi, sdt, email, chuyennganh, kinhnghiemgd, matkhau, ngaybatdau," +
                "ngayketthuc, avatar, is_admin, trangthai " +
                "FROM GiangVien " +
                "WHERE maGiangVien LIKE ? " +
                "or hoten LIKE ? " +
                "or gioitinh LIKE ? " +
                "or ngaysinh LIKE ? " +
                "or diachi LIKE ? " +
                "or sdt LIKE ? " +
                "or email LIKE ? " +
                "or chuyennganh LIKE ?" +
                "or kinhnghiemgd LIKE ?" +
                "or matkhau LIKE ?" +
                "or ngaybatdau LIKE ?" +
                "or ngayketthuc LIKE ?" +
                "or avatar LIKE ?" +
                "or is_admin LIKE ?" +
                "or trangthai LIKE ?";
        try( PreparedStatement preState = connectDB.prepareStatement(sql);){
            for(int i=1; i<=3; i++){
                preState.setString(i, addContent);
            }
            preState.setDate(4, Date.valueOf(addContent));
            for(int i=5 ; i<=8; i++){
                preState.setString(i, addContent);
            }
            preState.setInt(9, Integer.parseInt(addContent));
            preState.setString(10, addContent);
            preState.setDate(11, Date.valueOf(addContent));
            preState.setDate(12,Date.valueOf(addContent));
            preState.setString(13, addContent);
            preState.setInt(14, Integer.parseInt(addContent));
            preState.setString(15, addContent);

            ResultSet resultSet = preState.executeQuery();

            while(resultSet.next()){
                GiangVien giangVien = new GiangVien();
                LopHoc lop = new LopHoc();
                giangVien.setMaGiangVien(resultSet.getString(1));
                giangVien.setHoten(resultSet.getString(2));
                System.out.println("Ho ten: " + resultSet.getString(2));
                gioitinh = resultSet.getString(3);
                if ("F".equals(gioitinh)) {
                    giangVien.setGioitinh("Nữ");
                } else {
                    giangVien.setGioitinh("Nam");
                }

                giangVien.setNgaysinh(resultSet.getDate(4).toLocalDate());
                giangVien.setDiachi(resultSet.getString(5));
                giangVien.setSdt(resultSet.getString(6));
                giangVien.setEmail(resultSet.getString(7));
                giangVien.setChuyennganh(resultSet.getString(8));
                giangVien.setKinhnghiemgd(resultSet.getInt(9));
                giangVien.setMatkhau(resultSet.getString(10));
                giangVien.setNgaybatdau(resultSet.getDate(11).toLocalDate());
                giangVien.setNgayketthuc(resultSet.getDate(12).toLocalDate());
                giangVien.setAvatar(resultSet.getString(13));
                giangVien.setIs_admin(resultSet.getInt(14));
                giangVien.setTrangthai(resultSet.getString(15));


                giangVienList.add(giangVien);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return giangVienList;
    }
    }

