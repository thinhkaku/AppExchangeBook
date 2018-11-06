package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.dilog.MyLoadAppProgessBar;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.presenter.PresenterLogicBookDetailFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.BookDetailActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.RateBookAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.BookAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.PromotionAdapter;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.logininterface.SOService;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Sach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.UuDai;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.DanhGia;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit.ApiUtils;
import java.util.ArrayList;
import java.util.List;

public class BookDetailFragment extends BaseFragment implements View.OnClickListener, ViewBookDetailFragment {
    private ImageButton btnBack;
    private ImageView imgHinhSach;
    private TextView txtGiamGia,txtSLDanhGia, txtGiaMoi,txtGiaCu,txtTenSach,txtTinhTrang,txtChiTiet,txtTG,txtNXB,txtKt,txtHT,txtSt,txtNn,txtLm,txtLS,txtGiaTriDG;
    private Button btnChonMua,btnXemNX, btnVietNX;
    private ListView lvUudai;
    private SOService soService;
    private List<Sach>arrSach;
    private BookDetailActivity bookDetailActivity;
    private List<UuDai>arrUuDai;
    private int gia;
    MyLoadAppProgessBar myLoadAppProgessBar;
    private RecyclerView recycleGoiYSach, recycleDanhGia;
    private ProgressBar progressBarGoiYSach, progressBarImage;
    private BookAdapter bookAdapter;
    private RateBookAdapter rateBookAdapter;
    private List<DanhGia>arrDanhGia=new ArrayList<>();
    private boolean checkRecycleDanhGia;
    private Dialog dlVietNhanXet;
    private int idS;
    private PresenterLogicBookDetailFragment presenterLogicBookDetailFragment;

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    public List<Sach> getTTSach(){
        return arrSach;
    }


    @Override
    public void onAttach(Activity activity) {
        this.bookDetailActivity = (BookDetailActivity) activity;
        super.onAttach(activity);
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_detail_book,container,false);
        soService=ApiUtils.getSOService();
        presenterLogicBookDetailFragment=new PresenterLogicBookDetailFragment(this,soService);
        myLoadAppProgessBar=new MyLoadAppProgessBar(context);
        btnBack=view.findViewById(R.id.btnBack);
        imgHinhSach=view.findViewById(R.id.imgHinhSach);
        txtGiamGia=view.findViewById(R.id.txtGiamGia);
        txtGiaMoi=view.findViewById(R.id.txtGiaMoi);
        txtGiaCu=view.findViewById(R.id.txtGiaCu);
        txtTenSach=view.findViewById(R.id.txtTenSach);
        txtChiTiet=view.findViewById(R.id.txtChiTiet);
        txtTinhTrang=view.findViewById(R.id.txtTinhTrang);
        txtGiaTriDG=view.findViewById(R.id.txtGiaTriDG);
        txtSLDanhGia=view.findViewById(R.id.txtSLDanhGia);
        recycleDanhGia=view.findViewById(R.id.recycleDanhGia);
        btnXemNX=view.findViewById(R.id.btnXemNX);
        btnVietNX=view.findViewById(R.id.btnVietNX);
        txtTG=view.findViewById(R.id.txtTG);
        txtHT=view.findViewById(R.id.txtHT);
        txtLm=view.findViewById(R.id.txtLm);
        txtKt=view.findViewById(R.id.txtKt);
        txtNn=view.findViewById(R.id.txtNn);
        txtSt=view.findViewById(R.id.txtSt);
        txtNXB=view.findViewById(R.id.txtNXB);
        txtLS=view.findViewById(R.id.txtLS);
        btnChonMua=view.findViewById(R.id.btnChonMua);
        lvUudai=view.findViewById(R.id.lvUudai);
        recycleGoiYSach=view.findViewById(R.id.recycleGoiYSach);
        progressBarGoiYSach=view.findViewById(R.id.progressBarGoiYSach);
        progressBarImage=view.findViewById(R.id.progressBarImage);
        arrSach=new ArrayList<>();
        arrUuDai=new ArrayList<>();
        getData();
        initRecycleSachMoi();
        initRecycleDanhGia();
        addEvent();
        return view;
    }

    private void getDanhGia(){
        presenterLogicBookDetailFragment.getRateBook(idS);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void getSachGoiY(int idLS){
        presenterLogicBookDetailFragment.getEvaluate(idLS,progressBarGoiYSach);
    }

    private void initRecycleDanhGia(){
        recycleDanhGia.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(bookDetailActivity, LinearLayoutManager.VERTICAL, false);
        recycleDanhGia.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleDanhGia.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(bookDetailActivity, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recycleDanhGia.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        bookDetailActivity.getWindowManager().getDefaultDisplay().getMetrics(dimension);
    }

    private void initRecycleSachMoi() {
        recycleGoiYSach.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(bookDetailActivity, LinearLayoutManager.HORIZONTAL, false);
        recycleGoiYSach.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleGoiYSach.getContext(), DividerItemDecoration.HORIZONTAL);
        Drawable drawable = ContextCompat.getDrawable(bookDetailActivity, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recycleGoiYSach.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        bookDetailActivity.getWindowManager().getDefaultDisplay().getMetrics(dimension);

    }

    private void getChiTietSach(int idS){
        presenterLogicBookDetailFragment.getDetailBook(idS,myLoadAppProgessBar);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void getData() {
        idS= SharePreseventConstant.getInstance(bookDetailActivity).getIdS();
        getChiTietSach(idS);
    }

    private void ttSach(final List<Sach> arrSach) {
        txtTenSach.setText(arrSach.get(0).getTenS());
        txtGiaCu.setText("Giá cũ: "+Utils.formatGia(arrSach.get(0).getGiaS()));
        gia=arrSach.get(0).getGiaS()-arrSach.get(0).getGiaS()*arrSach.get(0).getGiamGia()/100;
        txtGiaMoi.setText("Chỉ còn: "+ Utils.formatGia(gia));
        txtGiaCu.setPaintFlags(txtGiaCu.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
        txtGiamGia.setText("- "+arrSach.get(0).getGiamGia()+" %");
        txtChiTiet.setText(arrSach.get(0).getMota());
        txtLS.setText(Util.loaiSach(arrSach.get(0).getIdLS()));
        Utils.loadImage(bookDetailActivity,arrSach.get(0).getHinhS(),imgHinhSach, progressBarImage);
        if (arrSach.get(0).getTt()==0){
            txtTinhTrang.setTextColor(Color.GREEN);
            txtTinhTrang.setText("Tình trạng: Còn hàng");
        }else {
            txtTinhTrang.setTextColor(Color.RED);
            txtTinhTrang.setPaintFlags(txtTinhTrang.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
            txtTinhTrang.setText("Tình trạng: Đã hết hàng");
        }
        txtNXB.setText(arrSach.get(0).getNxb());
        txtTG.setText(arrSach.get(0).getTg());
        txtKt.setText(arrSach.get(0).getKt());
        if (arrSach.get(0).getHt()==1)
        {
            txtHT.setText(context.getString(R.string.bia_mem));
        }else {
            txtHT.setText(context.getString(R.string.bia_cung));
        }
        txtSt.setText(arrSach.get(0).getSt()+"");
        txtNn.setText(arrSach.get(0).getNn());
        txtLm.setText(arrSach.get(0).getLuotMua()+"");
        getSachGoiY(arrSach.get(0).getIdLS());
    }

    private void addEvent() {
        btnBack.setOnClickListener(this);
        btnChonMua.setOnClickListener(this);
        btnXemNX.setOnClickListener(this);
        btnVietNX.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnBack:
                bookDetailActivity.finish();
                break;
            case R.id.btnChonMua:
                bookDetailActivity.swichFragment(bookDetailActivity.getFillOutPersonalInformationFragment());
                break;
            case R.id.btnXemNX:
                if (checkRecycleDanhGia==false){
                    btnXemNX.setText(getString(R.string.an_nhan_xet));
                    recycleDanhGia.setVisibility(View.VISIBLE);
                    checkRecycleDanhGia=true;
                }else {
                    btnXemNX.setText(getString(R.string.x_nhan_xet));
                    recycleDanhGia.setVisibility(View.GONE);
                    checkRecycleDanhGia=false;
                }

                break;
            case R.id.btnVietNX:
                initDialogVietNhanXet();
                break;
        }
    }

    private void initDialogVietNhanXet(){
        final int[] evaluate = {5};
        dlVietNhanXet=new Dialog(bookDetailActivity);
        dlVietNhanXet.setContentView(R.layout.dialog_edit_comment);
        dlVietNhanXet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dlVietNhanXet.setCancelable(false);
        dlVietNhanXet.setCanceledOnTouchOutside(false);
        TextView btnCancelDialog=dlVietNhanXet.findViewById(R.id.btnCancelDialog);
        final EditText edtComment=dlVietNhanXet.findViewById(R.id.edtComment);
        final EditText edtName=dlVietNhanXet.findViewById(R.id.edtName);
        Button btnSendComment=dlVietNhanXet.findViewById(R.id.btnSendComment);
        ImageView imgStart1=dlVietNhanXet.findViewById(R.id.imgStart1);
        final ImageView imgStart2=dlVietNhanXet.findViewById(R.id.imgStart2);
        final ImageView imgStart3=dlVietNhanXet.findViewById(R.id.imgStart3);
        final ImageView imgStart4=dlVietNhanXet.findViewById(R.id.imgStart4);
        final ImageView imgStart5=dlVietNhanXet.findViewById(R.id.imgStart5);
        imgStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate[0] =1;
                imgStart2.setImageResource(R.drawable.custom_start);
                imgStart3.setImageResource(R.drawable.custom_start);
                imgStart4.setImageResource(R.drawable.custom_start);
                imgStart5.setImageResource(R.drawable.custom_start);
            }
        });
        imgStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate[0] =2;
                imgStart2.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart3.setImageResource(R.drawable.custom_start);
                imgStart4.setImageResource(R.drawable.custom_start);
                imgStart5.setImageResource(R.drawable.custom_start);
            }
        });
        imgStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate[0] =3;
                imgStart2.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart3.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart4.setImageResource(R.drawable.custom_start);
                imgStart5.setImageResource(R.drawable.custom_start);
            }
        });
        imgStart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate[0] =4;
                imgStart2.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart3.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart4.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart5.setImageResource(R.drawable.custom_start);
            }
        });
        imgStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate[0] =5;
                imgStart2.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart3.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart4.setImageResource(R.drawable.ic_star_black_24dp);
                imgStart5.setImageResource(R.drawable.ic_star_black_24dp);
            }
        });
        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlVietNhanXet.dismiss();
            }
        });
        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtName.getText().toString();
                String comment=edtComment.getText().toString();
                presenterLogicBookDetailFragment.sendRate(idS,name,comment,evaluate[0], myLoadAppProgessBar);
                }
        });

        dlVietNhanXet.show();
    }

    @Override
    public void onSuccessSachGoiy(List<BiaSach> arrSach) {
        bookAdapter =new BookAdapter(bookDetailActivity,arrSach);
        recycleGoiYSach.setAdapter(bookAdapter);
    }

    @Override
    public void onErr() {

    }

    @Override
    public void onSuccessPromotion(List<UuDai> arrPromotion) {
        arrUuDai=arrPromotion;
        PromotionAdapter promotionAdapter =new PromotionAdapter(bookDetailActivity,arrUuDai);
        lvUudai.setAdapter(promotionAdapter);
    }

    @Override
    public void onSuccessDetailBook(List<Sach> arrDetailB) {
        arrSach=arrDetailB;
        ttSach(arrSach);
        getDanhGia();
    }

    @Override
    public void onSuccessGetRateBook(List<DanhGia> arrRateBook) {
        arrDanhGia=arrRateBook;
        rateBookAdapter =new RateBookAdapter(bookDetailActivity,arrDanhGia);
        recycleDanhGia.setAdapter(rateBookAdapter);
        txtSLDanhGia.setText(getString(R.string.sl_danh_gia)+" "+arrDanhGia.size()+" " +getString(R.string.nx));
        Utils.tinhDanhGia(arrDanhGia,txtGiaTriDG);
    }

    @Override
    public void onSuccessSendRateBook() {
        Toast.makeText(bookDetailActivity, getString(R.string.nx_thanh_cong), Toast.LENGTH_SHORT).show();
        dlVietNhanXet.dismiss();
        getDanhGia();
    }

    @Override
    public void onErrSendRateBook() {
        Toast.makeText(bookDetailActivity, getString(R.string.khong_de_trong), Toast.LENGTH_SHORT).show();
    }
}

