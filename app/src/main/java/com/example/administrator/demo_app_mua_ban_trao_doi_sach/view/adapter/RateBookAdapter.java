package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.DanhGia;

import java.util.ArrayList;
import java.util.List;

public class RateBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<DanhGia>arrDanhGia=new ArrayList<>();

    public RateBookAdapter(Context context, List<DanhGia> arrDanhGia) {
        this.context = context;
        this.arrDanhGia = arrDanhGia;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_evaluetion,parent,false);
        return new RateBookAdapter.ViewHolder(view);
    }


    private   class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtND, txtTime;
        ImageView  imgStart2, imgStart3, imgStart4, imgStart5;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtND=itemView.findViewById(R.id.txtND);
            txtTime=itemView.findViewById(R.id.txtTime);
            imgStart2=itemView.findViewById(R.id.imgStart2);
            imgStart3=itemView.findViewById(R.id.imgStart3);
            imgStart4=itemView.findViewById(R.id.imgStart4);
            imgStart5=itemView.findViewById(R.id.imgStart5);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DanhGia danhGia=arrDanhGia.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        holder1.txtName.setText(danhGia.getTen());
        holder1.txtTime.setText(Utils.formatDateDanhGia(danhGia.getTime()));
        holder1.txtND.setText(danhGia.getDanhG());
        int evaluate=danhGia.getGiaT();
        if (evaluate==1){
            holder1.imgStart2.setImageResource(R.drawable.custom_start);
            holder1.imgStart3.setImageResource(R.drawable.custom_start);
            holder1.imgStart4.setImageResource(R.drawable.custom_start);
            holder1.imgStart5.setImageResource(R.drawable.custom_start);
        }else if (evaluate==2){
            holder1.imgStart2.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart3.setImageResource(R.drawable.custom_start);
            holder1.imgStart4.setImageResource(R.drawable.custom_start);
            holder1.imgStart5.setImageResource(R.drawable.custom_start);
        }else if (evaluate==3){
            holder1.imgStart2.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart3.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart4.setImageResource(R.drawable.custom_start);
            holder1.imgStart5.setImageResource(R.drawable.custom_start);
        }else if (evaluate==4){
            holder1.imgStart2.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart3.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart4.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart5.setImageResource(R.drawable.custom_start);
        }else {
            holder1.imgStart2.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart3.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart4.setImageResource(R.drawable.ic_star_black_24dp);
            holder1.imgStart5.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return arrDanhGia.size();
    }
}
