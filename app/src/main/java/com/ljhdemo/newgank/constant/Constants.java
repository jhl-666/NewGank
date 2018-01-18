package com.ljhdemo.newgank.constant;

import android.os.Environment;

/**
 * Created by ljh on 2017/12/28.
 */

public class Constants {
    //接口请求的BaseUrl
    public static final String BASE_URL = "http://gank.io/api/";


    //保存图片的地址
    public static final String BASE_IMAGE_PATH = Environment.getExternalStorageDirectory() + "/NewGank";

    //标签
    public static final String FlagWelFare = "福利";
    public static final String FlagAndroid = "Android";
    public static final String FlagIOS = "iOS";
    public static final String FlagVideo = "休息视频";
    public static final String FlagExpand = "拓展资源";
    public static final String FlagJS = "前端";
    public static final String FlagAll = "all";

}
