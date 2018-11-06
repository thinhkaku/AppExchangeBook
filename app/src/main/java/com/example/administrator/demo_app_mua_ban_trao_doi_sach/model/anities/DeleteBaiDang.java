package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteBaiDang {

    @SerializedName("delete")
    @Expose
    private String delete;
    @SerializedName("error")
    @Expose
    private String error;

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
