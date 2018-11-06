package com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;

public class ButtonAnimation {
    private  Context context;
    private static  ButtonAnimation buttonAnimation;

    public ButtonAnimation(Context context) {
        this.context = context;
    }

    public static ButtonAnimation getInstance(Context context){
        if (buttonAnimation==null){
            buttonAnimation=new ButtonAnimation(context);
        }
        return buttonAnimation;
    }

    public  Animation startAnimation(){
        Animation animation= AnimationUtils.loadAnimation(context, R.anim.alpha_button);
        return animation;
    }
}
