package com.example.administrator.demo_app_mua_ban_trao_doi_sach.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.OnloadBitmap;

public class UpLoadHinhAsyncTask extends AsyncTask<Bitmap,Void,Bitmap>{
    private OnloadBitmap onloadBitmap;

    public UpLoadHinhAsyncTask(OnloadBitmap onloadBitmap) {
        this.onloadBitmap = onloadBitmap;
    }

    Bitmap bitmap;
    @Override
    protected Bitmap doInBackground(Bitmap... bitmaps) {
        bitmap=Bitmap.createScaledBitmap(bitmaps[0], 350, 500, false);
        return bitmap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bitmap=null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap!=null){
            onloadBitmap.resultBitmap(bitmap);
        }
    }
}
