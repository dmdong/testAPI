package com.manhdong.testapi.network;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.manhdong.testapi.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Saphiro on 7/27/2016.
 */
public class APIServiceBuilder {
    //Cấu hình pthuc kết nối server, parse data
    public static DemoAPIService buildAPIServices(Context context, String domain) {

        //View log retrofit
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(log).build();

        //cấu hình gSon;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(domain) //Các domain nên kết thúc = dấu /
                //Data converter =? gson,  sủ dụng expose và version để quản lý quá trình serial
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .setVersion((double) BuildConfig.VERSION_CODE).create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient) //okhttp
                .build();

        return retrofit.create(DemoAPIService.class);
    }
}
