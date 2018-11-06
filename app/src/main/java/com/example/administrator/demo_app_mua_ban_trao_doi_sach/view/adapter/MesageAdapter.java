package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity.DetailImageActivity;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Message;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;

import java.util.ArrayList;

import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

public class MesageAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Message> arrMessage;
    private ArrayList<Message> arrMessage1;
    private User user;

    public MesageAdapter(Context context, ArrayList<Message> arrMessage, User user) {
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.arrMessage = arrMessage;
        this.arrMessage1 = arrMessage;
        this.user=user;
    }

    @Override
    public int getCount() {
        return arrMessage.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler=null;
        View view=inflater.inflate(R.layout.item_message,parent,false);
        if (viewHodler==null){
            viewHodler=new ViewHodler();
            viewHodler.txtTinNhan=view.findViewById(R.id.txtTinNhan);
            viewHodler.lneaTinNhan=view.findViewById(R.id.lneaTinNhan);
            viewHodler.lnearBorderTinNhan=view.findViewById(R.id.lnearBorderTinNhan);
            viewHodler.imgNoiDungTinNhan=view.findViewById(R.id.imgNoiDungTinNhan);
            viewHodler.frameImage=view.findViewById(R.id.frameImage);
            viewHodler.txtDaXem=view.findViewById(R.id.txtDaXem);
            view.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) view.getTag();
        }
        Message message = arrMessage.get(position);
        if (user.getId()== message.getIdKhachHang()){
            viewHodler.lneaTinNhan.setGravity(Gravity.RIGHT);
            viewHodler.lnearBorderTinNhan.setBackgroundResource(R.drawable.border_tin_nhan1);
        }else {
            viewHodler.lneaTinNhan.setGravity(Gravity.LEFT);
            viewHodler.lnearBorderTinNhan.setBackgroundResource(R.drawable.border_tin_nhan);
        }
        final String noiDung= message.getNoiDung();
        if (noiDung.contains(".jpg")){
            viewHodler.lnearBorderTinNhan.setVisibility(View.GONE);
            viewHodler.frameImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(Constants.PORTIMAGE+noiDung).into(viewHodler.imgNoiDungTinNhan);
        }else {
            if (position==1&&position==arrMessage.size()-1){
                if (arrMessage.get(position).getIdKhachHang()!=arrMessage.get(position-1).getIdKhachHang()){
                    viewHodler.txtTinNhan.setText("\t"+ message.getNoiDung()+"\t"+"\n");
                }else {
                    viewHodler.txtTinNhan.setText("\t"+ message.getNoiDung()+"\t"+"\n"+ Utils.formatThoiGian(message.getThoiGian()));
                }
            }else {
                viewHodler.txtTinNhan.setText("\t"+ message.getNoiDung()+"\t"+"\n"+ Utils.formatThoiGian(message.getThoiGian()));
            }


        }

        viewHodler.imgNoiDungTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailImageActivity.class);
                intent.putExtra(Constants.IMAGE,"/"+noiDung);
                context.startActivity(intent);
            }
        });

        return view;
    }

    public void upDateTinNhan(String newtext){
        if (newtext.isEmpty()){
            arrMessage = arrMessage1;
        }else {
            ArrayList<Message> arrMessage2 =new ArrayList<>();
            for (Message message : arrMessage1){
                if (message.getNoiDung().toLowerCase().contains(newtext)){
                    arrMessage2.add(message);
                }
            }
            arrMessage = arrMessage2;
        }
        notifyDataSetChanged();
    }

    private class  ViewHodler{
        private EmojiconTextView txtTinNhan;
        private LinearLayout lneaTinNhan;
        private LinearLayout lnearBorderTinNhan;
        private ImageView imgNoiDungTinNhan;
        private FrameLayout frameImage;
        private TextView txtDaXem;
    }
}
