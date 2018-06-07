package com.ljhdemo.newgank.molule_main.http;

import com.ljhdemo.newgank.common.http.ServiceGenerator;

/**
 * Created by ljh on 2018/6/4.
 */

public enum ApiFactory {
    INSTANCE;

    private static GankApi mGankApi;

    public static GankApi gankApi() {
        return mGankApi = ServiceGenerator.getApiService(GankApi.class);
    }
}
