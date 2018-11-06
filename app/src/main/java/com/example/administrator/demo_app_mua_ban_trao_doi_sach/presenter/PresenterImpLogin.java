package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.app.Activity;
import android.widget.CheckBox;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;

public interface PresenterImpLogin {
     void login(String userName, String passWord, Activity activity, CheckBox checkBox, MyLoadAppProgessBar myLoadAppProgessBar);
}
