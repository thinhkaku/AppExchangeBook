package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicLogin;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.IntroduceActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Singleton;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.TrasosaConstans;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;

public class LoginFragment extends BaseFragment implements ViewLogin{
    private CheckBox checkBox;
    private Button btnLogin;
    private TextView btnQuenMatKhau;
    private Activity activity;
    private EditText edtUserName, edtPassWord;
    SharedPreferences.Editor editor;
    private MyLoadAppProgessBar myLoadAppProgessBar;
    private PresenterLogicLogin presenterLogicLogin;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        activity=getActivity();
        presenterLogicLogin=new PresenterLogicLogin(this);
        myLoadAppProgessBar=new MyLoadAppProgessBar(activity);
        initView(view);
        addEvent();
        return view;
    }

    public void getResultDangKi(String taiKhoan, String matKhau){
        edtUserName.setText(taiKhoan);
        edtPassWord.setText(matKhau);
    }

//    <LinearLayout
//    android:layout_width="match_parent"
//    android:orientation="horizontal"
//    android:gravity="left"
//    android:layout_marginTop="15sp"
//    android:layout_marginBottom="50sp"
//    android:layout_height="30sp">
//        <com.facebook.login.widget.LoginButton
//    android:id="@+id/login_button"
//    android:layout_width="wrap_content"
//    android:layout_height="30sp"
//    android:layout_marginLeft="5sp"
//    android:layout_marginRight="15sp"
//    android:layout_gravity="center_horizontal"
//    android:layout_marginBottom="15dp"
//    android:layout_marginTop="15dp" />
//
//    </LinearLayout>


    private void addEvent() {
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (checkBox.isChecked()) {
                    Singleton.Instance().toasty(activity,activity.getString(R.string.remember_pass));
                    SharedPreferences sharedPreferences1 = activity.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean(Constants.CHECK, true);
                    editor.commit();
                }else {
                    Singleton.Instance().toasty(activity,activity.getString(R.string.no_remember_pass));
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin.startAnimation(ButtonAnimation.getInstance(activity).startAnimation());
                String userName = edtUserName.getText().toString();
                String password = edtPassWord.getText().toString();
                presenterLogicLogin.login(userName,password,activity,checkBox,myLoadAppProgessBar);
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor = activity.getSharedPreferences(TrasosaConstans.TAIKHOAN_MK, activity.MODE_PRIVATE).edit();
                if (isChecked){
                    editor.putString(TrasosaConstans.TAI_KHOAN,edtUserName.getText()+"");
                    editor.putString(TrasosaConstans.MAT_KHAU,edtPassWord.getText()+"");
                    editor.apply();
                }else {
                    editor.putString(TrasosaConstans.TAI_KHOAN,edtUserName.getText()+"");
                    editor.putString(TrasosaConstans.MAT_KHAU,"");
                    editor.apply();

                }

            }
        });
        btnQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private void initView(View view) {
        btnQuenMatKhau=(TextView)view.findViewById(R.id.btnQuenMatKhau);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        edtUserName = (EditText) view.findViewById(R.id.edtUserName);
        edtPassWord = (EditText) view.findViewById(R.id.edtPassword);
        SharedPreferences prefs = activity.getSharedPreferences(TrasosaConstans.TAIKHOAN_MK, activity.MODE_PRIVATE);
        edtUserName.setText(prefs.getString(TrasosaConstans.TAI_KHOAN,""));
        edtPassWord.setText(prefs.getString(TrasosaConstans.MAT_KHAU,""));
        checkBox = (CheckBox)  view.findViewById(R.id.ckNhoMatKhau);
    }

    @Override
    public void onStart() {
        super.onStart();
        checkBox.setChecked(load1());
    }


    @Override
    public void onResume() {
        super.onResume();
        checkBox.setChecked(load1());
    }


    @Override
    public void onPause() {
        super.onPause();
        save1(checkBox.isChecked());
    }

    private boolean load1() {
        SharedPreferences sharedPreferences1 = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences1.getBoolean(Constants.CHECK, false);
    }

    private void save1(final boolean isChecked) {
        SharedPreferences sharedPreferences1 = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putBoolean(Constants.CHECK, isChecked);
        editor.commit();
    }

    @Override
    public void success(User user, String result) {
        Util.setUser(activity,user);
        if (result.equals("0")){
            activity.finish();
        }else {
            Intent intent = new Intent(activity, IntroduceActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    @Override
    public void err(String err) {
        Snackbar snackbar =Snackbar.make(btnLogin,err,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
