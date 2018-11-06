package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import java.io.Serializable;

public class Message implements Serializable {
    private int idTinNhan, idKhachHang;
    private String thoiGian, noiDung, anhDaiDien;

    public Message(int idTinNhan, int idKhachHang, String anhDaiDien, String thoiGian, String noiDung) {
        this.idTinNhan = idTinNhan;
        this.idKhachHang = idKhachHang;
        this.anhDaiDien=anhDaiDien;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
    }

    public Message() {
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public int getIdTinNhan() {
        return idTinNhan;
    }

    public void setIdTinNhan(int idTinNhan) {
        this.idTinNhan = idTinNhan;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
