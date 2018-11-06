package com.example.administrator.demo_app_mua_ban_trao_doi_sach.loginsytem;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.LoginSystemInterface;


import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import dmax.dialog.SpotsDialog;

/**
 * Created by Administrator on 10/16/2017.
 */

public class LoginAsync extends AsyncTask<String , Void,String> {
    private LoginSystemInterface loginSystemInterface;
    private Context context;
    private AlertDialog alertDialog;

    public LoginAsync(LoginSystemInterface loginSystemInterface, Context context) {
        this.loginSystemInterface = loginSystemInterface;
        this.context = context;
        alertDialog=new SpotsDialog(context,R.style.Custom);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String link = params[0];
        URL url = null;
        try {
            url = new URL(link);
            URLConnection connection =url.openConnection();
            InputStream inputStream = connection.getInputStream();
            byte[]b = new byte[1024];
            int count = inputStream.read(b);
            String s ="";
            while (count!=-1){
                s+=new String(b,0,count,"utf-8");
                count =inputStream.read(b);

            }
            inputStream.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s==null ||s.isEmpty()){
            loginSystemInterface.onEror();
        }else {
            loginSystemInterface.onSucess(s);
        }
        alertDialog.dismiss();
    }

}
