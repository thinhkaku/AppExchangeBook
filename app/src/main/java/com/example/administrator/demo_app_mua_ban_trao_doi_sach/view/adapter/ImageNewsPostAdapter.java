package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.DetailImageActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Hinh;

import java.util.List;

public class ImageNewsPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Hinh>arrHinh;
    private Context context;

    public ImageNewsPostAdapter(List<Hinh> arrHinh, Context context) {
        this.arrHinh = arrHinh;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageNewsPostAdapter.ViewHolder(view);
    }

    private   class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhNoiDung;
        ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhNoiDung=itemView.findViewById(R.id.imgHinhChiTiet);
            progressBar=itemView.findViewById(R.id.progressbarr);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final String img=Constants.PORTIMAGE+arrHinh.get(position).getHinh();
        ViewHolder holder1 = (ViewHolder) holder;
        Utils.loadImage(context,img,holder1.imgHinhNoiDung,holder1.progressBar);
        holder1.imgHinhNoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailImageActivity.class);
                intent.putExtra(Constants.IMAGE,arrHinh.get(position).getHinh());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrHinh.size();
    }
}
