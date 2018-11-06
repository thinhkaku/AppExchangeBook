package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<BiaSach>arrSach;
    private LayoutInflater inflater;
    private Activity mActivity;


    public BookAdapter(Context mContext, List<BiaSach> arrSach) {
        this.mContext = mContext;
        this.mActivity= (Activity) mContext;
        this.arrSach = arrSach;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_book,parent,false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BiaSach sach=arrSach.get(position);
        ViewHodler hodler1= (ViewHodler) holder;
        Utils.stAnimationRecycle(mContext,hodler1.itemCard);
        hodler1.txtTen.setText(sach.getTenS());
        hodler1.txtGiamGia.setText("-"+sach.getGiamGia()+" %");

        Picasso.with(mContext).load(sach.getHinhS()).resize(100,250).into(hodler1.imgSach);
        mActivity.registerForContextMenu(hodler1.btnThem);
        int gia=sach.getGiaS()-sach.getGiaS()*sach.getGiamGia()/100;
        hodler1.txtGia.setText(Utils.formatGia(gia));
        hodler1.txtGiaGoc.setText(Utils.formatGia(sach.getGiaS()));
        hodler1.txtGiaGoc.setPaintFlags(hodler1.txtGia.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
        final Intent intent=new Intent(mActivity, BookDetailActivity.class);
        hodler1.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreseventConstant.getInstance(mContext).setIdS(sach.getIdS());
                mActivity.openContextMenu(v);
            }
        });
        hodler1.imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreseventConstant.getInstance(mContext).setIdS(sach.getIdS());
                intent.putExtra(Constants.CHECK,false);
                mActivity.startActivity(intent);
            }
        });
        hodler1.btnChonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreseventConstant.getInstance(mContext).setIdS(sach.getIdS());
                intent.putExtra(Constants.CHECK,true);
                mActivity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrSach.size();
    }

    private class ViewHodler extends RecyclerView.ViewHolder{
        ImageButton btnThem;
        ImageView imgSach;
        TextView txtTen, txtGia,txtGiamGia,txtGiaGoc;
        Button btnChonMua;
        CardView itemCard;
        public ViewHodler(View itemView) {
            super(itemView);
            btnThem=itemView.findViewById(R.id.btnThemChiTiet);
            imgSach=itemView.findViewById(R.id.txtHinhSach);
            txtTen=itemView.findViewById(R.id.txtTenS);
            txtGia=itemView.findViewById(R.id.txtGSach);
            txtGiamGia=itemView.findViewById(R.id.txtGiamGia);
            txtGiaGoc=itemView.findViewById(R.id.txtGiaGoc);
            btnChonMua=itemView.findViewById(R.id.btnChonMua);
            itemCard=itemView.findViewById(R.id.itemCard);
            Utils.setTextStyle(mActivity,txtTen, Constants.FONT_STYLE);
        }
    }
}
