package com.example.administrator.demo_app_mua_ban_trao_doi_sach.model.retrofit;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getClient(String api){
        if (retrofit==null){
            //retrofit=new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).build();
            retrofit=restClient(api);
        }
        return retrofit;
    }
    public static Retrofit restClient( String api) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new Retrofit.Builder()
                .baseUrl(api)
                .client(configClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    private static OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        Interceptor headerIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
//                final String basicAuth = "Basic " + Base64.encodeToString((Utils.getToken(context) + ":password").getBytes(), Base64.NO_WRAP);
//                IotPreferences.getInstance(context).setAuthorization(basicAuth);
//                if (Utils.checkLoggedIn(context)) {
//                    builder.addHeader("Authorization", basicAuth);
//                }

                Request request = builder.build();
                return chain.proceed(request);
            }
        };


        // Log
//        if (BuildConfig.DEBUG && false) {
//            okHttpClient.addInterceptor(new LoggingInterceptor.Builder()
//                    .loggable(BuildConfig.DEBUG)
//                    .setLevel(Level.BODY)
//                    .log(Platform.INFO)
//                    .request("Request")
//                    .response("Response")
//                    .addHeader("version", BuildConfig.VERSION_NAME)
//                    .build());
//        }
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                // try the request
                Response response = chain.proceed(request);

                int tryCount = 0;
                while (!response.isSuccessful() && tryCount < 3) {

                    Log.d("intercept", "Request is not successful - " + tryCount);

                    tryCount++;

                    // retry the request
                    response = chain.proceed(request);
                }

                // otherwise just pass the original response on
                return response;
            }
        });

        okHttpClient.connectTimeout(10, TimeUnit.SECONDS);
        okHttpClient.readTimeout(10, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(10, TimeUnit.SECONDS);
        okHttpClient.dispatcher(dispatcher);
        okHttpClient.retryOnConnectionFailure(true);


        return okHttpClient.build();
    }
}
