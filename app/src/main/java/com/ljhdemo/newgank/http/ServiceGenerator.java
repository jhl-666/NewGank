package com.ljhdemo.newgank.http;

import com.ljhdemo.newgank.app.MyApplication;
import com.ljhdemo.newgank.constant.Constants;
import com.ljhdemo.newgank.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;

    public static APIService getApiService() {

        if (retrofit == null) {
            synchronized (ServiceGenerator.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(defaultOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit.create(APIService.class);
    }

    public static OkHttpClient defaultOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (ServiceGenerator.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder client = new OkHttpClient.Builder();
                    client.writeTimeout(30 * 1000, TimeUnit.MILLISECONDS);
                    client.readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
                    client.connectTimeout(15 * 1000, TimeUnit.MILLISECONDS);
                    //设置缓存路径
                    File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "okHttpCache");
                    //设置缓存 10M
                    Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
                    client.cache(cache);
                    //设置拦截器
                    client.addInterceptor(new TokenInterceptor());
                    client.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
                    client.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
                    okHttpClient = client.build();
                }
            }
        }

        return okHttpClient;
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            //方案一：有网和没有网都是先读缓存
//                Request request = chain.request();
//                Log.i(TAG, "request=" + request);
//                Response response = chain.proceed(request);
//                Log.i(TAG, "response=" + response);
//
//                String cacheControl = request.cacheControl().toString();
//                if (TextUtils.isEmpty(cacheControl)) {
//                    cacheControl = "public, max-age=60";
//                }
//                return response.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();

            //方案二：无网读缓存，有网根据过期时间重新请求
            boolean netWorkConection = NetworkUtils.hasNetWorkConection(MyApplication.getContext());
            Request request = chain.request();
            if (!netWorkConection) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (netWorkConection) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                response.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .header("Cache-Control", cacheControl)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    };
}