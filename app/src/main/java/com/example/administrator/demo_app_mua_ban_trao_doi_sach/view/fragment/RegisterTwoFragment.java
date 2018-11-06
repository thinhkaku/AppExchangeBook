package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicRegisterTwoFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.Login;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;

public class RegisterTwoFragment extends BaseFragment implements View.OnClickListener ,ViewRegisterTwoFragment{
    private Button btnTiep, btnTroLai;
    private EditText edtDiaChi,edtTaiKhoan, edtMatKhau;
    private Login login;
    private MainRegisterFragment mainRegisterFragment;
    private SOService soService;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private PresenterLogicRegisterTwoFragment presenterLogicRegisterTwoFragment;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_register_two,container,false);
        btnTiep=view.findViewById(R.id.btnDangKiTiep2);
        btnTroLai=view.findViewById(R.id.btnTroLaiDangKi2);
        edtDiaChi=view.findViewById(R.id.edtDiaChi);
        edtTaiKhoan=view.findViewById(R.id.edtTenTaiKhoanDangKi);
        edtMatKhau=view.findViewById(R.id.edtMatKhauDangKi);
        myLoadAppProgessBar=new MyLoadAppProgessBar(login);
        presenterLogicRegisterTwoFragment=new PresenterLogicRegisterTwoFragment(soService,myLoadAppProgessBar,login,this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        login= (Login) activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        addEvent();
    }

    private void addEvent() {
        btnTiep.setOnClickListener(this);
        btnTroLai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDangKiTiep2:
                final String address=edtDiaChi.getText().toString();
                final String user=edtTaiKhoan.getText().toString();
                final String password=edtMatKhau.getText().toString();
                presenterLogicRegisterTwoFragment.getRegisterTwo(edtDiaChi, address, user, password);
                break;
            case R.id.btnTroLaiDangKi2:
                mainRegisterFragment = (MainRegisterFragment) login.getFragmentDangKi();
                mainRegisterFragment.getFragmentDangKi1();
                break;
        }
    }

    @Override
    public void onSusscess() {
        mainRegisterFragment = (MainRegisterFragment) login.getFragmentDangKi();
        mainRegisterFragment.getFragmentDangKi3();
    }

    @Override
    public void onErrExist() {
        Snackbar snackbar =Snackbar.make(edtDiaChi,getContext().getString(R.string.exist_user),Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
