package com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo_app_mua_ban_trao_doi_sach.R;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.ButtonAnimation;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Constants;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.constant.Singleton;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.MainRegisterFragment;
import com.example.administrator.demo_app_mua_ban_trao_doi_sach.view.fragment.LoginFragment;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
//import com.facebook.CallbackManager;
//import com.facebook.login.widget.LoginButton;

public class Login extends BaseActivity{
//    LoginButton loginButton;
//    CallbackManager callbackManager;
    private TextView btnLogin, btnRegister;
    private LoginFragment loginFragment;
    private MainRegisterFragment mainRegisterFragment;
    private FragmentTransaction fragmentTransaction;
    private Socket socket;


    {
        try {
            socket = IO.socket(Constants.PORT);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

//    <com.facebook.login.widget.LoginButton
//    android:id="@+id/login_button"
//    android:layout_width="wrap_content"
//    android:layout_height="30sp"
//    android:layout_marginLeft="5sp"
//    android:layout_marginRight="15sp"
//    android:layout_gravity="center_horizontal"
//    android:layout_marginBottom="15dp"
//    android:layout_marginTop="15dp" />

    @Override
    protected void requestAgain() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // FacebookSdk.sdkInitialize(this);

//        loginButton=findViewById(R.id.login_button);
//        callbackManager = CallbackManager.Factory.create();
//        FacebookCallback<LoginResult>callback=new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        };
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                final String userId=loginResult.getAccessToken().getUserId();
//                GraphRequest graphRequest=GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        displayIDinfo(object,userId);
//                    }
//
//                });
//                Bundle parameter=new Bundle();
//                parameter.putString("fields","first_name,last_name,email,id");
//                graphRequest.setParameters(parameter);
//                graphRequest.executeAsync();
//            }
//
//            @Override
//            public void onCancel() {
//                Toast.makeText(Login.this,"Hủy!",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Toast.makeText(Login.this,"Bạn chưa cài đặt ứng dụng facebook",Toast.LENGTH_SHORT).show();
//            }
//        });
//        loginButton.setReadPermissions("email","public_profile");
//
//
//        if (AccessToken.getCurrentAccessToken() != null)
//        {
//            SharedPreferences prefs = getSharedPreferences(LOGIN_FACEBOOK, MODE_PRIVATE);
//            edtUserName.setText(prefs.getString("Taikhoan",""));
//            edtPassWord.setText(prefs.getString("Matkhau",""));
//        }else {
//            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//            edtUserName.setText(prefs.getString("Taikhoan",""));
//            edtPassWord.setText(prefs.getString("Matkhau",""));
//        }
//        //lấy key hash
////        try {
////            PackageInfo info = getPackageManager().getPackageInfo(
////                    "com.example.administrator.demo_app_mua_ban_trao_doi_sach",
////                    PackageManager.GET_SIGNATURES);
////            for (Signature signature : info.signatures) {
////                MessageDigest md = MessageDigest.getInstance("SHA");
////                md.update(signature.toByteArray());
////                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
////            }
////        } catch (PackageManager.NameNotFoundException e) {
////
////        } catch (NoSuchAlgorithmException e) {
////
////        }


    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_dang_nhap);
        socket.connect();
        Singleton.Instance().setmSocket(socket);
        initFragment();
        btnRegister=findViewById(R.id.btnDangKiFrag);
        btnLogin=findViewById(R.id.btnDangNhapFrag);
        addEvent();
    }

    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin.startAnimation(ButtonAnimation.getInstance(Login.this).startAnimation());
                btnLogin.setBackgroundResource(R.drawable.boder_text_canhan);
                btnRegister.setBackgroundResource(R.drawable.backround_text);
                swithFragmentLeftRight(loginFragment);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegister.startAnimation(ButtonAnimation.getInstance(Login.this).startAnimation());
                btnRegister.setBackgroundResource(R.drawable.boder_text_canhan);
                btnLogin.setBackgroundResource(R.drawable.backround_text);
                swithFragmentRightleft(mainRegisterFragment);
            }
        });
    }

    public Fragment getFragmentDangKi(){
        return mainRegisterFragment;
    }
    public void getFragmentDangNhap(){
        btnLogin.setBackgroundResource(R.drawable.boder_text_canhan);
        btnRegister.setBackgroundResource(R.drawable.backround_text);
        swithFragmentRightleft(loginFragment);
    }

    public Fragment getFramentDangNhap(){
        return loginFragment;
    }



    private void initFragment() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        loginFragment =new LoginFragment();
        mainRegisterFragment = MainRegisterFragment.getInstance(getSupportFragmentManager());
        fragmentTransaction.add(R.id.dangNhapDangKi, loginFragment);
        fragmentTransaction.add(R.id.dangNhapDangKi, mainRegisterFragment);
        fragmentTransaction.commit();
        swithFragmentRightleft(loginFragment);
    }

    public void swithFragmentRightleft(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left1,R.anim.slide_out_right);
        transaction.hide(loginFragment);
        transaction.hide(mainRegisterFragment);
        transaction.show(fragment);
        transaction.commit();
    }

    public void swithFragmentLeftRight(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_right);
        transaction.hide(loginFragment);
        transaction.hide(mainRegisterFragment);
        transaction.show(fragment);
        transaction.commit();
    }



//    public void displayIDinfo(JSONObject object,String idToken) {
//        String first_name,last_name, email,id;
//        String sdt = "0971129897";
//        try {
//            first_name=object.getString("first_name");
//            last_name=object.getString("last_name");
//            email=object.getString("email");
//            id=object.getString("id");
////            String api= LoginConfig.singup(first_name+last_name+id,idToken,email,first_name,last_name,sdt,id);
////            loginAPI(api);
//            editor = getSharedPreferences(LOGIN_FACEBOOK, MODE_PRIVATE).edit();
//            editor.putString("Taikhoan",first_name+last_name+id);
//            editor.putString("Matkhau",idToken);
//            editor.apply();
//            edtUserName.setText(first_name+last_name+id);
//            edtPassWord.setText(idToken);
//
//
//            editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//            editor.putString("userId",idToken);
//            editor.putString("email",email);
//            editor.apply();
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

}
