package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicGroupChatFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.GroupChatAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.NhomChat;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;

import java.util.ArrayList;
import java.util.List;


public class FragmentGroupChat extends BaseFragment implements ViewGroupChatFragment{
    private GridView gvChat;
    private GroupChatAdapter groupChatAdapter;
    private Activity activity;
    private User user;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private SOService soService;
    private TextView txtGroup;
    private PresenterLogicGroupChatFragment presenterLogicGroupChatFragment;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_group_chat,container,false);
        gvChat=view.findViewById(R.id.gvNhanTin);
        txtGroup=view.findViewById(R.id.txtGroup);
        myLoadAppProgessBar=new MyLoadAppProgessBar(activity);
        soService= ApiUtils.getSOService();
        presenterLogicGroupChatFragment=new PresenterLogicGroupChatFragment(this,soService,myLoadAppProgessBar);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        user= Util.getUser(activity);
        presenterLogicGroupChatFragment.getGroupChat(user.getId());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }


    public void updateData(String newtext){
        groupChatAdapter.upDateNhomChat(newtext);
        groupChatAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessGroupChat(List<NhomChat> arrGroupChat) {
        if (arrGroupChat.size()==0){
            txtGroup.setVisibility(View.VISIBLE);
        }else {
            txtGroup.setVisibility(View.INVISIBLE);
        }
        groupChatAdapter =new GroupChatAdapter(activity,arrGroupChat);
        gvChat.setAdapter(groupChatAdapter);
    }

    @Override
    public void err() {

    }
}
