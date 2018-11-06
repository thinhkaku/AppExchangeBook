package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.UuDai;

import java.util.ArrayList;
import java.util.List;

public class PromotionAdapter extends ArrayAdapter<UuDai> {
    private LayoutInflater inflater;
    private List<UuDai>arrUuDai=new ArrayList<>();
    private Context context;

    public PromotionAdapter(@NonNull Context context, @NonNull List<UuDai> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        inflater=LayoutInflater.from(context);
        arrUuDai=objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=inflater.inflate(R.layout.item_uu_dai,parent,false);
        TextView txtChiTiet=view.findViewById(R.id.txtChiTietUuDai);
        UuDai uuDai=arrUuDai.get(position);
        txtChiTiet.setText(uuDai.getChiTiet());

        return view;
    }
}
