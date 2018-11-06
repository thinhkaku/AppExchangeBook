package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NhomChat implements Serializable {

    @SerializedName("idNhom")
    @Expose
    private Integer idNhom;
    @SerializedName("idPhong")
    @Expose
    private String idPhong;
    @SerializedName("idKhachHang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("anhDaiDien")
    @Expose
    private String anhDaiDien;

    public Integer getIdNhom() {
        return idNhom;
    }

    public void setIdNhom(Integer idNhom) {
        this.idNhom = idNhom;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }
}