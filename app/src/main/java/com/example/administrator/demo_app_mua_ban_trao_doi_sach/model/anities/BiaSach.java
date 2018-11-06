package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BiaSach implements Comparable<BiaSach>{

    @SerializedName("idS")
    @Expose
    private Integer idS;
    @SerializedName("tenS")
    @Expose
    private String tenS;
    @SerializedName("giaS")
    @Expose
    private Integer giaS;
    @SerializedName("hinhS")
    @Expose
    private String hinhS;
    @SerializedName("giamGia")
    @Expose
    private Integer giamGia;

    public Integer getIdS() {
        return idS;
    }

    public void setIdS(Integer idS) {
        this.idS = idS;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public Integer getGiaS() {
        return giaS;
    }

    public void setGiaS(Integer giaS) {
        this.giaS = giaS;
    }

    public String getHinhS() {
        return hinhS;
    }

    public void setHinhS(String hinhS) {
        this.hinhS = hinhS;
    }

    public Integer getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Integer giamGia) {
        this.giamGia = giamGia;
    }

    @Override
    public int compareTo(@NonNull BiaSach o) {
            return giaS.compareTo(o.giaS);
    }
}
