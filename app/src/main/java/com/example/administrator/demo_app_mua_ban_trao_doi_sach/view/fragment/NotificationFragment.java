package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.Utils;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.Notification;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends BaseFragment {
    private List<Notification>arrNotification;
    private NotificationAdapter notificationAdapter;
    private RecyclerView recycleNotification;
    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_notification,container,false);
        recycleNotification=view.findViewById(R.id.recycleNotification);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initAction();
    }

    private void initAction() {
        arrNotification=new ArrayList<>();
        arrNotification.add(new Notification(1,"https://culturebox.francetvinfo.fr/sites/default/files/assets/images/2013/08/audiolib.jpg"));
        arrNotification.add(new Notification(1,"https://www.saga.vn/uploads/%E1%BA%A2nh%20Minh%20H%E1%BB%8Da/magazine-mockup-print-advert_2.jpg"));
        arrNotification.add(new Notification(1,"http://vlhanoi.vn/wp-content/uploads/2015/02/in-sach-bao-tap-chi.png"));
        arrNotification.add(new Notification(1,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0xukBX7QGSfE_OYVuBWas3QTLFsn8KuykNkfu5xcQ1fgDakat"));
        arrNotification.add(new Notification(1,"http://eduvie.com.vn/img/webroot/upload/image/images/chinh-sach-noi-dung-quang-cao-tren-facebook-2.png"));
        arrNotification.add(new Notification(1,"https://png.pngtree.com/thumb_back/fw800/back_pic/05/04/98/655963cd29c56a5.jpg"));
        arrNotification.add(new Notification(1,"https://png.pngtree.com/thumb_back/fw800/back_pic/03/74/02/1457bbe492109a6.jpg"));
        Utils.initRecycleView(recycleNotification,activity);
        notificationAdapter=new NotificationAdapter(activity,arrNotification);
        recycleNotification.setAdapter(notificationAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
