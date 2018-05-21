package com.example.sunny.androidandjs;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by sunny on 2018/5/21.
 */

public class YctJsInterface {

    private static final String TAG="YctJsInterface";
    private YctJsBridge yctJsBridge;

    public YctJsInterface(YctJsBridge yctJsBridge){
        this.yctJsBridge = yctJsBridge;
    }

    @JavascriptInterface
    public void setValue(String value){
        yctJsBridge.setTextViewStr(value);
        Log.d(TAG,"setValue---"+value);
    }
}
