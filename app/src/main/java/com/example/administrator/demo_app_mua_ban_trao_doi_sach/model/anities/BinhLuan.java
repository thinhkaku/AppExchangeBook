package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import java.io.Serializable;

public class BinhLuan implements Serializable {
    private int id,idKhachHang;
    private String hinhDaiDien,ten;
    private String noiDung,thoiGian;

    public BinhLuan() {
    }

    public BinhLuan(int id,int idKhachHang, String hinhDaiDien, String ten, String noiDung,String thoiGian) {
        this.id = id;
        this.idKhachHang=idKhachHang;
        this.hinhDaiDien = hinhDaiDien;
        this.ten = ten;
        this.noiDung = noiDung;
        this.thoiGian=thoiGian;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        this.hinhDaiDien = hinhDaiDien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
