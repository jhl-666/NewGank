package com.ljhdemo.newgank.molule_main.http;

import com.ljhdemo.newgank.molule_main.bean.GankResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ljh on 2017/12/27.
 */

public interface GankApi {
    @GET("data/{type}/{count}/{pageNum}")
    Observable<GankResult> getGankResult(
            @Path("type") String type,
            @Path("count") Integer count,
            @Path("pageNum") Integer pageNum);
}
