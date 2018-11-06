package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.Login;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.NewsPostActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.NewsStorePersonalActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.MessageActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.ListAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NumberComment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangeFragment extends BaseFragment implements View.OnClickListener {
    private FloatingActionButton fab,fab1,fab2,fab3;
    private  boolean ktHienMenu=true;
    private boolean ktImageFabButton=true;
    private Context context;
    private List<BaiDang> arrListView=new ArrayList<>();
    private ListAdapter listAdapter;
    private Activity activity;
    private ImageView imgHinhDaiDienDangTin1;
    private LinearLayout lnearDangBai, lnHideDang;
    private User user;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBarLoadNews;
    private int page;
    private SOService soService;
    private Button btnDangNhapGianHang;
    private RecyclerView recyclerView;
    private boolean checkLoadData;
    private TextView txtSlBd;
    private Animation animationFabButton1,animationFabButton2,animationFabButton3,animationFabButton,animationFabButtonXoayNguoc,animationFabButton1XoayNguoc,animationFabButton2XoayNguoc,animationFabButton3XoayNguoc;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_exchange,container,false);
        fab=view.findViewById(R.id.fab);
        fab1=view.findViewById(R.id.fab1);
        fab2=view.findViewById(R.id.fab2);
        fab3=view.findViewById(R.id.fab3);
        txtSlBd=view.findViewById(R.id.txtSlBd);
        btnDangNhapGianHang=view.findViewById(R.id.btnDangNhapGianHang);
        lnHideDang=view.findViewById(R.id.lnHideDang);
        lnearDangBai=view.findViewById(R.id.lnearDangBai);
        imgHinhDaiDienDangTin1=view.findViewById(R.id.imgHinhDaiDienDangTin1);
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh_layout);
        progressBarLoadNews=view.findViewById(R.id.progressBarLoadNews);
        recyclerView=view.findViewById(R.id.recyclerView);
        page=1;
        soService= ApiUtils.getSOService();
        return view;
    }

    private void  checkAcount(){
        user= Util.getUser(context);
        if (user==null){
            lnHideDang.setVisibility(View.VISIBLE);
        }else {
            lnHideDang.setVisibility(View.INVISIBLE);
            Picasso.with(context).load(Constants.PORTIMAGE+user.getAnhDaiDien()).resize(70,50).into(imgHinhDaiDienDangTin1);
            if (checkLoadData==false){
                reCyClerView();
                checkLoadData=true;
            }

        }
    }

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

    private void initFloatingButton() {
        animationFabButton1= AnimationUtils.loadAnimation(context,R.anim.floatingbutton1_anim);
        animationFabButton2=AnimationUtils.loadAnimation(context,R.anim.floatingbutton2_anim);
        animationFabButton3=AnimationUtils.loadAnimation(context,R.anim.floatingbutton3_anim);
        animationFabButton=AnimationUtils.loadAnimation(context,R.anim.floatingbutton_anim);
        animationFabButtonXoayNguoc=AnimationUtils.loadAnimation(context,R.anim.floatingbuttonxoaynguoc_anim);
        animationFabButton1XoayNguoc=AnimationUtils.loadAnimation(context,R.anim.floatingbutton1_anim_xoaynguoc);
        animationFabButton2XoayNguoc=AnimationUtils.loadAnimation(context,R.anim.floatingbutton2_anim_xoaynguoc);
        animationFabButton3XoayNguoc=AnimationUtils.loadAnimation(context,R.anim.floatingbutton3_anim_xoaynguoc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktImageButton();
                hienMenu();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,NewsStorePersonalActivity.class);
                startActivity(intent);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MessageActivity.class);
                startActivity(intent);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdapter.refetchBaiDang();
            }
        });

    }

    private void ktImageButton(){
        if (ktImageFabButton){
            fab.startAnimation(animationFabButton);
            fab.setImageResource(R.drawable.ic_remove_circle_outline_black_24dp);
            ktImageFabButton=false;
        }else {
            fab.startAnimation(animationFabButtonXoayNguoc);
            fab.setImageResource(R.drawable.ic_control_point_black_24dp);
            ktImageFabButton=true;
        }
    }
    private void hienMenu(){
        if (ktHienMenu){
            fab1.show();
            fab2.show();
            fab3.show();
            fab1.startAnimation(animationFabButton1);
            fab2.startAnimation(animationFabButton2);
            fab3.startAnimation(animationFabButton3);
            ktHienMenu=false;
        }else {
            fab1.startAnimation(animationFabButton1XoayNguoc);
            fab2.startAnimation(animationFabButton2XoayNguoc);
            fab3.startAnimation(animationFabButton3XoayNguoc);
            fab1.hide();
            fab2.hide();
            fab3.hide();
            ktHienMenu=true;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        checkAcount();
    }

    private void reCyClerView() {
        soService.getSoBaiDang().enqueue(new Callback<List<NumberComment>>() {
            @Override
            public void onResponse(Call<List<NumberComment>> call, Response<List<NumberComment>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    txtSlBd.setText(response.body().get(0).getSoBL()+" +");
                }
            }

            @Override
            public void onFailure(Call<List<NumberComment>> call, Throwable t) {

            }
        });
        Utils.initRecycleView(recyclerView,activity);
        listAdapter = new ListAdapter(arrListView, context,user,page,swipeRefreshLayout, progressBarLoadNews);
        recyclerView.setAdapter(listAdapter);
        listAdapter.getBaiDang();
    }


    @Override
    public void onStart() {
        super.onStart();
        addEvent();
        initFloatingButton();
    }

    private void addEvent() {
        lnearDangBai.setOnClickListener(this);
        btnDangNhapGianHang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lnearDangBai:
                lnearDangBai.startAnimation(ButtonAnimation.getInstance(context).startAnimation());
                Intent intent=new Intent(context,NewsPostActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDangNhapGianHang:
                Intent intent1=new Intent(context, Login.class);
                startActivity(intent1);
                break;
        }
    }
}
