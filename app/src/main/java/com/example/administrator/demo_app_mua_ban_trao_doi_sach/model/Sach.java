package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sach  implements Serializable {

    @SerializedName("idLS")
    @Expose
    private Integer idLS;
    @SerializedName("tenS")
    @Expose
    private String tenS;
    @SerializedName("tg")
    @Expose
    private String tg;
    @SerializedName("nxb")
    @Expose
    private String nxb;
    @SerializedName("hinhS")
    @Expose
    private String hinhS;
    @SerializedName("mota")
    @Expose
    private String mota;
    @SerializedName("giaS")
    @Expose
    private Integer giaS;
    @SerializedName("nn")
    @Expose
    private String nn;
    @SerializedName("kt")
    @Expose
    private String kt;
    @SerializedName("ht")
    @Expose
    private Integer ht;
    @SerializedName("st")
    @Expose
    private Integer st;
    @SerializedName("giamGia")
    @Expose
    private Integer giamGia;
    @SerializedName("luotMua")
    @Expose
    private Integer luotMua;

    @SerializedName("tt")
    @Expose
    private Integer tt;

    public Integer getTt() {
        return tt;
    }

    public void setTt(Integer tt) {
        this.tt = tt;
    }

    public Integer getIdLS() {
        return idLS;
    }

    public void setIdLS(Integer idLS) {
        this.idLS = idLS;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getHinhS() {
        return hinhS;
    }

    public void setHinhS(String hinhS) {
        this.hinhS = hinhS;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getGiaS() {
        return giaS;
    }

    public void setGiaS(Integer giaS) {
        this.giaS = giaS;
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        this.nn = nn;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }

    public Integer getHt() {
        return ht;
    }

    public void setHt(Integer ht) {
        this.ht = ht;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public Integer getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Integer giamGia) {
        this.giamGia = giamGia;
    }

    public Integer getLuotMua() {
        return luotMua;
    }

    public void setLuotMua(Integer luotMua) {
        this.luotMua = luotMua;
    }

}
