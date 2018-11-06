package com.example.administrator.demo_app_mua_ban_trao_doi_sach.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
    public static User getUser(Context context){
        Gson gson = new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        String json = appSharedPrefs.getString(Constants.KEY_PUSH_USER, "");
        return gson.fromJson(json, User.class);
    }

    public static void setUser(Context context,User user){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(Constants.KEY_PUSH_USER, json);
        prefsEditor.commit();
    }



    public static String loaiSach(int id)
    {
        if (id==1){
            return "Sách mới nhất";
        }
        if (id==2){
            return "Best Sellers";
        }
        if (id==3){
            return "Sách kinh điển";
        }
        if (id==6){
            return "Sách văn học";
        }
        if (id==4){
            return "Sách kinh tế";
        }
        if (id==5){
            return "Sách Tâm lý";
        }
        if (id==7){
            return "Truyện tranh";
        }
        if (id==8){
            return "Tuổi teen";
        }
        if (id==9){
            return "Nuôi dạy trẻ";
        }
        if (id==10){
            return "Sách khoa học";
        }
        if (id==11){
            return "Sách nước ngoài";
        }
        return "";
    }
}
