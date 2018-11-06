package com.example.administrator.demo_app_mua_ban_trao_doi_sach.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.OnLoadByteArray;

import java.io.ByteArrayOutputStream;

public class ConverBitmaptoByteArrayAsync extends AsyncTask<Bitmap,Void,byte[]> {
    private byte[] aByte;
    private OnLoadByteArray onLoadByteArray;

    public ConverBitmaptoByteArrayAsync(OnLoadByteArray onLoadByteArray) {
        this.onLoadByteArray = onLoadByteArray;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        aByte=null;
    }

    @Override
    protected byte[] doInBackground(Bitmap... bitmaps) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmaps[0].compress(Bitmap.CompressFormat.PNG, 100, stream);
        aByte = stream.toByteArray();
        return aByte;
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        if (bytes!=null){
            onLoadByteArray.onResultByteArray(bytes);
        }
    }
}
