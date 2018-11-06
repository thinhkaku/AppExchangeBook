package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.BookDetailActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.BiaSach;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookOptionAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<BiaSach>arrBiaSach=new ArrayList<>();
    private List<BiaSach>arrBiaSach1=new ArrayList<>();
    private Context context;
    private Activity activity;

    public BookOptionAdapter(List<BiaSach> arrBiaSach, Context context) {
        this.arrBiaSach = arrBiaSach;
        this.arrBiaSach1 = arrBiaSach;
        this.context = context;
        this.activity= (Activity) context;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return arrBiaSach.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHoder viewHoder;
        if (view==null){
            viewHoder=new ViewHoder();
            view=inflater.inflate(R.layout.item_book,parent,false);
            viewHoder.itemCard=view.findViewById(R.id.itemCard);
            viewHoder.btnChonMua=view.findViewById(R.id.btnChonMua);
            viewHoder.btnThemChiTiet=view.findViewById(R.id.btnThemChiTiet);
            viewHoder.txtTenS=view.findViewById(R.id.txtTenS);
            viewHoder.txtGiaGoc=view.findViewById(R.id.txtGiaGoc);
            viewHoder.txtGiamGia=view.findViewById(R.id.txtGiamGia);
            viewHoder.txtGSach=view.findViewById(R.id.txtGSach);
            viewHoder.imgHinhSach=view.findViewById(R.id.txtHinhSach);
            view.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoder) view.getTag();
        }
        final BiaSach sach=arrBiaSach.get(position);
        Utils.stAnimationRecycle(context,viewHoder.itemCard);
        viewHoder.txtTenS.setText(sach.getTenS());
        viewHoder.txtGiamGia.setText("-"+sach.getGiamGia()+" %");
        Picasso.with(context).load(sach.getHinhS()).into(viewHoder.imgHinhSach);
        activity.registerForContextMenu(viewHoder.btnThemChiTiet);
        int gia=sach.getGiaS()-sach.getGiaS()*sach.getGiamGia()/100;
        viewHoder.txtGSach.setText(Utils.formatGia(gia));
        viewHoder.txtGiaGoc.setText(Utils.formatGia(sach.getGiaS()));
        viewHoder.txtGiaGoc.setPaintFlags(viewHoder.txtGiaGoc.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
        final Intent intent=new Intent(context, BookDetailActivity.class);
        viewHoder.btnThemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreseventConstant.getInstance(context).setIdS(sach.getIdS());
                activity.openContextMenu(v);
            }
        });
        viewHoder.imgHinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreseventConstant.getInstance(context).setIdS(sach.getIdS());
                intent.putExtra(Constants.CHECK,false);
                activity.startActivity(intent);
            }
        });
        viewHoder.btnChonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreseventConstant.getInstance(context).setIdS(sach.getIdS());
                intent.putExtra(Constants.CHECK,true);
                activity.startActivity(intent);
            }
        });
        return view;
    }
    private class ViewHoder{
        TextView txtGiamGia, txtTenS,txtGSach, txtGiaGoc;
        ImageButton btnThemChiTiet;
        ImageView imgHinhSach;
        Button btnChonMua;
        CardView itemCard;
    }
}
