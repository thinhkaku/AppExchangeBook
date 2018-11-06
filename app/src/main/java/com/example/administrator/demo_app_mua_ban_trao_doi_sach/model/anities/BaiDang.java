package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BaiDang implements Serializable{

    @SerializedName("idBaiDang")
    @Expose
    private Integer idBaiDang;

    @SerializedName("idKhachHang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("sdt")
    @Expose
    private Integer sdt;

    @SerializedName("ten")
    @Expose
    private String ten;

    @SerializedName("anhDaiDien")
    @Expose

    private String anhDaiDien;

    @SerializedName("tenSach")
    @Expose
    private String tenSach;

    @SerializedName("thoiGian")
    @Expose
    private String thoiGian;

    @SerializedName("noiDung")
    @Expose
    private String noiDung;


    @SerializedName("gia")
    @Expose
    private Integer gia;

    private Integer luotLike;

    private List<Hinh>arrListImage;

    public List<Hinh> getArrListImage() {
        return arrListImage;
    }

    public void setArrListImage(List<Hinh> arrListImage) {
        this.arrListImage = arrListImage;
    }

    public Integer getSoBL() {
        return soBL;
    }

    public void setSoBL(Integer soBL) {
        this.soBL = soBL;
    }

    private Integer soBL;
    private boolean checkLike;

    public BaiDang(Integer idBaiDang, Integer idKhachHang, Integer sdt, String ten, String anhDaiDien, String tenSach, String thoiGian, String noiDung, Integer gia, Integer luotLike, boolean checkLike, List<Hinh>arrImage) {
        this.idBaiDang = idBaiDang;
        this.idKhachHang = idKhachHang;
        this.sdt = sdt;
        this.ten = ten;
        this.anhDaiDien = anhDaiDien;
        this.tenSach = tenSach;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
        this.gia = gia;
        this.luotLike = luotLike;
        this.checkLike=checkLike;
        this.arrListImage=arrImage;
    }

    public boolean isCheckLike() {
        return checkLike;
    }

    public void setCheckLike(boolean checkLike) {
        this.checkLike = checkLike;
    }

    public Integer getIdBaiDang() {
        return idBaiDang;
    }

    public void setIdBaiDang(Integer idBaiDang) {
        this.idBaiDang = idBaiDang;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Integer getSdt() {
        return sdt;
    }

    public void setSdt(Integer sdt) {
        this.sdt = sdt;
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

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
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


    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public Integer getLuotLike() {
        return luotLike;
    }

    public void setLuotLike(Integer luotLike) {
        this.luotLike = luotLike;
    }

}
