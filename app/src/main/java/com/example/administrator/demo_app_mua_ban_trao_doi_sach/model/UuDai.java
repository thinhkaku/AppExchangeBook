package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UuDai implements Serializable {

    @SerializedName("idUuDai")
    @Expose
    private Integer idUuDai;
    @SerializedName("chiTiet")
    @Expose
    private String chiTiet;

    public Integer getIdUuDai() {
        return idUuDai;
    }

    public void setIdUuDai(Integer idUuDai) {
        this.idUuDai = idUuDai;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

}
