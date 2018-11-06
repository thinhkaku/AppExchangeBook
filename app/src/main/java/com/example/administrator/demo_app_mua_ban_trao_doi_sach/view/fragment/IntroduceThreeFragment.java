package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;

/**
 * Created by Administrator on 12/28/2017.
 */

public class IntroduceThreeFragment extends Fragment {

    public static IntroduceThreeFragment newInstance() {
        IntroduceThreeFragment aboutFragment = new IntroduceThreeFragment();
        return aboutFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dital_3,container,false);
    }
}
