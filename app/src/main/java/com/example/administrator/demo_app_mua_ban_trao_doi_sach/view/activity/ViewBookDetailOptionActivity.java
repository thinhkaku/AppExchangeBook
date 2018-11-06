package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;

import java.util.List;

public interface ViewBookDetailOptionActivity {
    void onSuccessGetSearch(List<BiaSach> arrListS);
    void onSuccessGetStyleBook(List<BiaSach> arrListS);
    void err();
}
