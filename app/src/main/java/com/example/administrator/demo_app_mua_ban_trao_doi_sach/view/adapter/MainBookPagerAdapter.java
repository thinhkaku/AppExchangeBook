package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.athbk.ultimatetablayout.IFTabAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;

import java.util.ArrayList;

public class MainBookPagerAdapter extends FragmentStatePagerAdapter implements IFTabAdapter {
    private ArrayList<Fragment>arrFragment=new ArrayList<>();
    private ArrayList<Integer>arrIcon=new ArrayList<>();
    public MainBookPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
    }
    public void addFragment(Fragment fragment,Integer icon){
        arrFragment.add(fragment);
        arrIcon.add(icon);
    }

    @Override
    public int getCount() {
        return arrIcon.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public String getTitle(int i) {
        return "";
    }


    @Override
    public int getIcon(int i) {
        return arrIcon.get(i);
    }
}
