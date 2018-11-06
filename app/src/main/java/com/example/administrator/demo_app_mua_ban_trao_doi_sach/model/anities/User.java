package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class User implements Serializable {
    @SerializedName("idKhachHang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("anhDaiDien")
    @Expose
    private String anhDaiDien;
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("sdt")
    @Expose
    private Integer sdt;
    @SerializedName("diaChi")
    @Expose
    private String diaChi;
    @SerializedName("userPass")
    @Expose
    private String userPass;
    @SerializedName("user")
    @Expose
    private String user;

    public User(int id, int sdt, String anhDaiDien, String ten, String email, String diaChi) {
        this.idKhachHang = id;
        this.sdt = sdt;
        this.anhDaiDien = anhDaiDien;
        this.ten = ten;
        this.email = email;
        this.diaChi = diaChi;
    }

    public User() {
    }

    public Integer getId() {
        return idKhachHang;
    }

    public void setId(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSdt() {
        return sdt;
    }

    public void setSdt(Integer sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
