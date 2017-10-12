package com.example.wangjun.demoapk.WebDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class CWebViewActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webdemo_cwebview);

        webView = (WebView) findViewById(R.id.web_view);

//        调用 WebView 的 getSettings()方法可以去设置一些浏览器的属性
        webView.getSettings().setJavaScriptEnabled(true); // 让 WebView 支持 JavaScript 脚本

//      调用了 WebView 的 setWebViewClient()方法， 并传入了 WebViewClient 的匿名类作为参数，
//      然后重写了 shouldOverrideUrlLoading()方法。这就表明当需要从一个网页跳转到另一个网页时，
//      我们希望目标网页仍然在当前 WebView 中显示，而不是打开系统浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 根据传入的参数再去加载新的网页
                view.loadUrl(url);
                // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
                return true;
            }
        });

//      将网址传入，即可展示相应网页的内容
        webView.loadUrl("http://www.baidu.com");


    }
}
