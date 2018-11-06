package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;



import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.IntroducePagerAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.TrasosaConstans;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.IntroduceOneFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.IntroduceTwoFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.IntroduceThreeFragment;
import com.rd.PageIndicatorView;

/**
 * Created by Administrator on 12/28/2017.
 */

public class IntroduceActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private IntroducePagerAdapter introducePagerAdapter;
    private Animation animationButton;
    private Button btnBack, btnNext;
    SharedPreferences.Editor editor;
    private PageIndicatorView pageIndicatorView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_beginapp);
        initViewPager();
        intiView();
    }

    private void  initViewPager(){
        pageIndicatorView=findViewById(R.id.pageIndicatorView);
        viewPager=findViewById(R.id.viewPagerAbout);
        introducePagerAdapter =new IntroducePagerAdapter(getSupportFragmentManager(),this);
        introducePagerAdapter.addFragment(IntroduceOneFragment.newInstance(),"1");
        introducePagerAdapter.addFragment(IntroduceTwoFragment.newInstance(),"2");
        introducePagerAdapter.addFragment(IntroduceThreeFragment.newInstance(),"3");
        viewPager.setAdapter(introducePagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==2){
                    btnNext.setText(getString(R.string.done));
                }else {
                    btnNext.setText(getString(R.string.next));
                }
                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void intiView() {
        animationButton= AnimationUtils.loadAnimation(IntroduceActivity.this,R.anim.alpha_button);
        btnBack=(Button)findViewById(R.id.btnBack);
        btnNext=(Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNext.startAnimation(animationButton);
                int viTri=viewPager.getCurrentItem();
                if (viTri==2){
                    editor = getSharedPreferences(TrasosaConstans.CHECK_BEGIN, MODE_PRIVATE).edit();
                    editor.putString(TrasosaConstans.CHECK_BEGIN,TrasosaConstans.OK);
                    editor.apply();
                    finish();
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }else {
                    viewPager.setCurrentItem(viTri+1);
                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBack.startAnimation(animationButton);
                int viTri=viewPager.getCurrentItem();
                if (viTri==0){
                    finish();
                }else {
                    viewPager.setCurrentItem(viTri-1);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
