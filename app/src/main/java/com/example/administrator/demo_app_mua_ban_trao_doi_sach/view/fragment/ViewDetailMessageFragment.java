package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.ResultUploadImage;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Message;

import java.util.ArrayList;

public interface ViewDetailMessageFragment {
    void onSuccessUploadInage();
    void onErr();
    void onErrSendMessage();
    void onResultMessage(ArrayList<Message> arrMessage);
}
