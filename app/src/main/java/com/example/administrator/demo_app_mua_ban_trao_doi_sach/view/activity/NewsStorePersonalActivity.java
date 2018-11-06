package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.StoreNewsCustomerAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.SQLiteData;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;

import java.util.ArrayList;

public class NewsStorePersonalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StoreNewsCustomerAdapter storeNewsCustomerAdapter;
    private ArrayList<BaiDang> arrBaiDang=new ArrayList();
    private SQLiteData sqLiteData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luu_tru_bai_dang_layout);
        Toolbar toolbar=findViewById(R.id.toolbarLuuTru);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getData();
        reCyClerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        sqLiteData=new SQLiteData(this);
        arrBaiDang=sqLiteData.getBaiDang();
    }


    private void reCyClerView() {
        recyclerView =findViewById(R.id.recyclerLuuTru);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewsStorePersonalActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        storeNewsCustomerAdapter =new StoreNewsCustomerAdapter(arrBaiDang,this,sqLiteData);
        recyclerView.setAdapter(storeNewsCustomerAdapter);

    }
}
