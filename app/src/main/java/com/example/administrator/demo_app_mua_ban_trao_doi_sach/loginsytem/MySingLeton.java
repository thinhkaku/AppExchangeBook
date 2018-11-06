package com.example.administrator.demo_app_mua_ban_trao_doi_sach.loginsytem;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 12/30/2017.
 */

public class MySingLeton {
    private static  MySingLeton mInstance;
    private RequestQueue requestQueue;
    private static Context mcontext;
    private MySingLeton(Context context){
        mcontext=context;
        requestQueue= getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }
    public static  synchronized MySingLeton getmInstance(Context context){
        if (mInstance==null){
            mInstance=new MySingLeton(context);
        }
        return mInstance;
    }
    public <T> void addToRequestQueue(Request request){
        getRequestQueue().add(request);
    }
}
