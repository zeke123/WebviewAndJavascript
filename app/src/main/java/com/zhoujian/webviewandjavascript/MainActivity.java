package com.zhoujian.webviewandjavascript;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private WebView webview;
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webview = (WebView) findViewById(R.id.webview);
        mTextView = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);

        // javascript可用
        webview.getSettings().setJavaScriptEnabled(true);
        // 从assets中加载html
        webview.loadUrl("file:///android_asset/demo.html");
        //设置回调接口
        webview.addJavascriptInterface(this, "demo");

        //webview调用js

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // 无参数调用
                webview.loadUrl("javascript:methodFromWebview()");
                // 有参数调用
                webview.loadUrl("javascript:methodFromWebviewWithParam(" + "'我来自Webview,传递过来的参数：id=342'" + ")");
            }
        });

    }

    //@android.webkit.JavascriptInterface https://github.com/zeke123/WebviewAndJavascript.git

    @JavascriptInterface
    public void methodFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String text = mTextView.getText() + "\njs调用了Webview函数";
                mTextView.setText(text);
            }
        });
    }

    //@android.webkit.JavascriptInterface

    @JavascriptInterface
    public void methodFromJsWithParam(final String mString) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String text = mTextView.getText() +  "\njs调用了Webview函数传递参数：" + mString;
                mTextView.setText(text);
            }
        });

    }
}