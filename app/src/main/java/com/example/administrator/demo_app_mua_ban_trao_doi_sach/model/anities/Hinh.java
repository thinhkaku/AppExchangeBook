package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Hinh implements Serializable{
    @SerializedName("hinh")
    @Expose
    private String hinh;
    public Hinh(String hinh) {
        this.hinh = hinh;
    }
    public String getHinh() {
        return hinh;
    }
    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

}
