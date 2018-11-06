package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumberComment {

    @SerializedName("soBL")
    @Expose
    private Integer soBL;

    public Integer getSoBL() {
        return soBL;
    }

    public void setSoBL(Integer soBL) {
        this.soBL = soBL;
    }

}
