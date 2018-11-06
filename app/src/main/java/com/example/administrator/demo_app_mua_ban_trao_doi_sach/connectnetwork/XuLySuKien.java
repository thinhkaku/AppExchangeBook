package com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork;

import android.app.Activity;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.OnLoadListener;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.XuLyLogic;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.MainView;

public class XuLySuKien implements OnLoadListener{
    private MainView mainView;
    private XuLyLogic xuLyLogic;
    public XuLySuKien(MainView mainView,Activity activity) {
        xuLyLogic=new XuLyLogic(this,activity);
        this.mainView = mainView;
    }

    public void sendToServer(String event,String data){
        xuLyLogic.clientSentData(event,data);
    }
    public void serverSendData(String event){
        xuLyLogic.serverRequestClient(event);
    }

    @Override
    public void onSucess(Object b) {
        mainView.traVeKetQua(b);
    }

    @Override
    public void onErorr() {

    }
}
