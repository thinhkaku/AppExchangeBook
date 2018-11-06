package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.PayBookActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.BookStoreAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.GetDataFromSqlite;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHangArr;

import java.util.ArrayList;
import java.util.List;

public class BookStoreFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recycleChoThanhToan;
    private BookStoreAdapter bookStoreAdapter;
    private TextView txtMessage, txtTongTien;
    private Button btnThanhToan;
    private List<GioHang>arrBiaS;
    private Context context;
    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_store,container,false);
        recycleChoThanhToan=view.findViewById(R.id.recycleChoThanhToan);
        txtMessage=view.findViewById(R.id.txtMessage);
        txtTongTien=view.findViewById(R.id.txtTongTien);
        btnThanhToan=view.findViewById(R.id.btnThanhToan);
        arrBiaS=new ArrayList<>();
        return view;
    }

    private void initRecycleSachChoThanhToan() {
        recycleChoThanhToan.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycleChoThanhToan.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleChoThanhToan.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recycleChoThanhToan.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dimension);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrBiaS.size()==0){
            txtMessage.setVisibility(View.VISIBLE);
        }else {
            txtMessage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecycleSachChoThanhToan();
        initAction();
        addEvent();
    }

    private void addEvent() {
        btnThanhToan.setOnClickListener(this);
    }

    private void initAction() {
        GetDataFromSqlite getDataFromSqlite=new GetDataFromSqlite();
        arrBiaS=getDataFromSqlite.getGioHang();
                bookStoreAdapter =new BookStoreAdapter(context,arrBiaS,getDataFromSqlite,txtMessage, txtTongTien);
                recycleChoThanhToan.setAdapter(bookStoreAdapter);
                bookStoreAdapter.notifyDataSetChanged();
                int tongTien=0;
                for (int i=0;i<arrBiaS.size();i++)
                {
                    if (arrBiaS.get(i).getCheck()==1){
                        tongTien+=arrBiaS.get(i).getGiaS()*arrBiaS.get(i).getSoLuong();
                    }
                }
        txtTongTien.setText(Utils.formatGia(tongTien));
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThanhToan:
                GetDataFromSqlite getDataFromSqlite=new GetDataFromSqlite();
                List<GioHang>arrS= getDataFromSqlite.getGioHang();
                ArrayList<GioHangArr>arrSMua= new ArrayList<>();
                for (int i=0;i<arrS.size();i++){
                    if (arrS.get(i).getCheck()==1){
                        GioHangArr gioHangArr=new GioHangArr();
                        gioHangArr.setId(arrS.get(i).getId());
                        gioHangArr.setTenS(arrS.get(i).getTenS());
                        gioHangArr.setHinhS(arrS.get(i).getHinhS());
                        gioHangArr.setGiaS(arrS.get(i).getGiaS());
                        gioHangArr.setSoLuong(arrS.get(i).getSoLuong());
                        gioHangArr.setCheck(arrS.get(i).getCheck());
                        arrSMua.add(gioHangArr);
                    }
                }
                Intent intent=new Intent(activity, PayBookActivity.class);
                intent.putExtra(Constants.SEND_GIO_HANG,arrSMua);
                startActivity(intent);
                break;
        }
    }
}
