package com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.MainRegisterFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.ViewRegisterOneFragment;

public class PresenterLogicRegisterOneFragment implements PresenterImpRegisterOneFragment{
    private ViewRegisterOneFragment viewRegisterOneFragment;

    public PresenterLogicRegisterOneFragment(ViewRegisterOneFragment viewRegisterOneFragment) {
        this.viewRegisterOneFragment = viewRegisterOneFragment;
    }


    @Override
    public void registerOne(EditText editText, String name, String email, String phone, Context context) {
        if (name.isEmpty()||phone.isEmpty()){
            Snackbar snackbar =Snackbar.make(editText, context.getString(R.string.chua_nhap_du_thong_tin),Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        else if (name.length()<2){
            Snackbar snackbar =Snackbar.make(editText, context.getString(R.string.chua_nhap_du_ten),Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        else if (phone.length()<10 ||phone.length()>11 || !phone.substring(0,1).equals("0")){
            Snackbar snackbar =Snackbar.make(editText,context.getString(R.string.false_phone),Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        else {
            if(isValidEmail(email)){
                SharePreseventConstant.getInstance(context).setDangTin1(name,email,phone);
                viewRegisterOneFragment.onSuccessEmail();
            }else if (email.isEmpty()){
                SharePreseventConstant.getInstance(context).setDangTin1(name, Constants.FORMAT_EMAIL,phone);
                viewRegisterOneFragment.onSuccessEmail();
            }else {
                Snackbar snackbar =Snackbar.make(editText, context.getString(R.string.email_ko_hop_le),Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
