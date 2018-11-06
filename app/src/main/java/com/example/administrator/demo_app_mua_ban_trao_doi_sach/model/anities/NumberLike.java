package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumberLike {

    @SerializedName("soLK")
    @Expose
    private Integer soLK;

    public Integer getSoLK() {
        return soLK;
    }

    public void setSoLK(Integer soLK) {
        this.soLK = soLK;
    }

}
