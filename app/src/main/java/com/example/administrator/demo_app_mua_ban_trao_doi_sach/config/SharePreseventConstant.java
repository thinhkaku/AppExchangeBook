package com.example.administrator.demo_app_mua_ban_trao_doi_sach.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.TrasosaConstans;

public class SharePreseventConstant {
    private static SharedPreferences mSettings;
    private static SharedPreferences.Editor mEditor;

    public SharePreseventConstant() {
    }
    private static class SingletonHelper {
        private static final SharePreseventConstant INSTANCE = new SharePreseventConstant();
    }
    public static SharePreseventConstant getInstance(Context mContext) {
        mSettings = mContext.getSharedPreferences("share_app_trao_doi_sach", 0);
        mEditor = mSettings.edit();
        return SingletonHelper.INSTANCE;
    }
    public void setCheckDangBai(String token) {
        mEditor.putString(TrasosaConstans.CHECK_DANG_BAI, token);
        mEditor.apply();
    }

    public String getCheckDangBai() {
        return mSettings.getString(TrasosaConstans.CHECK_DANG_BAI, "");
    }
    public void setDangTin1(String ten, String email,String sdt) {
        mEditor.putString(Constants.TEN, ten);
        mEditor.putString(Constants.EMAIL, email);
        mEditor.putString(Constants.SDT, sdt);
        mEditor.apply();
    }
    public String getTen() {
        return mSettings.getString(Constants.TEN, "");
    }
    public String getEmail() {
        return mSettings.getString(Constants.EMAIL, "");
    }

    public String getSDT() {
        return mSettings.getString(Constants.SDT, "");
    }

    public void setDangTin2(String diaChi, String taiKhoan,String matKhau) {
        mEditor.putString(Constants.DIACHI, diaChi);
        mEditor.putString(Constants.TAIKHOAN, taiKhoan);
        mEditor.putString(Constants.MATKHAU, matKhau);
        mEditor.apply();
    }

    public String getDiaChi() {
        return mSettings.getString(Constants.DIACHI, "");
    }
    public String getTaiKhoan() {
        return mSettings.getString(Constants.TAIKHOAN, "");
    }

    public String getMatKhau() {
        return mSettings.getString(Constants.MATKHAU, "");
    }

    public void setIdS(int idS) {
        mEditor.putInt(Constants.IDS, idS);
        mEditor.apply();
    }

    public int getIdS() {
        return mSettings.getInt(Constants.IDS, -1);
    }

}
