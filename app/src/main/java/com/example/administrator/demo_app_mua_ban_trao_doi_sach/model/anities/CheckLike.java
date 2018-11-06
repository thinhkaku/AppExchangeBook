package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class CheckLike {

    @SerializedName("checkLike")
    @Expose
    private Integer checkLike;

    public Integer getCheckLike() {
        return checkLike;
    }

    public void setCheckLike(Integer checkLike) {
        this.checkLike = checkLike;
    }

}
