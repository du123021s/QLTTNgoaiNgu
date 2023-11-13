package model;

import java.time.LocalDate;
import java.util.List;

public class LopHoc {
    private String maLop;
    private Integer soluongHvCuaLop;
    private String trangThaiLop;
    private LocalDate lopTaoNgay;

    private KhoaHoc khoaHoc;
    private List<HocVien> dsHocVien;
    private GiangVien giangVien;
    private LichHoc lichHoc;

    public LopHoc() {
    }

    public LopHoc(String maLop, Integer soluongHvCuaLop, String trangThaiLop, LocalDate lopTaoNgay) {
        this.maLop = maLop;
        this.soluongHvCuaLop = soluongHvCuaLop;
        this.trangThaiLop = trangThaiLop;
        this.lopTaoNgay = lopTaoNgay;
    }

    public LopHoc(String maLop, Integer soluongHvCuaLop, String trangThaiLop, LocalDate lopTaoNgay, KhoaHoc khoaHoc, List<HocVien> dsHocVien, GiangVien giangVien, LichHoc lichHoc) {
        this.maLop = maLop;
        this.soluongHvCuaLop = soluongHvCuaLop;
        this.trangThaiLop = trangThaiLop;
        this.lopTaoNgay = lopTaoNgay;
        this.khoaHoc = khoaHoc;
        this.dsHocVien = dsHocVien;
        this.giangVien = giangVien;
        this.lichHoc = lichHoc;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public Integer getSoluongHvCuaLop() {
        return soluongHvCuaLop;
    }

    public void setSoluongHvCuaLop(Integer soluongHvCuaLop) {
        this.soluongHvCuaLop = soluongHvCuaLop;
    }

    public String getTrangThaiLop() {
        return trangThaiLop;
    }

    public void setTrangThaiLop(String trangThaiLop) {
        this.trangThaiLop = trangThaiLop;
    }

    public LocalDate getLopTaoNgay() {
        return lopTaoNgay;
    }

    public void setLopTaoNgay(LocalDate lopTaoNgay) {
        this.lopTaoNgay = lopTaoNgay;
    }

    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public List<HocVien> getDsHocVien() {
        return dsHocVien;
    }

    public void setDsHocVien(List<HocVien> dsHocVien) {
        this.dsHocVien = dsHocVien;
    }

    public GiangVien getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }

    public LichHoc getLichHoc() {
        return lichHoc;
    }

    public void setLichHoc(LichHoc lichHoc) {
        this.lichHoc = lichHoc;
    }

    public String toString(){
        return this.maLop;
    }
}
