package com.example.sunny.androidandjs;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements YctJsBridge,View.OnClickListener {

    private WebView layout_main_wv;
    private TextView layout_main_tv;
    private EditText layout_main_et;
    private Button layout_main_btn_send;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        initWidgets(savedInstanceState);
    }

    private void initWidgets(Bundle savedInstanceState) {
        layout_main_wv = findViewById(R.id.layout_main_wv);
        layout_main_tv = findViewById(R.id.layout_main_tv);

        layout_main_et = findViewById(R.id.layout_main_et);
        layout_main_btn_send = findViewById(R.id.layout_main_btn_send);
        layout_main_btn_send.setOnClickListener(this);

        layout_main_wv.getSettings().setJavaScriptEnabled(true);
        layout_main_wv.addJavascriptInterface(new YctJsInterface(this),"yctjsinterface");
        layout_main_wv.loadUrl("file:///android_asset/testjavascript.html");

        //只在api19以上才存在，这个是设置利用chrome可以调试，在chrome中要输入chrome://inspect/#devices
        layout_main_wv.setWebContentsDebuggingEnabled(true);
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

    @Override
    public void onClick(View view) {
        if (view == layout_main_btn_send){
            String str = layout_main_et.getText().toString();
            String forJSString = "javascript:if(window.fromAndroid){window.fromAndroid('"+str+"')}";
            layout_main_wv.loadUrl(forJSString);
        }
    }
}
