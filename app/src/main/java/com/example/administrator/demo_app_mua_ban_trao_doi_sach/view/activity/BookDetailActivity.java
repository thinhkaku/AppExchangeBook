package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Visibility;
import android.view.View;
import android.widget.ProgressBar;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.FillOutPersonalInformationFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.BookDetailFragment;

public class BookDetailActivity extends BaseActivity {
    private BookDetailFragment bookDetailFragment;
    private FillOutPersonalInformationFragment fillOutPersonalInformationFragment;
    private boolean check;

    @Override
    protected void requestAgain() {

    }


    @Override
    protected void initView() {
        setContentView(R.layout.layout_mua_hang);
        initData();
        initFragment();
    }


    private void initData() {
        Intent intent=getIntent();
        check=intent.getBooleanExtra(Constants.CHECK,false);

    }

    private void initFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        bookDetailFragment =new BookDetailFragment();
        fillOutPersonalInformationFragment =new FillOutPersonalInformationFragment();
        fragmentTransaction.add(R.id.panel_mua_hang, bookDetailFragment);
        fragmentTransaction.add(R.id.panel_mua_hang, fillOutPersonalInformationFragment);
        fragmentTransaction.commit();
        if (check==false){
            swichFragment(bookDetailFragment);
        }else {
            swichFragment(fillOutPersonalInformationFragment);
        }

    }
    public void swichFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.hide(bookDetailFragment);
        fragmentTransaction.hide(fillOutPersonalInformationFragment);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public BookDetailFragment getBookDetailFragment() {
        return bookDetailFragment;
    }

    public FillOutPersonalInformationFragment getFillOutPersonalInformationFragment() {
        return fillOutPersonalInformationFragment;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
