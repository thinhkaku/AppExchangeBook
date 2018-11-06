package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.BookDetailActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.database.GetDataFromSqlite;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Sach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.GioHang;

import java.util.ArrayList;
import java.util.List;

public class FillOutPersonalInformationFragment extends BaseFragment implements View.OnClickListener {
    private ImageButton btnBackTT,btnAddSl,btnRemoveSl;
    private Button btnThemGioHang;
    private TextView txtGiaS,txtSL,txtTongTien;
    private BookDetailActivity bookDetailActivity;
    private BookDetailFragment bookDetailFragment;
    private int sL=1;
    private int gia;
    private int tongTien=0;
    private Context context;
    private List<Sach>arrSach;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onAttach(Activity activity) {
        bookDetailActivity = (BookDetailActivity) activity;
        super.onAttach(activity);
    }



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_fill_out_personal_information,container,false);
        btnBackTT=view.findViewById(R.id.btnBackTT);
        txtGiaS=view.findViewById(R.id.txtGiaS);
        txtTongTien=view.findViewById(R.id.txtTongTien);
        btnAddSl=view.findViewById(R.id.btnAddSl);
        btnRemoveSl=view.findViewById(R.id.btnRemoveSl);
        btnThemGioHang=view.findViewById(R.id.btnThemGioHang);
        txtSL=view.findViewById(R.id.txtSL);
        arrSach=new ArrayList<>();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        addEvent();
    }

    private void initData() {
        txtSL.setText(sL+"");
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if (gia==0){
                    bookDetailFragment = bookDetailActivity.getBookDetailFragment();
                    arrSach= bookDetailFragment.getTTSach();
                    if (arrSach!=null && arrSach.size()!=0){
                        gia=arrSach.get(0).getGiaS()-arrSach.get(0).getGiaS()*arrSach.get(0).getGiamGia()/100;;
                    }

                    tongTien();
                    txtGiaS.setText("Giá: "+Utils.formatGia(gia));
                }else {
                    handler.removeCallbacks(this);
                }
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);

    }
    private void tongTien()
    {
        tongTien=gia*sL;
        txtTongTien.setText("Tổng tiền: "+ Utils.formatGia(tongTien));
    }

    private void addEvent() {
        btnBackTT.setOnClickListener(this);
        btnAddSl.setOnClickListener(this);
        btnRemoveSl.setOnClickListener(this);
        btnThemGioHang.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnBackTT:
                bookDetailActivity.swichFragment(bookDetailActivity.getBookDetailFragment());
                break;
            case R.id.btnAddSl:
                Utils.stAnimationButton(bookDetailActivity,btnAddSl);
                sL++;
                txtSL.setText(sL+"");
                tongTien();
                break;
            case R.id.btnRemoveSl:
                Utils.stAnimationButton(bookDetailActivity,btnRemoveSl);
                sL--;
                if (sL==0){
                    sL=1;
                }
                tongTien();
                txtSL.setText(sL+"");
                break;
            case R.id.btnThemGioHang:
            if (arrSach!=null &&arrSach.size()!=0){
                GioHang gioHang=new GioHang(1,arrSach.get(0).getTenS(),arrSach.get(0).getHinhS(),gia,sL,0);
                GetDataFromSqlite getDataFromSqlite=new GetDataFromSqlite();
                List<GioHang>arrGioHang1=new ArrayList<>();
                arrGioHang1=getDataFromSqlite.getGioHang();
                int dem=0;
                for (int i=0;i<arrGioHang1.size();i++){
                    if (gioHang.getTenS().equalsIgnoreCase(arrGioHang1.get(i).getTenS())){
                        dem++;
                    }
                }
                if (dem!=0){
                    Toast.makeText(bookDetailActivity, context.getString(R.string.sp_da_co_trong_gh), Toast.LENGTH_SHORT).show();
                }else {
                    getDataFromSqlite.insert(gioHang);
                    Toast.makeText(context,context.getString(R.string.add_gh_success),Toast.LENGTH_SHORT).show();
                }
            }
                break;
        }
    }
}

