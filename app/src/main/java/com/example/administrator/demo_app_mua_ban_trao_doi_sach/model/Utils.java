package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.config.Util;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.DanhGia;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.anities.User;
import com.koushikdutta.ion.Ion;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Utils {
        public static boolean checkInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
    public static String formatGia(int gia){
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        return decimalFormat.format(gia)+" vnđ";
    }
    public static void stAnimationButton(Context context, View button){
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.alpha_button);
        button.startAnimation(animation);
    }

    public static void stAnimationRecycle(Context context, View button){
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.alpha_title);
        button.startAnimation(animation);
    }
    public static  String formatThoiGian(String date){
        SimpleDateFormat sd=new SimpleDateFormat(Constants.LAST_UPDATE_TIME, Locale.getDefault());
        SimpleDateFormat sd1=new SimpleDateFormat(Constants.FORMAT_TIME, Locale.getDefault());
        try {
            Date date1=sd1.parse(date);
            return sd.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  String formatDateDanhGia(String date){
        SimpleDateFormat sd=new SimpleDateFormat(Constants.DATE_DANH_GIA, Locale.getDefault());
        SimpleDateFormat sd1=new SimpleDateFormat(Constants.FORMAT_TIME, Locale.getDefault());
        try {
            Date date1=sd1.parse(date);
            return sd.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void tinhDanhGia(List<DanhGia>arrDanhGia, TextView txtGiaTri){
            double T=0;
            int size=arrDanhGia.size();
            if(size==0){
                txtGiaTri.setText("5.0");
            }else {
                for (int i=0;i<size;i++)
                {
                    T+=arrDanhGia.get(i).getGiaT();
                }
                double giaTri=T/size;
                DecimalFormat sd=new DecimalFormat(Constants.FORMAT_DANH_GIA);

                txtGiaTri.setText(sd.format(giaTri));
            }

    }

    public static  String formatHourM(String date){
        SimpleDateFormat sd=new SimpleDateFormat(Constants.HOUR, Locale.getDefault());
        SimpleDateFormat sd1=new SimpleDateFormat(Constants.FORMAT_TIME, Locale.getDefault());
        try {
            Date date1=sd1.parse(date);
            return sd.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getDayCurent(){
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        SimpleDateFormat sd=new SimpleDateFormat(Constants.DAY);
        return Integer.parseInt(sd.format(date));
    }
    public static String getDay(String date){
            SimpleDateFormat sd=new SimpleDateFormat(Constants.DAY);
        SimpleDateFormat sd1=new SimpleDateFormat(Constants.FORMAT_TIME, Locale.getDefault());
        try {
            Date date1=sd1.parse(date);
            return sd.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkAcount(Context context){
        User user= Util.getUser(context);
        if (user==null){
            return false;
        }else {
            return true;
        }
    }



    public static MultipartBody.Part getBody(Context context, Uri uri){
        File file= new File(getRealPathFromURI(context,uri));
        RequestBody requestBody = RequestBody.create(
                MediaType.parse(context.getContentResolver().getType(uri)),
                file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(Constants.PRODUCTIMAGE, file.getName(), requestBody);
        return body;
    }
    public static String getRealPathFromURI(Context context,Uri contentUri) {

        Cursor cursor = null;
        try {

            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            return cursor.getString(column_index);
        } finally {

            if (cursor != null) {

                cursor.close();
            }
        }
    }


    public static void loadImage(Context context, String url, ImageView imageView, final ProgressBar progressBarImage){
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions  options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.load)
//                .showImageForEmptyUri(R.drawable.load)
//                .showImageOnFail(R.drawable.load)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .delayBeforeLoading(1000)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(url, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                progressBarImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                progressBarImage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                progressBarImage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                progressBarImage.setVisibility(View.INVISIBLE);
            }
        });
    }

    public static void initRecycleBookHomePage(RecyclerView recyclerView, Activity activity){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL);
        Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.custom_divider_book_homepage);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        DisplayMetrics dimension = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dimension);
    }

    public static void setTextStyle(Activity activity, TextView textView, String nameStyle){
        Typeface typeface=Typeface.createFromAsset(activity.getAssets(), nameStyle);
        textView.setTypeface(typeface);
    }

    public static void initDilogSelectCamera(final Context context, final Fragment mFragment, final int CAMERA_PIC_REQUEST, final int ABUM_PIC_REQUEST)
    {
        final Dialog dlSelect=new Dialog(context);
        dlSelect.setContentView(R.layout.dialog_select_from_image);
        dlSelect.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final LinearLayout lnearCamera=dlSelect.findViewById(R.id.lnearCamera);
        final LinearLayout lnearAbum=dlSelect.findViewById(R.id.lnearAbum);
        lnearCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.stAnimationButton(context,lnearCamera);
                Utils.openCamera((Activity) context,mFragment,CAMERA_PIC_REQUEST);
                dlSelect.dismiss();
            }
        });

        lnearAbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.stAnimationButton(context,lnearAbum);
                Utils.openAbum((Activity) context,mFragment,ABUM_PIC_REQUEST);
                dlSelect.dismiss();
            }
        });
        dlSelect.show();
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static void initRecycleView(RecyclerView recyclerView, Activity activity){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemViewCacheSize(30);
        recyclerView.setDrawingCacheEnabled(true);

        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        DisplayMetrics dimension = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dimension);
    }
    private static class WrapContentLinearLayoutManager extends LinearLayoutManager {

        public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }



    public static void initPermission(Activity activity, Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (fragment==null){
                    if (activity.shouldShowRequestPermissionRationale(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        //Toast.makeText(DetailActivatedActivity.this, "Permission isn't granted ", Toast.LENGTH_SHORT).show();
                    }
                    // Permisson don't granted and dont show mDialogCamera again.
                    else {
                        //Toast.makeText(DetailActivatedActivity.this, "Permisson don't granted and dont show mDialogCamera again ", Toast.LENGTH_SHORT).show();
                    }
                    //Register permission
                    activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else {
                    if (fragment.shouldShowRequestPermissionRationale(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        //Toast.makeText(DetailActivatedActivity.this, "Permission isn't granted ", Toast.LENGTH_SHORT).show();
                    }
                    // Permisson don't granted and dont show mDialogCamera again.
                    else {
                        //Toast.makeText(DetailActivatedActivity.this, "Permisson don't granted and dont show mDialogCamera again ", Toast.LENGTH_SHORT).show();
                    }
                    //Register permission
                    fragment.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        }
    }

    public static Bitmap getThumbnail(Context context,Uri uri,int THUMBNAIL_SIZE) throws FileNotFoundException, IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();

        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > THUMBNAIL_SIZE) ? (originalSize / THUMBNAIL_SIZE) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true; //optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }

    public static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }

    public static void openCamera(Activity activity,Fragment fragment, int RESULT){
        initPermission(activity,fragment);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (fragment==null){
            activity.startActivityForResult(cameraIntent, RESULT);
        }else {
            fragment.startActivityForResult(cameraIntent, RESULT);
        }
    }

    public static void  openAbum(Activity activity,Fragment fragment, int RESULT){
        initPermission(activity,fragment);
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        if (fragment==null){
            activity.startActivityForResult(intent,RESULT);
        }else {
            fragment.startActivityForResult(intent,RESULT);
        }
    }





    public static void scaleImage(Activity activity, ImageView view) throws NoSuchElementException {
        // Get bitmap from the the ImageView.
        Bitmap bitmap = null;

        try {
            Drawable drawing = view.getDrawable();
            bitmap = ((BitmapDrawable) drawing).getBitmap();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("No drawable on given view");
        } catch (ClassCastException e) {
            // Check bitmap is Ion drawable
            bitmap = Ion.with(view).getBitmap();
        }

        // Get current dimensions AND the desired bounding box
        int width = 0;

        try {
            width = bitmap.getWidth();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Can't find bitmap on given view/drawable");
        }

        int height = bitmap.getHeight();
        int bounding = dpToPx(150, activity);

        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);


        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);

        view.setImageDrawable(result);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private static int dpToPx(int dp, Activity activity) {
        float density = activity.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }

    public static File makeImageFile(Bitmap bitmap) {
        OutputStream fOut;
        File mediaStorageDir = new File(
                Environment
                        .getExternalStorageDirectory()
                        + File.separator
                        + "image");
        File file = new File(mediaStorageDir.getPath(), String.valueOf(System
                .currentTimeMillis()) + ".jpg");
        try {
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }


    }

    public static int getWidthScreen(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
    public static int getHeightScreen(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height;
    }

    public static void listenEditText(EditText editText, final Button button){
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length()!=0){
                        button.setVisibility(View.VISIBLE);
                    }else {
                        button.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
    }



}
