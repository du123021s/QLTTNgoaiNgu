package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.HocVien;
import model.LopHoc;


import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

public class HocVienService extends ConnectMySQLServer {


    public ObservableList<HocVien> getStudentList() {
        String gioitinh;
        ObservableList<HocVien> hocVienList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT maHocVien, hoten, gioitinh, ngaysinh, sdt, "
                    + "email, diachi, ngaydangky, maLop, trangthai FROM HocVien " +
                    "WHERE trangthai NOT LIKE 'Hoàn thành';";

            PreparedStatement preState = connectDB.prepareStatement(sql);
            ResultSet resultSet = preState.executeQuery();

            while (resultSet.next()) {
                HocVien hocVien = new HocVien();
                LopHoc lop = new LopHoc();
                hocVien.setMaHV(resultSet.getString(1));
                hocVien.setHoTenHV(resultSet.getString(2));
                gioitinh = resultSet.getString(3);
                if ("F".equals(gioitinh)) {
                    hocVien.setGioiTinhHV("Nữ");
                } else {
                    hocVien.setGioiTinhHV("Nam");
                }

                hocVien.setNgaySinhHV(resultSet.getDate(4).toLocalDate());
                hocVien.setSdtHV(resultSet.getString(5));
                hocVien.setEmailHV(resultSet.getString(6));
                hocVien.setDiaChiHV(resultSet.getString(7));
                hocVien.setNgayDangKyHoc(resultSet.getDate(8).toLocalDate());
                lop.setMaLop(resultSet.getString(9));
                hocVien.setMaLop(lop);
                hocVien.setTrangThaiHV(resultSet.getString(10));


                hocVienList.add(hocVien);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return hocVienList;
    }

    public int dangkyHocVienMoi(HocVien hocVien) {
        String maHv = hocVien.getMaHV();
        String hotenHv = hocVien.getHoTenHV();
        String gioiTinhHv = hocVien.getGioiTinhHV();
        LocalDate ngaySinhHv = hocVien.getNgaySinhHV();
        String sdtHv = hocVien.getSdtHV();
        String email = hocVien.getEmailHV();
        String diaChiHv = hocVien.getDiaChiHV();
        String trangThaiHv = hocVien.getTrangThaiHV();
        String maLop = hocVien.getMaLop().getMaLop();
        PreparedStatement preState = null;

        System.out.println("DIA CHI: " + diaChiHv);
        try {
            connectDB.setAutoCommit(false);
            String sql = "INSERT INTO HocVien (maHocVien, hoten, gioitinh, ngaysinh, sdt, email, " +
                    "diachi, trangthai, maLop) VALUES (?,?,?,?,?,?,?,?,?)";
            preState = connectDB.prepareStatement(sql);

            preState.setString(1, maHv);
            preState.setString(2, hotenHv);
            preState.setString(3, gioiTinhHv);
            preState.setDate(4, Date.valueOf(ngaySinhHv));
            preState.setString(5, sdtHv);
            preState.setString(6, email);
            preState.setString(7, diaChiHv);
            preState.setString(8, trangThaiHv);
            preState.setString(9, maLop);

            preState.executeUpdate();
            connectDB.commit();
            return 1;

        } catch (SQLException e) {
            if (connectDB != null) {
                try {
                    connectDB.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connectDB != null) {
                try {
                    preState.close();
                    connectDB.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public int kiemTraHocVien(String maHV) {
        try {
            String sql = "SELECT maHocVien From HocVien WHERE maHocVien = ?;";
            PreparedStatement preState = connectDB.prepareStatement(sql);
            preState.setString(1, maHV);

            ResultSet result = preState.executeQuery();
            if (result.next()) {
                return 1;
            } else {
                System.out.println("Không tìm thấy");
                return 0;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // Hàm cập nhật HocVien bằng stored procedure
    public int updateHocVien(HocVien hocVien) {
        String sql = "{CALL UpdateHocVien(?,?,?,?,?,?,?,?,?)}";

        try (CallableStatement callState = connectDB.prepareCall(sql)) {
            connectDB.setAutoCommit(false);

            // Truyền giá trị vào stored procedure
            callState.setString(1, hocVien.getMaHV());
            callState.setString(2, hocVien.getHoTenHV());
            callState.setString(3, hocVien.getGioiTinhHV());
            callState.setDate(4, Date.valueOf(hocVien.getNgaySinhHV()));
            callState.setString(5, hocVien.getSdtHV());
            callState.setString(6, hocVien.getEmailHV());
            callState.setString(7, hocVien.getDiaChiHV());
            callState.setString(8, hocVien.getTrangThaiHV());
            callState.setString(9, hocVien.getMaLop().getMaLop());

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

    public boolean deleteHocVien(String maHv) {
        boolean success = false;
        String sql = "SELECT  updateTrangThaiHV(?) as result;";
        try (PreparedStatement call = connectDB.prepareStatement(sql)) {
            call.setString(1, maHv);
            try (ResultSet resultSet = call.executeQuery();) {
                if(resultSet.next()){
                    success = resultSet.getBoolean(1);
                    System.out.println("Result: " + success);

                }

                connectDB.setAutoCommit(false);
                connectDB.commit();
                return success;
            }
        } catch (SQLException e) {
            if (connectDB != null) {
                try {
                    connectDB.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        }
        return success;
    }

    public ObservableList<HocVien> searchHocVien(String content) {
        String gioitinh;
        ObservableList<HocVien> hocVienList = FXCollections.observableArrayList();
        String addContent = '%' + content + '%';
        String sql = "SELECT maHocVien, hoten, gioitinh, ngaysinh, sdt, email, diachi, maLop, trangthai " +
                "FROM HocVien " +
                "WHERE maHocVien LIKE ? " +
                "or hoten LIKE ? " +
                "or maHocVien LIKE ? " +
                "or gioitinh LIKE ? " +
                "or diachi LIKE ? " +
                "or maLop LIKE ? " +
                "or trangthai LIKE ? ";
        try (PreparedStatement preState = connectDB.prepareStatement(sql);) {
            for (int i = 1; i <= 7; i++) {
                preState.setString(i, addContent);
            }
            ResultSet resultSet = preState.executeQuery();

            while (resultSet.next()) {
                HocVien hocVien = new HocVien();
                LopHoc lop = new LopHoc();
                hocVien.setMaHV(resultSet.getString(1));
                hocVien.setHoTenHV(resultSet.getString(2));
                System.out.println("Ho ten: " + resultSet.getString(2));
                gioitinh = resultSet.getString(3);
                if ("F".equals(gioitinh)) {
                    hocVien.setGioiTinhHV("Nữ");
                } else {
                    hocVien.setGioiTinhHV("Nam");
                }

                hocVien.setNgaySinhHV(resultSet.getDate(4).toLocalDate());
                hocVien.setSdtHV(resultSet.getString(5));
                hocVien.setEmailHV(resultSet.getString(6));
                hocVien.setDiaChiHV(resultSet.getString(7));
                lop.setMaLop(resultSet.getString(8));
                hocVien.setMaLop(lop);
                hocVien.setTrangThaiHV(resultSet.getString(9));


                hocVienList.add(hocVien);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return hocVienList;
    }
}

