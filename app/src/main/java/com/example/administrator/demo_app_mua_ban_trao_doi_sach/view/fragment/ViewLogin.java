package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;

public interface ViewLogin {
    void success(User user, String result);
    void err(String err);
}
