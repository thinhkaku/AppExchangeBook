package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model;
import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class ResultUploadImage {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("name")
    @Expose
    private String name;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
