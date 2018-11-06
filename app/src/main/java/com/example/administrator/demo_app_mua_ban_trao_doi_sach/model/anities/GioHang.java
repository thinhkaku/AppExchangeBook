package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GioHang extends RealmObject{
    @PrimaryKey
    private int id;
    private String tenS;
    private String hinhS;
    private int giaS;
    private int soLuong;
    private int check;

    public GioHang() {
    }

    public GioHang(int id, String tenS, String hinhS, int giaS, int soLuong, int check) {
        this.id = id;
        this.tenS = tenS;
        this.hinhS = hinhS;
        this.giaS = giaS;
        this.soLuong = soLuong;
        this.check = check;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public String getHinhS() {
        return hinhS;
    }

    public void setHinhS(String hinhS) {
        this.hinhS = hinhS;
    }

    public int getGiaS() {
        return giaS;
    }

    public void setGiaS(int giaS) {
        this.giaS = giaS;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
