package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Singleton;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import es.dmoral.toasty.Toasty;

public abstract class BaseActivity extends AppCompatActivity {
    private Socket socket;
    {
        try {
            if (socket==null){
                socket = IO.socket(Constants.PORT);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    BroadcastReceiver ktKetnoi = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getActiveNetworkInfo() != null) {
                goiApi();
                requestAgain();
            } else {
                Toasty.Config.getInstance()
                        .setTextColor(Color.GREEN)
                        .apply();
                Toasty.custom(BaseActivity.this, getString(R.string.not_internet), getResources().getDrawable(R.drawable.ic_signal_cellular_connected_no_internet_4_bar_black_24dp),
                        Color.BLACK, Toast.LENGTH_SHORT, true, true).show();
            }
        }
    };

    protected abstract  void requestAgain();

    private void goiApi() {
        if (socket==null){
            socket.connect();
            Singleton.Instance().setmSocket(socket);
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(ktKetnoi, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ktKetnoi != null) {
            unregisterReceiver(ktKetnoi);
        }
    }


    protected abstract void initView();
}
