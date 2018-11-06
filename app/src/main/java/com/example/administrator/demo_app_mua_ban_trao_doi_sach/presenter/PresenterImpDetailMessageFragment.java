package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork.XuLySuKien;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Message;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;

import java.util.ArrayList;

public interface PresenterImpDetailMessageFragment {
    void upLoadImage(Uri uri, Context context);
    void sendMessage(Context context, ArrayList<Message> arrMessage, String content, int idCustommer);
}
