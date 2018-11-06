package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.notifiresult;
import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class MessageResult {

    @SerializedName("message")
    @Expose
    private ResultDangBai resultDangBai;
    @SerializedName("error")
    @Expose
    private String error;

    public ResultDangBai getMessage() {
        return resultDangBai;
    }

    public void setMessage(ResultDangBai resultDangBai) {
        this.resultDangBai = resultDangBai;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
