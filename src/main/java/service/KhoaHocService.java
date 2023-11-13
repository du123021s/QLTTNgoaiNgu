package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.HocVien;
import model.KhoaHoc;

import java.sql.*;
import java.time.LocalDate;

public class KhoaHocService extends ConnectMySQLServer {

    public ObservableList<KhoaHoc> getKhoaHocList() {
        ObservableList<KhoaHoc> khoaHocs = FXCollections.observableArrayList();
        String statusDefault = "Kết thúc";
        String sql = "SELECT maKhoaHoc, ten, mota, thoigianhoc, hocphi, ngaybatdau, " +
                "ngayketthuc, soluonghv_toida, trangthai FROM KhoaHoc " +
                "WHERE trangthai NOT LIKE ? ;";
        try {
            connectDB.setAutoCommit(false); // Bắt đầu transaction
            try (PreparedStatement preState = connectDB.prepareStatement(sql);) {
                preState.setString(1, statusDefault);
                try (ResultSet resultSet = preState.executeQuery();) {
                    while (resultSet.next()) {
                        KhoaHoc khoaHoc = new KhoaHoc();
                        int soluongConLai = kiemTraSoLuongHVConLai(resultSet.getString(1));

                        khoaHoc.setMaKH(resultSet.getString(1));
                        khoaHoc.setTenKH(resultSet.getString(2));
                        khoaHoc.setMotaKH(resultSet.getString(3));
                        khoaHoc.setThoiGianHocKH(resultSet.getInt(4));
                        khoaHoc.setHocPhiKH(resultSet.getDouble(5));
                        khoaHoc.setNgayBatDauKH(resultSet.getDate(6).toLocalDate());
                        khoaHoc.setNgayKetThucKH(resultSet.getDate(7).toLocalDate());

                        System.out.println("sl: " + resultSet.getInt(8));
                        khoaHoc.setSoLuongHVToiDa(resultSet.getInt(8));

                        if (soluongConLai > 0) {
                            khoaHoc.setSoLuongHVHienTai(soluongConLai);
                        } else {
                            khoaHoc.setSoLuongHVHienTai(resultSet.getInt(8));
                        }

                        khoaHoc.setTrangThaiKH(resultSet.getString(9));
                        khoaHocs.add(khoaHoc);
                    }

                    connectDB.commit(); // Kết thúc transaction, commit các thay đổi

                }
            } catch (SQLException e) {
                if (connectDB != null && !connectDB.isClosed()) {
                    System.out.println("KET NOI THANH CONG");
                    connectDB.rollback(); // Nếu có lỗi, rollback transaction
                } else {
                    System.out.println("KET NOI DA BI DONG TRC ĐÓ.");
                }
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return khoaHocs;
    }


    public int kiemTraSoLuongHVConLai(String maKH) {
        int remainingSlots = 0;
        String sql = "{CALL GetRemainingSlots(?) }";
        try (CallableStatement call = connectDB.prepareCall(sql)) {
            call.setString(1, maKH);
            boolean hasResults = call.execute();

            if (hasResults) {
                try (ResultSet resultSet = call.getResultSet()) {
                    while (resultSet.next()) {
                        remainingSlots = resultSet.getInt("SoLuongConLai");
                        System.out.println("Remaining Slots: " + remainingSlots);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return remainingSlots;
    }

    public ObservableList<KhoaHoc> searchKhoaHoc(String keywork) {
        ObservableList<KhoaHoc> khoaHocs = FXCollections.observableArrayList();
        String sql = "SELECT maKhoaHoc, ten, mota, thoigianhoc, hocphi, ngaybatdau, ngayketthuc, soluonghv_toida, trangthai FROM KhoaHoc\n" +
                "WHERE maKhoaHoc LIKE ? \n" +
                "\t\tOR ten LIKE ? \n" +
                "\t\tOR mota LIKE ? \n" +
                "\t\tOR thoigianhoc LIKE ?  \n" +
                "\t\tOR hocphi LIKE ? \n" +
                "\t\tOR ngaybatdau LIKE ? \n" +
                "\t\tOR ngayketthuc LIKE ?  \n" +
                "\t\tOR soluonghv_toida LIKE ? \n" +
                "\t\tOR trangthai LIKE ?";

        try (PreparedStatement preState = connectDB.prepareStatement(sql);){
            for (int i = 1; i <= 9; i++) {
                preState.setString(i, "%" + keywork + "%");
            }
            try(ResultSet resultSet = preState.executeQuery()){

                while (resultSet.next()) {
                    KhoaHoc khoaHoc = new KhoaHoc();
                    int soluongConLai = kiemTraSoLuongHVConLai(resultSet.getString(1));
                    khoaHoc.setMaKH(resultSet.getString(1));
                    khoaHoc.setTenKH(resultSet.getString(2));
                    khoaHoc.setMotaKH(resultSet.getString(3));
                    khoaHoc.setThoiGianHocKH(resultSet.getInt(4));
                    khoaHoc.setHocPhiKH(resultSet.getDouble(5));
                    khoaHoc.setNgayBatDauKH(resultSet.getDate(6).toLocalDate());
                    khoaHoc.setNgayKetThucKH(resultSet.getDate(7).toLocalDate());
                    if (soluongConLai > 0) {
                        khoaHoc.setSoLuongHVHienTai(soluongConLai);
                    } else {
                        khoaHoc.setSoLuongHVHienTai(resultSet.getInt(8));
                    }
                    khoaHoc.setSoLuongHVToiDa(resultSet.getInt(8));
                    khoaHoc.setTrangThaiKH(resultSet.getString(9));
                    khoaHocs.add(khoaHoc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khoaHocs;
    }

    public int kiemTraMaKhoaHoc(String maKH) throws SQLException {
        PreparedStatement preState = null;
        ResultSet result = null;
        try {
            String sql = "SELECT maKhoaHoc FROM KhoaHoc WHERE maKhoaHoc = ? ;";
            preState = connectDB.prepareStatement(sql);
            preState.setString(1, maKH);
            result = preState.executeQuery();

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

    public int deleteKhoaHoc(String maKH) {
        String sql = "UPDATE KhoaHoc\n" +
                "SET trangthai = 'Kết thúc' \n" +
                "WHERE maKhoaHoc = ?";

        try {
            if (connectDB.isClosed()) {
                System.out.println("KET NOI ĐÃ BỊ ĐÓNG. ");
            }
            connectDB.setAutoCommit(false);  // Bắt đầu giao dịch

            try (PreparedStatement preState = connectDB.prepareStatement(sql)) {
                preState.setString(1, maKH);
                preState.executeUpdate();

                connectDB.commit();  // Không lỗi thi commit
                return 1;

            } catch (SQLException e) {
                connectDB.rollback();  // Lỗi xảy ra rollbacl
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateKhoaHoc(KhoaHoc khoaHoc) {
        String sql = "{CALL UpdateKhoaHoc(?,?,?,?,?,?,?,?,?)}";

        try {
            connectDB.setAutoCommit(true);
            try (CallableStatement callState = connectDB.prepareCall(sql)) {
                // Truyền giá trị vào stored procedure
                callState.setString(1, khoaHoc.getMaKH());
                callState.setString(2, khoaHoc.getTenKH());
                callState.setString(3, khoaHoc.getMotaKH());
                callState.setInt(4, khoaHoc.getThoiGianHocKH());
                callState.setDouble(5, khoaHoc.getHocPhiKH());
                callState.setDate(6, Date.valueOf(khoaHoc.getNgayBatDauKH()));
                callState.setDate(7, Date.valueOf(khoaHoc.getNgayKetThucKH()));
                callState.setInt(8, khoaHoc.getSoLuongHVToiDa());
                callState.setString(9, khoaHoc.getTrangThaiKH());

                // Thực thi stored procedure
                callState.executeUpdate();

                // Commit transaction
                connectDB.commit();
                return 1;

            } catch (SQLException e) {

                connectDB.rollback();
                e.printStackTrace();

            } finally {
                connectDB.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertKhoaHocMoi(KhoaHoc khoaHoc) {
        String sql = "CALL InsertKhoaHoc(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        CallableStatement preState = null;
        try {
            if (connectDB.isClosed()) {
                System.out.println("KET NOI ĐÃ BỊ ĐÓNG. ");
            }
            connectDB.setAutoCommit(false);
            preState = connectDB.prepareCall(sql);

            preState.setString(1, khoaHoc.getMaKH());
            preState.setString(2, khoaHoc.getTenKH());
            preState.setString(3, khoaHoc.getMotaKH());
            preState.setInt(4, khoaHoc.getThoiGianHocKH());
            preState.setDouble(5, khoaHoc.getHocPhiKH());
            preState.setDate(6, Date.valueOf(khoaHoc.getNgayBatDauKH()));
            preState.setDate(7, Date.valueOf(khoaHoc.getNgayKetThucKH()));
            preState.setInt(8, khoaHoc.getSoLuongHVToiDa());
            preState.setString(9, khoaHoc.getTrangThaiKH());

            preState.executeUpdate();
            connectDB.commit();
            return 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0; // Trả về 0 nếu có lỗi
        } finally {
            try {
                if (preState != null) {
                    preState.close();
                }
                if (!connectDB.isClosed()) {
                    connectDB.setAutoCommit(true);
                    connectDB.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
