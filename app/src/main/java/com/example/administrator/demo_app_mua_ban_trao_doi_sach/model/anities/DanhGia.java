package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class DanhGia {
    @SerializedName("idS")
    @Expose
    private Integer idS;
    @SerializedName("danhG")
    @Expose
    private String danhG;
    @SerializedName("giaT")
    @Expose
    private Integer giaT;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("ten")
    @Expose
    private String ten;

    public Integer getIdS() {
        return idS;
    }

    public void setIdS(Integer idS) {
        this.idS = idS;
    }

    public String getDanhG() {
        return danhG;
    }

    public void setDanhG(String danhG) {
        this.danhG = danhG;
    }

    public Integer getGiaT() {
        return giaT;
    }

    public void setGiaT(Integer giaT) {
        this.giaT = giaT;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

}
