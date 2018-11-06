package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;


import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class ListDeleteBaiDang {

    @SerializedName("message")
    @Expose
    private DeleteBaiDang deleteBaiDang;

    public DeleteBaiDang getMessage() {
        return deleteBaiDang;
    }

    public void setMessage(DeleteBaiDang deleteBaiDang) {
        this.deleteBaiDang = deleteBaiDang;
    }

}
