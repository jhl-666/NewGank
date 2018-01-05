package com.ljhdemo.newgank.http;

import com.ljhdemo.newgank.utils.DateUtils;
import com.ljhdemo.newgank.utils.LogUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * OkHttp拦截器（判断是否登录过期，并重新登录）
 */
public class TokenInterceptor implements Interceptor {

    private Charset UTF8 = Charset.forName("UTF-8");

    public TokenInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .build();

        Response response = chain.proceed(request);
        isTokenExpired(response, request, chain);//拦截服务器返回数据
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param
     * @param request
     * @param chain
     * @return
     */
    private boolean isTokenExpired(Response response, final Request request, Chain chain) throws IOException {
        String result = null;
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        if (!HttpEngine.hasBody(response)) {
            //END HTTP
        } else if (bodyEncoded(response.headers())) {
            //HTTP (encoded body omitted)
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    //Couldn't decode the response body; charset is likely malformed.无法解码响应体；字符集可能是畸形的。
                }
            }

            if (contentLength != 0) {
                result = buffer.clone().readString(charset);
               /* LogUtil.i("-----TokenInterceptor----- :\nrequest url:" + request.url() + "\ntime:" + System.currentTimeMillis() + "\n");
                LogUtil.json(result);*/
                LogUtil.i("\nrequest url:" + request.url()
                        + "\ntime:" + DateUtils.getDate(System.currentTimeMillis())
                        + "\nconnection:" + chain.connection()
                        + "\nheaders:" + request.headers()
                        + "\n" + result);

            }
        }

       /* ResponseResult responseResult = new Gson().fromJson(result, ResponseResult.class);

        if ("-98".equals(responseResult.getResult().getCode())) {
            return true;
        }*/
        return false;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}