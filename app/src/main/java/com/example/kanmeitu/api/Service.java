package com.example.kanmeitu.api;

import com.example.kanmeitu.domain.Image;
import com.example.kanmeitu.domain.response.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 接口地址
 *
 * 定义项目中用到的接口
 *
 * Created by smile on 02/03/2019.
 */
public interface Service {

    /**
     * 获取图片列表
     * @return
     */
    @GET("v1/images")
    Observable<ListResponse<Image>> images();

}
