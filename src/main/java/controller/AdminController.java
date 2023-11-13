package controller;

import javafx.application.Platform;
import model.HocVien;
import view.*;

public class AdminController {
    private AdminUI view;
    private HocVien model;


    public AdminController(HocVien model, AdminUI view){
        this.model = model;
        this.view = view;
    }

    public void loadData(){
        changeColorBtn();
        moveToTaiLieuUI();
        moveToGiangVienUI();
        moveToHocVienUI();
        moveToKhoaHocUI();
        moveToLichHocUI();
        moveToLopHoc();
        moveToPhongHocUI();
        moveToTaiLieuKhoaHocUI();
    }


    /** ========================= XU LY CHUYEN GIAO DIEN =====================================*/
    private void moveToTaiLieuKhoaHocUI() {
        this.view.tlkhoahocBtn.setOnAction(event -> {
            TaiLieuKhoaHocUI taiLieuKhoaHocUI = new TaiLieuKhoaHocUI();
            this.view.rootBp.setCenter(taiLieuKhoaHocUI);
        });
    }

    private void moveToLichHocUI() {
        this.view.lichHocBtn.setOnAction(event -> {
            LichHocUI lichHocUI = new LichHocUI();
            this.view.rootBp.setCenter(lichHocUI);
        });
    }

    private void moveToLopHoc() {
        this.view.lophocBtn.setOnAction(event -> {
            LopHocUI lopHocUI = new LopHocUI();
            this.view.rootBp.setCenter(lopHocUI);
        });

    }

    private void moveToPhongHocUI() {
        this.view.phonghocBtn.setOnAction(event -> {
            PhongHocUI phongHocUI = new PhongHocUI();
            this.view.rootBp.setCenter(phongHocUI);
        });
    }

    private void moveToTaiLieuUI() {
        this.view.taiLieuBtn.setOnAction(event -> {
            TaiLieuUI taiLieuUI = new TaiLieuUI();
            this.view.rootBp.setCenter(taiLieuUI);
        });
    }

    private void moveToKhoaHocUI() {
        this.view.khoaHocBtn.setOnAction(event -> {
            KhoaHocUI khoaHocUI = new KhoaHocUI();
            this.view.rootBp.setCenter(khoaHocUI);
        });
    }

    private void moveToGiangVienUI() {
        this.view.giangvienBtn.setOnAction(event -> {
            GiangVienUI giangVienUI = new GiangVienUI();
            this.view.rootBp.setCenter(giangVienUI);
        });
    }

    private void moveToHocVienUI() {
        this.view.hvBtn.setOnAction(event -> {
            this.view.hocvien = new HocVienUI();
            this.view.rootBp.setCenter(this.view.hocvien);
        });
    }


    /**
     * HÀM XỬ LÝ MÀU SẮC KHI CHUYỂN BUTTON
     * */
    protected void changeColorBtn(){
        this.view.hvBtn.setOnMouseEntered((event) -> {
            this.view.hvBtn.setStyle(this.view.menuEffect);
        });

        this.view.hvBtn.setOnMouseExited((event) -> {
            this.view.hvBtn.setStyle(this.view.menuFormat);
        });

        this.view.khoaHocBtn.setOnMouseEntered((event) -> {
            this.view.khoaHocBtn.setStyle(this.view.menuEffect);
        });

        this.view.khoaHocBtn.setOnMouseExited((event) -> {
            this.view.khoaHocBtn.setStyle(this.view.menuFormat);
        });

        this.view.lichHocBtn.setOnMouseEntered((event) -> {
            this.view.lichHocBtn.setStyle(this.view.menuEffect);
        });

        this.view.lichHocBtn.setOnMouseExited((event) -> {
            this.view.lichHocBtn.setStyle(this.view.menuFormat);
        });

        this.view.taiLieuBtn.setOnMouseEntered((event) -> {
            this.view.taiLieuBtn.setStyle(this.view.menuEffect);
        });

        this.view.taiLieuBtn.setOnMouseExited((event) -> {
            this.view.taiLieuBtn.setStyle(this.view.menuFormat);
        });

        this.view.giangvienBtn.setOnMouseEntered((event) -> {
            this.view.giangvienBtn.setStyle(this.view.menuEffect);
        });

        this.view.giangvienBtn.setOnMouseExited((event) -> {
            this.view.giangvienBtn.setStyle(this.view.menuFormat);
        });
        this.view.phonghocBtn.setOnMouseEntered((event) -> {
            this.view.phonghocBtn.setStyle(this.view.menuEffect);
        });

        this.view.phonghocBtn.setOnMouseExited((event) -> {
            this.view.phonghocBtn.setStyle(this.view.menuFormat);
        });
        this.view.lophocBtn.setOnMouseEntered((event) -> {
            this.view.lophocBtn.setStyle(this.view.menuEffect);
        });

        this.view.lophocBtn.setOnMouseExited((event) -> {
            this.view.lophocBtn.setStyle(this.view.menuFormat);
        });
        this.view.tlkhoahocBtn.setOnMouseEntered((event) -> {
            this.view.tlkhoahocBtn.setStyle(this.view.menuEffect);
        });

        this.view.tlkhoahocBtn.setOnMouseExited((event) -> {
            this.view.tlkhoahocBtn.setStyle(this.view.menuFormat);
        });
    }

}
