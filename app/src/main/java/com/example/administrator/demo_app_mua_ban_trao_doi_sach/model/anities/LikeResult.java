package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities;
import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class LikeResult {

    @SerializedName("idLike")
    @Expose
    private String idLike;

    public String getIdLike() {
        return idLike;
    }

    public void setIdLike(String idLike) {
        this.idLike = idLike;
    }

}