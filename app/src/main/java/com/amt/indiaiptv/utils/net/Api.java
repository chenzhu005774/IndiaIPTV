package com.amt.indiaiptv.utils.net;

import com.amt.indiaiptv.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *API 请求的基本类
 **/
public class Api {
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 2000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 2000;

    public static  String  token = "";

    private static Api retrofitManager = null;
    public ApiService movieService;

    String HOST = "http://gank.io/";
    String API_SERVER_URL = HOST + "api/data/";
//    private final String basUrl=API_SERVER_URL;
   private final String basUrl = "http://192.168.2.207:9003/vee_api/";

    /**
     * 构造方法私有化
     */
    private Api() {
        StringBuilder baseUrl = new StringBuilder();
        baseUrl.append(basUrl);

        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.i("request OkHttpMessage:"+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //公共参数
        BasicParamsInterceptor basicParamsInterceptor =
                new BasicParamsInterceptor.Builder()
                        .addParam("reqTime",  "")
//                        .addParam("token", "123")
                        .addHeaderLine("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
                        .addHeaderParam("token",token) //添加头
                        .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(basicParamsInterceptor)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl.toString())
                .build();
        movieService = retrofit.create(ApiService.class);
    }

    /**
     * 提供外部调用的单例
     */
    public static ApiService getDefault() {
        retrofitManager = new Api();
        return retrofitManager.movieService;
    }

}