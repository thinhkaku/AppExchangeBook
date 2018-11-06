package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.Login;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.NewsPostPersonalAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BaiDang;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalCustomerFragment extends BaseFragment implements View.OnClickListener {

    private Button btnBaiDangCN, btnDangNhapGianHang, btnAcessInformation, btnEditInfo, btnDoiTaiKhoan;
    private LinearLayout lnHideDang;
    private Context context;
    private RecyclerView recycleBaiDangCN;
    private EditText edtTenKh, edtHoKH, edtSdtKh, edtMaBuuDien, edtDiaChi;
    private boolean checkEnableButton, checkRecycleView;
    private SOService soService;
    private User user;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private NewsPostPersonalAdapter newsPostPersonalAdapter;
    private List<BaiDang> arrListView = new ArrayList<>();
    private LinearLayout lnFormsCaNhan;
    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    private void reCyClerView() {
        Utils.initRecycleView(recycleBaiDangCN, activity);
        newsPostPersonalAdapter = new NewsPostPersonalAdapter(soService, arrListView, context, myLoadAppProgessBar, user);
        recycleBaiDangCN.setAdapter(newsPostPersonalAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_personal_customer, container, false);
        edtTenKh = view.findViewById(R.id.edtTenKh);
        edtSdtKh = view.findViewById(R.id.edtSdtKh);
        edtDiaChi = view.findViewById(R.id.edtDiaChi);
        recycleBaiDangCN = view.findViewById(R.id.recycleBaiDangCN);
        edtMaBuuDien = view.findViewById(R.id.edtMaBuuDien);
        edtHoKH = view.findViewById(R.id.edtHoKH);
        btnBaiDangCN = view.findViewById(R.id.btnBaiDangCN);
        btnEditInfo = view.findViewById(R.id.btnEditInfo);
        btnAcessInformation = view.findViewById(R.id.btnAcessInformation);
        lnFormsCaNhan = view.findViewById(R.id.lnFormsCaNhan);
        lnHideDang = view.findViewById(R.id.lnHideDang);
        btnDangNhapGianHang = view.findViewById(R.id.btnDangNhapGianHang);
        btnDoiTaiKhoan = view.findViewById(R.id.btnDoiTaiKhoan);
        myLoadAppProgessBar = new MyLoadAppProgessBar(context);
        soService = ApiUtils.getSOService();
        return view;
    }

    private void checkAcount() {
        user = Util.getUser(context);
        if (user == null) {
            lnFormsCaNhan.setVisibility(View.GONE);
            lnHideDang.setVisibility(View.VISIBLE);
            lnHideDang.setMinimumHeight(Utils.getHeightScreen(activity));
        } else {
            lnHideDang.setVisibility(View.INVISIBLE);
            lnFormsCaNhan.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        initAction();
        checkEnableButton();

    }

    @Override
    public void onResume() {
        super.onResume();
        checkAcount();

        Utils.listenEditText(edtTenKh, btnAcessInformation);
        Utils.listenEditText(edtHoKH, btnAcessInformation);
        Utils.listenEditText(edtSdtKh, btnAcessInformation);
        Utils.listenEditText(edtMaBuuDien, btnAcessInformation);
        Utils.listenEditText(edtDiaChi, btnAcessInformation);

    }

    private void setData() {
        edtTenKh.setHint(user.getTen());
    }

    private void initAction() {
        btnBaiDangCN.setOnClickListener(this);
        btnDangNhapGianHang.setOnClickListener(this);
        btnAcessInformation.setOnClickListener(this);
        btnEditInfo.setOnClickListener(this);
        btnDoiTaiKhoan.setOnClickListener(this);
    }

    private void checkEnableButton() {
        if (checkEnableButton == false) {
            btnAcessInformation.setVisibility(View.GONE);
        } else {
            btnAcessInformation.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBaiDangCN:
                if (checkRecycleView == false) {
                    recycleBaiDangCN.setVisibility(View.VISIBLE);
                    getDataBaiDang();
                    checkRecycleView = true;
                } else {
                    recycleBaiDangCN.setVisibility(View.GONE);
                    checkRecycleView = false;
                }

                break;
            case R.id.btnDangNhapGianHang:
                Intent intent1 = new Intent(context, Login.class);
                startActivity(intent1);
                break;
            case R.id.btnAcessInformation:
                checkEnableButton = false;
                checkEnableButton();
                break;
            case R.id.btnEditInfo:
                checkEnableButton = true;
                checkEnableButton();
                break;
            case R.id.btnDoiTaiKhoan:
                Intent intent = new Intent(activity, Login.class);
                startActivity(intent);
                break;
        }
    }


    private void getDataBaiDang() {
        reCyClerView();
        newsPostPersonalAdapter.getNews(user.getId());
    }

}
