package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;

import java.util.ArrayList;

public interface OnloadBaiDang {
    void onsucess(ArrayList<BaiDang>arrBaiDang);
    void onErrr();

}
