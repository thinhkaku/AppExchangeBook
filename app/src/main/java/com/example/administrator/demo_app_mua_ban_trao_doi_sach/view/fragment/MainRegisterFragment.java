package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;

public class MainRegisterFragment extends BaseFragment {
    private static MainRegisterFragment mainRegisterFragment;
    private  FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RegisterOneFragment registerOneFragment;
    private RegisterTwoFragment registerTwoFragment;
    private RegisterThreeFragment registerThreeFragment;
    private boolean checkFragmentManager;

    public static MainRegisterFragment getInstance(FragmentManager fragmentManager){
        mainRegisterFragment =new MainRegisterFragment();
        mainRegisterFragment.fragmentManager=fragmentManager;
        return mainRegisterFragment;
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        if (checkFragmentManager==false){
            initFragment(fragmentManager);
            checkFragmentManager=true;
        }
    }

    private void initFragment(FragmentManager fragmentManager) {
        fragmentTransaction=fragmentManager.beginTransaction();
        registerOneFragment =new RegisterOneFragment();
        registerTwoFragment =new RegisterTwoFragment();
        registerThreeFragment =new RegisterThreeFragment();
        fragmentTransaction.add(R.id.fragmentPanelDangKi, registerOneFragment);
        fragmentTransaction.add(R.id.fragmentPanelDangKi, registerTwoFragment);
        fragmentTransaction.add(R.id.fragmentPanelDangKi, registerThreeFragment);
        fragmentTransaction.commit();
        swithFragmentRightleft(registerOneFragment,fragmentManager);
    }

    public void swithFragmentRightleft(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left1,R.anim.slide_out_right);
        transaction.hide(registerOneFragment);
        transaction.hide(registerTwoFragment);
        transaction.hide(registerThreeFragment);
        transaction.show(fragment);
        transaction.commit();
    }

    public void swithFragmentLeftRight(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_right);
        transaction.hide(registerOneFragment);
        transaction.hide(registerTwoFragment);
        transaction.hide(registerThreeFragment);
        transaction.show(fragment);
        transaction.commit();
    }

    public void getFragmentDangKi2(){
        swithFragmentRightleft(registerTwoFragment,fragmentManager);
    }
    public void getFragmentDangKi2Back(){
        swithFragmentLeftRight(registerTwoFragment,fragmentManager);
    }
    public void getFragmentDangKi3(){
        swithFragmentRightleft(registerThreeFragment,fragmentManager);
    }

    public void getFragmentDangKi1(){
        swithFragmentLeftRight(registerOneFragment,fragmentManager);
    }

}
