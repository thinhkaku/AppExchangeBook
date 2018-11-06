package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NhomChat;

import java.util.List;

public interface ViewGroupChatFragment {
    void onSuccessGroupChat(List<NhomChat>arrGroupChat);
    void err();
}
