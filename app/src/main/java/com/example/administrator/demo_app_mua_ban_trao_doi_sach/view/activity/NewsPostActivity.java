package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.UpImageNewPostFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.UpNewsPostFragment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 12/30/2017.
 */

public class NewsPostActivity extends BaseActivity implements View.OnClickListener {
    private UpNewsPostFragment upNewsPostFragment;
    private UpImageNewPostFragment upImageNewPostFragment;
    private ImageButton btnBackDang,btnDangBai;
    private int THUMBNAIL_SIZE=400;

    @Override
    protected void requestAgain() {

    }


    protected void initView() {
        setContentView(R.layout.layout_dangtin);
        initFragment();
        btnBackDang=findViewById(R.id.btnBackDang);
        btnDangBai=findViewById(R.id.btnDangBai);
        addEvent();
    }

    private void initFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction  transaction=fragmentManager.beginTransaction();
        upNewsPostFragment =new UpNewsPostFragment();
        upImageNewPostFragment =new UpImageNewPostFragment();
        transaction.add(R.id.fragmentDangTin, upNewsPostFragment);
        transaction.add(R.id.fragmentDangTin, upImageNewPostFragment);
        transaction.commit();
        switchFragment(upNewsPostFragment);

    }

    private void addEvent() {
        btnBackDang.setOnClickListener(this);
        btnDangBai.setOnClickListener(this);
    }

    public void switchFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction  transaction=fragmentManager.beginTransaction();
        transaction.hide(upNewsPostFragment);
        transaction.hide(upImageNewPostFragment);
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
    }


    public UpImageNewPostFragment getUpImageNewPostFragment() {
        return upImageNewPostFragment;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        ButtonAnimation buttonAnimation=new ButtonAnimation(this);
        switch (v.getId()){
            case R.id.btnDangBai:
                btnDangBai.startAnimation(buttonAnimation.startAnimation());
                upImageNewPostFragment.guiUrlToServer();
                break;
            case R.id.btnBackDang:
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
        }
    }


}
