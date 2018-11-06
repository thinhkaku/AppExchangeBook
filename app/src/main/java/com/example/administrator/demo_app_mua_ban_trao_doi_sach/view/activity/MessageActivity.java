package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.TrasosaConstans;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.FragmentGroupChat;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.DetailMessageFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MessageActivity extends BaseActivity{
    private DetailMessageFragment detailMessageFragment;
    private FragmentGroupChat fragmentGroupChat;
    private MaterialSearchView materialSearchView;
    private Toolbar toolbar;
    private Handler handler;
    private Runnable runnable;


    public DetailMessageFragment getDetailMessageFragment() {
        return detailMessageFragment;
    }

    public FragmentGroupChat getFragmentGroupChat() {
        return fragmentGroupChat;
    }


    @Override
    protected void requestAgain() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_message);
        toolbar=findViewById(R.id.toolbarNhanTin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        addFragmmemt();
        materialSearchView=findViewById(R.id.btnMaterSearchViewNhanTin);
    }

    private void processCheck(){
         handler=new Handler();
         runnable=new Runnable() {
            @Override
            public void run() {
                checkSearchView();
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
    private void checkSearchView(){
        if (materialSearchView.isSearchOpen()){
            toolbar.setVisibility(View.INVISIBLE);
        }else {
            toolbar.setVisibility(View.VISIBLE);
            handler.removeCallbacks(runnable);
        }
    }

    private void addFragmmemt() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentGroupChat =new FragmentGroupChat();
        detailMessageFragment =new DetailMessageFragment();
        fragmentTransaction.add(R.id.frPanel, fragmentGroupChat);
        fragmentTransaction.add(R.id.frPanel, detailMessageFragment, Constants.NOI_DUNG_TIN_NHAN_FRAGMENT);
        fragmentTransaction.commit();

        swicthFrament(fragmentGroupChat);
    }
    public void swicthFrament(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragmentGroupChat);
        fragmentTransaction.hide(detailMessageFragment);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DetailMessageFragment test = (DetailMessageFragment) getSupportFragmentManager().findFragmentByTag(Constants.NOI_DUNG_TIN_NHAN_FRAGMENT);
        if (item.getItemId()==android.R.id.home){
            if (test != null && test.isVisible()) {
                toolbar.setTitle(TrasosaConstans.TIN_NHAN);
                swicthFrament(getFragmentGroupChat());
            }
            else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTilteToolBar(String title){
        toolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final DetailMessageFragment test = (DetailMessageFragment) getSupportFragmentManager().findFragmentByTag(Constants.NOI_DUNG_TIN_NHAN_FRAGMENT);
        final DetailMessageFragment detailMessageFragment = getDetailMessageFragment();
        final FragmentGroupChat fragmentGroupChat = getFragmentGroupChat();
        getMenuInflater().inflate(R.menu.menu_search_tin_nhan,menu);
        MenuItem item=menu.findItem(R.id.btnSearchTinNhan);
        materialSearchView.setMenuItem(item);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processCheck();
                if (test != null && test.isVisible()) {
                    detailMessageFragment.updateData(newText);
                }
                else {
                    fragmentGroupChat.updateData(newText);
                }
                return true;
            }
        });
        return true;

    }



    @Override
    public void onBackPressed() {
        DetailMessageFragment test = (DetailMessageFragment) getSupportFragmentManager().findFragmentByTag(Constants.NOI_DUNG_TIN_NHAN_FRAGMENT);
        if (materialSearchView.isSearchOpen()){
            materialSearchView.closeSearch();
        }else {
            if (test != null && test.isVisible()) {
                toolbar.setTitle(TrasosaConstans.TIN_NHAN);
                swicthFrament(getFragmentGroupChat());
            }
            else {
                finish();
            }
        }

    }

}
