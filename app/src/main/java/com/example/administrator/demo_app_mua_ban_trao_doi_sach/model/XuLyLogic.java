package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model;

import android.app.Activity;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Singleton;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class XuLyLogic {
    private OnLoadListener onLoadListener;
    private Emitter.Listener onResult;
    private Activity activity;

    {
        onResult = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onLoadListener.onSucess(args[0]);
                    }
                });

            }
        };

    }

    public void clientSentData(String event,String data){
        Singleton.Instance().getmSocket().emit(event,data);
    }
    public void serverRequestClient(String event){
        Singleton.Instance().getmSocket().on(event,onResult);
    }

    public XuLyLogic(OnLoadListener onLoadListener,Activity activity) {
        this.activity=activity;
        this.onLoadListener = onLoadListener;
    }


}
