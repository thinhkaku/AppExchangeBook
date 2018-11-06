package com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import es.dmoral.toasty.Toasty;

/**
 * Created by quang on 21-Jan-18.
 */

public class Singleton {
    private static Singleton instance;
    Socket mSocket;
    Emitter.Listener onResult;
    Emitter.Listener listTable;

    private Singleton() {
    }

    public static Singleton Instance()
    {
        if (instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }
    public Socket getmSocket()
    {
        return this.mSocket;
    }

    public Emitter.Listener getOnResult()
    {
        return this.onResult;
    }

    public void setmSocket(Socket socket)
    {
        this.mSocket = socket;
    }

    public void setOnResult(Emitter.Listener listener)
    {
        this.onResult = listener;
    }

    public Emitter.Listener getListTable() {
        return listTable;
    }

    public void inTT(Context context,String messeng){
        Toast.makeText(context,messeng,Toast.LENGTH_SHORT).show();
    }

    public void toasty(Context context,String meseseg){
        Toasty.Config.getInstance()
                .setTextColor(Color.GREEN).setInfoColor(Color.YELLOW)
                .apply();
        Toasty.custom(context, meseseg, context.getResources().getDrawable(R.drawable.ic_beenhere_black_24dp),
                Color.BLACK, Toast.LENGTH_SHORT, true, true).show();
    }

}
