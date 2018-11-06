package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.SharePreseventConstant;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Notification;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.BookDetailActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.BookOptionsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Notification>arrListNotification;
    private LayoutInflater inflater;

    public NotificationAdapter(Context context, List<Notification> arrListNotification) {
        this.context = context;
        this.arrListNotification = arrListNotification;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_notifinecation,parent,false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Notification notification=arrListNotification.get(position);
        final ViewHodler viewHodler= (ViewHodler) holder;
        Picasso.with(context).load(notification.getImgPath()).into(viewHodler.imgNotification);
        viewHodler.imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextToDS(position,viewHodler.imgNotification);
            }
        });
    }

    private void nextToDS(int idLS,View view)
    {
        Utils.stAnimationButton(context,view);
        Intent intent=new Intent(context, BookOptionsActivity.class);
        intent.putExtra(Constants.ID,idLS);
        context.startActivity(intent);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return arrListNotification.size();
    }

    private class ViewHodler extends RecyclerView.ViewHolder{
        ImageView imgNotification;

        public ViewHodler(View itemView) {
            super(itemView);
            imgNotification=itemView.findViewById(R.id.imgNotifinecation);
        }
    }
}
