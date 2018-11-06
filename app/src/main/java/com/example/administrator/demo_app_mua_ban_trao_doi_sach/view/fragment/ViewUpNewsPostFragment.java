package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.notifiresult.MessageResult;

public interface ViewUpNewsPostFragment {
    void onSuccessPostNews(MessageResult messageResult);
    void err();
    void errResult();
}
