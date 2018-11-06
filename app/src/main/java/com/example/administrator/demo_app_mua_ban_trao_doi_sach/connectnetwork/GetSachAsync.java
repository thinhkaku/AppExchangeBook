package com.example.administrator.demo_app_mua_ban_trao_doi_sach.connectnetwork;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.BookAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSachAsync{
    private SOService soService;
    private RecyclerView recyclerView;
    private Activity activity;
    private ProgressBar progressBar;
    private BookAdapter bookAdapter;

    public GetSachAsync(SOService soService, RecyclerView recyclerView, Activity activity, ProgressBar progressBar) {
        this.soService = soService;
        this.recyclerView = recyclerView;
        this.activity = activity;
        this.progressBar = progressBar;
    }


    private void getData(int id){
        soService.getSach(id).enqueue(new Callback<List<BiaSach>>() {
            @Override
            public void onResponse(Call<List<BiaSach>> call, Response<List<BiaSach>> response) {
                if (response.body()!=null){
                    bookAdapter =new BookAdapter(activity,response.body());
                    recyclerView.setAdapter(bookAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BiaSach>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }



    public void execute(int id) {
        progressBar.setVisibility(View.VISIBLE);
        getData(id);
    }
}
