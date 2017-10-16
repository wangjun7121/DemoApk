package com.example.wangjun.demoapk.WebDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.wangjun.demoapk.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


//HttpClient 是一个接口，因此无法创建它的实例，通常情况下都会创建一个 DefaultHttpClient 的实例，如下所示：
//        HttpClient httpClient = new DefaultHttpClient();
//
//发起一条 GET 请求，
//        可以创建一个 HttpGet 对象， 并传入目标的网络地址，然后调用 HttpClient 的 execute()方法即可：
//            HttpGet httpGet = new HttpGet("http://www.baidu.com");
//            httpClient.execute(httpGet);
//
//发起一条 POST 请求会比 GET 稍微复杂一点， 我们需要创建一个 HttpPost 对象，并传入目标的网络地址，如下所示：
//            HttpPost httpPost = new HttpPost("http://www.baidu.com");
//
//        然后通过一个 NameValuePair 集合来存放待提交的参数， 并将这个参数集合传入到一个
//        UrlEncodedFormEntity 中，然后调用 HttpPost 的 setEntity()方法将构建好的 UrlEncodedFormEntity
//        传入，如下所示：
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("username", "admin"));
//            params.add(new BasicNameValuePair("password", "123456"));
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
//            httpPost.setEntity(entity);
//        接下来的操作就和 HttpGet 一样了，调用 HttpClient 的 execute()方法，并将 HttpPost 对象传入即可：
//            httpClient.execute(httpPost);
//
//执行 execute()方法之后会返回一个 HttpResponse 对象， 服务器所返回的所有信息就会包
//        含在这里面。 通常情况下我们都会先取出服务器返回的状态码， 如果等于 200 就说明请求和
//        响应都成功了，如下所示：
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                // 请求和响应都成功了
//            }
//
//        接下来在这个 if 判断的内部取出服务返回的具体内容，可以调用 getEntity()方法获取到
//        一个 HttpEntity 实例， 然后再用 EntityUtils.toString()这个静态方法将 HttpEntity 转换成字符串
//        即可，如下所示：
//            HttpEntity entity = httpResponse.getEntity();
//            String response = EntityUtils.toString(entity);
//        注意如果服务器返回的数据是带有中文的， 直接调用 EntityUtils.toString()方法进行转换
//        会有乱码的情况出现，这个时候只需要在转换的时候将字符集指定成 utf-8 就可以了，如下
//        所示：
//            String response = EntityUtils.toString(entity, "utf-8");


//使用 HttpClient:
//        与 HttpURLConnection 效果类似
//        使用方法：
//        1. 创建 HttpClient 对象
//        2. 根据 GET/POST 设置访问
//            GET：HttpGet = new HttpGet("www.baidu.com")
//            SET: HttpPost = new HttpPost("www.baidu.com")
//            通过 NameValuePair 保存要发送的数据
//        3. 执行访问 HttpClient.execute()
//
//
//在API 23中，Google已经移除了移除了Apache HttpClient相关的类
//        谷歌推荐使用HttpUrlConnection，如果要继续使用需要Apache HttpClient，有两种方法。
//
//        1. Eclipse下libs里添加org.apache.http.legacy.jar，
//        2. Android studio里在相应的module下的build.gradle中加入即可。
//            android {
//                useLibrary 'org.apache.http.legacy'
//            }

public class CHttpClientActivity extends Activity implements OnClickListener {
    public static final int SHOW_RESPONSE = 0;
    private Button sendRequest;
    private TextView responseText;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    // 在这里进行UI操作，将结果显示到界面上
                    responseText.setText(response);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webdemo_chttpclient);
        sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithHttpClient();
        }
    }

    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://www.baidu.com");
                    HttpResponse httpResponse = httpClient.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        // 请求和响应都成功了
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        // 将服务器返回的结果存放到Message中
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}