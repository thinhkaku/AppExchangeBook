package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.BookStoreAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.GetDataFromSqlite;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHangArr;

import java.util.ArrayList;
import java.util.List;

public class PayBookActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton btnBack;
    private RecyclerView recycleChoThanhToan;
    private List<GioHang> arrBiaS;
    private TextView txtMessage;
    private BookStoreAdapter bookStoreAdapter;

    @Override
    protected void requestAgain() {

    }

    private void addEvent() {
        btnBack.setOnClickListener(this);
    }

    protected void initView() {
        setContentView(R.layout.layout_thanh_toan);
        btnBack=findViewById(R.id.btnBack);
        recycleChoThanhToan=findViewById(R.id.recycleChoThanhToan);
        txtMessage=findViewById(R.id.txtMessage);
        initData();
        initRecycleSachChoThanhToan();

        addEvent();
    }

    private void initRecycleSachChoThanhToan() {
        recycleChoThanhToan.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycleChoThanhToan.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleChoThanhToan.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recycleChoThanhToan.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        GetDataFromSqlite getDataFromSqlite=new GetDataFromSqlite();
        bookStoreAdapter =new BookStoreAdapter(this,arrBiaS,getDataFromSqlite,txtMessage,null);
        recycleChoThanhToan.setAdapter(bookStoreAdapter);
        bookStoreAdapter.notifyDataSetChanged();
    }


    private void initData() {
        Intent intent=getIntent();
        ArrayList<GioHangArr>arrMua=new ArrayList<>();
        arrBiaS=new ArrayList<>();
        arrMua= (ArrayList<GioHangArr>) intent.getSerializableExtra(Constants.SEND_GIO_HANG);
        if (arrMua!=null){
            for (int i=0;i<arrMua.size();i++){
                    GioHang gioHang=new GioHang();
                gioHang.setId(arrMua.get(i).getId());
                gioHang.setTenS(arrMua.get(i).getTenS());
                gioHang.setHinhS(arrMua.get(i).getHinhS());
                gioHang.setGiaS(arrMua.get(i).getGiaS());
                gioHang.setSoLuong(arrMua.get(i).getSoLuong());
                gioHang.setCheck(arrMua.get(i).getCheck());
                    arrBiaS.add(gioHang);

            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Utils.stAnimationButton(this,btnBack);
                finish();
                break;
        }
    }
}
