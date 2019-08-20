package com.example.kanmeitu.api;

import com.example.kanmeitu.domain.Image;
import com.example.kanmeitu.domain.response.ListResponse;
import com.example.kanmeitu.util.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 接口工具类，单例
 *
 * Created by smile on 02/03/2019.
 */
public class Api {
    private static final String SONG_ID = "song_id";
    private static Api instance;
    private static Service service;

    Api() {
        //OkHttp有很多配置，详细的查看《详解OKHttp》课程
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //这里是创建Retrofit
        //同样他也有很多配置
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constants.ENDPOINT) //这里就是配置API地址
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Service.class);
    }

    /**
     * 返回当前对象的唯一实例
     *
     * 单例设计模式
     * @return
     */
    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    public Observable<ListResponse<Image>> images() {
        //调用service里面的方法
        //service是一个接口
        //调用接口之所以还能返回数据
        //是retrofit框架内容代理的该方法的调用
        return service.images();
    }
}