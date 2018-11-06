package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class AdvertisementAdapter extends PagerAdapter {

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    Activity activity;
    ArrayList<String> urlImages;
    public AdvertisementAdapter(Activity activity, ArrayList<String> urlImages) {
        this.activity = activity;
        this.urlImages = urlImages;

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_image_black_24dp)
                .showImageForEmptyUri(R.drawable.ic_image_black_24dp)
                .showImageOnFail(R.drawable.ic_image_black_24dp)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_hinh, container, false);

        ImageView mImageView = (ImageView) view
                .findViewById(R.id.imgAdvertisement);
        final ProgressBar pgLoading = (ProgressBar) view.findViewById(R.id.pg_log);
        imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        imageLoader.displayImage(urlImages.get(position), mImageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                pgLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                //pgLoading.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                pgLoading.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                //pgLoading.setVisibility(View.GONE);
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, ""+urlImages.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return urlImages.size();
    }




    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
