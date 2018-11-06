package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicRegisterOneFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.Login;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;

public class RegisterOneFragment extends BaseFragment implements View.OnClickListener, ViewRegisterOneFragment {
    private Button btnDangKiTiep, btnHuy;
    private EditText edtTen, edtSDT, edtEmail;
    private MainRegisterFragment mainRegisterFragment;
    private Login login;
    private PresenterLogicRegisterOneFragment presenterLogicRegisterOneFragment;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.login= (Login) activity;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.framen_register_one,container,false);
        btnDangKiTiep=view.findViewById(R.id.btnDangKiTiep);
        edtTen=view.findViewById(R.id.edtTenDangKi);
        edtEmail=view.findViewById(R.id.edtEmailDangKi);
        edtSDT=view.findViewById(R.id.edtSdtDangKi);
        btnHuy=view.findViewById(R.id.btnHuyDangKi1);
        presenterLogicRegisterOneFragment=new PresenterLogicRegisterOneFragment(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        addEvent();
    }

    private void addEvent() {
        btnDangKiTiep.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDangKiTiep:
                String name=edtTen.getText().toString();
                String email=edtEmail.getText().toString();
                String phone=edtSDT.getText().toString();
                presenterLogicRegisterOneFragment.registerOne(edtTen ,name, email, phone, getContext());
                break;

            case R.id.btnHuyDangKi1:
                login.getFragmentDangNhap();
                break;
        }
    }

    @Override
    public void onSuccessEmail() {
        mainRegisterFragment = (MainRegisterFragment) login.getFragmentDangKi();
        mainRegisterFragment.getFragmentDangKi2();
    }

}
