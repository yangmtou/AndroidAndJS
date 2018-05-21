package com.example.sunny.androidandjs;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements YctJsBridge {

    private WebView layout_main_wv;
    private TextView layout_main_tv;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets(savedInstanceState);
    }

    private void initWidgets(Bundle savedInstanceState) {
        layout_main_wv = findViewById(R.id.layout_main_wv);
        layout_main_tv = findViewById(R.id.layout_main_tv);

        layout_main_wv.getSettings().setJavaScriptEnabled(true);
        layout_main_wv.addJavascriptInterface(new YctJsInterface(this),"yctjsinterface");
        layout_main_wv.loadUrl("file:///android_asset/testjavascript.html");

        handler = new Handler();
    }

    @Override
    public void setTextViewStr(final String str) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                layout_main_tv.setText(str);
            }
        });
    }
}
