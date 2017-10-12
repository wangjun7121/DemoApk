package com.example.wangjun.demoapk.WebDemo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




//使用HttpURLConnection
//    1. 设置访问地址 URL = URL("www.baidu.com")
//    2. 初始化 HttpURLConnection 对象
//        .openConnection: 打开连接
//        .setRequestMethod: 设置读/写，即GET/POST
//        .setConnectTimout: 设置连接超时时间
//        .setRReadTimeout: 设置读取超时时间
//        .getInputStream: 获得服务器返回的输入流
//        .disconnect: 关闭链接


public class CHttpURLConnectionTestActivity extends Activity implements View.OnClickListener {

    private static final int MSG_CACHE_PIC = 1;
    private static final int MSG_NEW_PIC = 2;
    private static final int ERROR = 3;
    private static final int EXCEPTION = 4;
    public static final int SHOW_RESPONSE = 5;

    private TextView responseText;
    private EditText pathCode;
    private Button getCode;
    private EditText pathPic;
    private Button getPic;
    private ImageView mIvShow;


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    // 在这里进行UI操作，将结果显示到界面上
                    responseText.setText(response);
                    break;
                case MSG_CACHE_PIC:
                    //3.处理消息 运行在主线程
                    Bitmap bitmap = (Bitmap) msg.obj;
                    mIvShow.setImageBitmap(bitmap);
//                    System.out.println("(不用下载)缓存图片");
                    break;
                case MSG_NEW_PIC:
                    Bitmap bitmap2 = (Bitmap) msg.obj;
                    mIvShow.setImageBitmap(bitmap2);
//                    System.out.println("新下载(还没有缓存)下载的图片");
                    break;
                case ERROR:
                    Toast.makeText(CHttpURLConnectionTestActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    break;
                case EXCEPTION:
                    Toast.makeText(CHttpURLConnectionTestActivity.this, "发生异常，请求失败", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webdemo_chttpurlconntest);

        getCode = (Button) findViewById(R.id.getCode);
        pathCode = (EditText) findViewById(R.id.pathCode);
        getPic = (Button) findViewById(R.id.getPic);
        pathPic = (EditText) findViewById(R.id.pathPic);
        responseText = (TextView) findViewById(R.id.response_text);
        mIvShow = (ImageView) findViewById(R.id.mIvShow);

        getCode.setOnClickListener(this);
        getPic.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.getCode:
                sendRequestWithHttpURLConnection();
                break;
            case R.id.getPic:
                final String path = pathPic.getText().toString().trim();// http://www.baidu.com/aa.png

                Log.v("WJWind",path);
                if (TextUtils.isEmpty(path)) {
                    Toast.makeText(this, "图片路径不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(){
                    public void run() {
                        getImage(path);
                    };
                }.start();

                break;
            default:
                break;
        }


    }

    /**
     * 获取图片
     * @param path
     */
    private void getImage(String path) {
        File file = new File(getCacheDir(), Base64.encodeToString(path.getBytes(), Base64.DEFAULT));

        Log.v("WJWind","1");

        if (file.exists() && file.length() > 0) {
//            System.out.println("图片存在，拿缓存");
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            //更新ui 不能写在子线程
            //iv.setImageBitmap(bitmap);
            Message msg = new Message();//声明消息
            msg.what = MSG_CACHE_PIC;
            msg.obj = bitmap;//设置数据
            handler.sendMessage(msg);//让handler帮我们发送数据
        } else {
//            System.out.println("图片不存在，获取数据生成缓存");
            // 通过http请求把图片获取下来。
            try {
                // 1.声明访问的路径， url 网络资源 http ftp rtsp
                URL url = new URL(path);
                // 2.通过路径得到一个连接 http的连接
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                // 3.判断服务器给我们返回的状态信息。
                // 200 成功 302 从定向 404资源没找到 5xx 服务器内部错误
                int code = conn.getResponseCode();

                Log.v("WJWind",String.valueOf(code));
                if (code == 200) {
                    InputStream is = conn.getInputStream();// png的图片
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                    is.close();
                    fos.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    //更新ui ，不能写在子线程
                    Message msg = new Message();
                    msg.obj = bitmap;
                    msg.what = MSG_NEW_PIC;
                    handler.sendMessage(msg);
                    //iv.setImageBitmap(bitmap);
                } else {
                    // 请求失败
                    //土司更新ui，不能写在子线程
                    //Toast.makeText(this, "请求失败", 0).show();
                    Message msg = new Message();
                    msg.what = ERROR;
                    handler.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //土司不能写在子线程
                //Toast.makeText(this, "发生异常，请求失败", 0).show();
                Message msg = new Message();
                msg.what = EXCEPTION;
                handler.sendMessage(msg);
            }
        }
    }



    private void sendRequestWithHttpURLConnection() {
// 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String path = pathCode.getText().toString().trim();

                HttpURLConnection connection = null;
                try {
                    URL url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    int code = connection.getResponseCode();

                    do{

                        Log.v("WJWind",String.valueOf(code));
                        if(code == 302) // 地址被重定向了
                        {
                            String location = connection.getHeaderField("Location");
                            Log.v("WJWind","location "+location+"");
                            url  = new URL(location);
                            connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(3000);
                            code = connection.getResponseCode();
                            Log.v("WJWind",String.valueOf(code));
                        }

                    }while (code != 200);



                    InputStream in = connection.getInputStream();

                    // 下面对获取到的输入流进行读取
                    BufferedReader reader = new BufferedReader(new   InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                        Log.v("WJWind",String.valueOf(line));
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    // 将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}



//HTTP协议状态码表示的意思主要分为五类 ,大体是 :
//        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        1×× 　　保留
//        2×× 　　表示请求成功地接收
//        3×× 　　为完成请求客户需进一步细化请求
//        4×× 　　客户错误
//        5×× 　　服务器错误
//
//        ---------------------------------------------------------------
//
//        100 Continue 初始的请求已经接受，客户应当继续发送请求的其余部分。（HTTP 1.1新）
//
//        101 Switching Protocols 服务器将遵从客户的请求转换到另外一种协议（HTTP 1.1新）
//
//
//
//        Successful
//        =================================
//
//        200 OK 一切正常，对GET和POST请求的应答文档跟在后面。
//
//        201 Created 服务器已经创建了文档，Location头给出了它的URL。
//
//        202 Accepted 已经接受请求，但处理尚未完成。
//
//        203 Non-Authoritative Information 文档已经正常地返回，但一些应答头可能不正确，因为使用的是文档的拷贝（HTTP 1.1新）。
//
//        204 No Content 没有新文档，浏览器应该继续显示原来的文档。如果用户定期地刷新页面，而Servlet可以确定用户文档足够新，这个状态代码是很有用的。
//
//        205 Reset Content 没有新的内容，但浏览器应该重置它所显示的内容。用来强制浏览器清除表单输入内容（HTTP 1.1新）。
//
//        206 Partial Content 客户发送了一个带有Range头的GET请求，服务器完成了它（HTTP 1.1新）。
//
//
//
//        Redirection
//        ==================================
//
//        300 Multiple Choices 客户请求的文档可以在多个位置找到，这些位置已经在返回的文档内列出。如果服务器要提出优先选择，则应该在Location应答头指明。
//
//        301 Moved Permanently 客户请求的文档在其他地方，新的URL在Location头中给出，浏览器应该自动地访问新的URL。
//
//        302 Found 类似于301，但新的URL应该被视为临时性的替代，而不是永久性的。注意，在HTTP1.0中对应的状态信息是“Moved Temporatily”。
//        出现该状态代码时，浏览器能够自动访问新的URL，因此它是一个很有用的状态代码。
//
//        注意这个状态代码有时候可以和301替换使用。例如，如果浏览器错误地请求http://host/~user（缺少了后面的斜杠），有的服务器返回301，有的则返回302。
//
//        严格地说，我们只能假定只有当原来的请求是GET时浏览器才会自动重定向。请参见307。
//
//        303 See Other 类似于301/302，不同之处在于，如果原来的请求是POST，Location头指定的重定向目标文档应该通过GET提取（HTTP 1.1新）。
//
//        304 Not Modified 客户端有缓冲的文档并发出了一个条件性的请求（一般是提供If-Modified-Since头表示客户只想比指定日期更新的文档）。服务器告诉客户，原来缓冲的文档还可以继续使用。
//
//        305 Use Proxy 客户请求的文档应该通过Location头所指明的代理服务器提取（HTTP 1.1新）。
//
//        307 Temporary Redirect 和302（Found）相同。许多浏览器会错误地响应302应答进行重定向，即使原来的请求是POST，即使它实际上只能在POST请求的应答是303时 才能重定向。由于这个原因，HTTP 1.1新增了307，以便更加清除地区分几个状态代码：当出现303应答时，浏览器可以跟随重定向的GET和POST请求；如果是307应答，则浏览器只 能跟随对GET请求的重定向。（HTTP 1.1新）
//
//
//
//        Client Error
//        =====================
//
//        400 Bad Request 请求出现语法错误。
//
//        401 Unauthorized 客户试图未经授权访问受密码保护的页面。应答中会包含一个WWW-Authenticate头，浏览器据此显示用户名字/密码对话框，然后在填写合适的Authorization头后再次发出请求。
//
//        403 Forbidden 资源不可用。服务器理解客户的请求，但拒绝处理它。通常由于服务器上文件或目录的权限设置导致。
//
//        404 Not Found 无法找到指定位置的资源。这也是一个常用的应答。
//
//        405 Method Not Allowed 请求方法（GET、POST、HEAD、Delete、PUT、TRACE等）对指定的资源不适用。（HTTP 1.1新）
//
//        406 Not Acceptable 指定的资源已经找到，但它的MIME类型和客户在Accpet头中所指定的不兼容。（HTTP 1.1新）
//
//        407 Proxy Authentication Required 类似于401，表示客户必须先经过代理服务器的授权。（HTTP 1.1新）
//
//        408 Request Timeout 在服务器许可的等待时间内，客户一直没有发出任何请求。客户可以在以后重复同一请求。（HTTP 1.1新）
//
//        409 Conflict 通常和PUT请求有关。由于请求和资源的当前状态相冲突，因此请求不能成功。（HTTP 1.1新）
//
//        410 Gone 所请求的文档已经不再可用，而且服务器不知道应该重定向到哪一个地址。它和404的不同在于，返回407表示文档永久地离开了指定的位置，而404表示由于未知的原因文档不可用。（HTTP 1.1新）
//
//        411 Length Required 服务器不能处理请求，除非客户发送一个Content-Length头。（HTTP 1.1新）
//
//        412 Precondition Failed 请求头中指定的一些前提条件失败。（HTTP 1.1新）
//
//        413 Request Entity Too Large 目标文档的大小超过服务器当前愿意处理的大小。如果服务器认为自己能够稍后再处理该请求，则应该提供一个Retry-After头。（HTTP 1.1新）
//
//        414 Request URI Too Long URI太长。（HTTP 1.1新）
//
//        416 Requested Range Not Satisfiable 服务器不能满足客户在请求中指定的Range头。（HTTP 1.1新）
//
//
//
//        Server Error
//        ===================================
//
//        500 Internal Server Error 服务器遇到了意料不到的情况，不能完成客户的请求。
//
//        501 Not Implemented 服务器不支持实现请求所需要的功能。例如，客户发出了一个服务器不支持的PUT请求。
//
//        502 Bad Gateway 服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答。
//
//        503 Service Unavailable 服务器由于维护或者负载过重未能应答。例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个Retry-After头。
//
//        504 Gateway Timeout 由作为代理或网关的服务器使用，表示不能及时地从远程服务器获得应答。（HTTP 1.1新）
//
//        505 HTTP Version Not Supported 服务器不支持请求中所指明的HTTP版本。（HTTP 1.1新）



// Get请求实现：
//                String baseUrl = "https://xxx.com/getUsers?";
//                StringBuilder tempParams = new StringBuilder();
//                int pos = 0;
//                for (String key : paramsMap.keySet()) {
//                    if (pos > 0) {
//                        tempParams.append("&");
//                    }
//                    tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key),"utf-8")));
//                    pos++;
//                }
//                String requestUrl = baseUrl + tempParams.toString();
//                // 新建一个URL对象
//                URL url = new URL(requestUrl);
//                // 打开一个HttpURLConnection连接
//                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//                // 设置连接主机超时时间
//                urlConn.setConnectTimeout(5 * 1000);
//                //设置从主机读取数据超时
//                urlConn.setReadTimeout(5 * 1000);
//                // 设置是否使用缓存  默认是true
//                urlConn.setUseCaches(true);
//                // 设置为Post请求
//                urlConn.setRequestMethod("GET");
//                //urlConn设置请求头信息
//                //设置请求中的媒体类型信息。
//                urlConn.setRequestProperty("Content-Type", "application/json");
//                //设置客户端与服务连接类型
//                urlConn.addRequestProperty("Connection", "Keep-Alive");
//                // 开始连接
//                urlConn.connect();
//                // 判断请求是否成功
//                if (urlConn.getResponseCode() == 200) {
//                    // 获取返回的数据
//                    String result = streamToString(urlConn.getInputStream());
//                    Log.e(TAG, "Get方式请求成功，result--->" + result);
//                } else {
//                    Log.e(TAG, "Get方式请求失败");
//                }
//                // 关闭连接
//                urlConn.disconnect();


//
//POST请求实现：
//private void requestPost(HashMap<String, String> paramsMap)
//{
//        try {
//            String baseUrl = "https://xxx.com/getUsers";
//            //合成参数
//            StringBuilder tempParams = new StringBuilder();
//            int pos = 0;
//            for (String key : paramsMap.keySet()) {
//                if (pos > 0) {
//                    tempParams.append("&");
//                }
//                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key),"utf-8")));
//                pos++;
//            }
//            String params =tempParams.toString();
//            // 请求的参数转换为byte数组
//            byte[] postData = params.getBytes();
//            // 新建一个URL对象
//            URL url = new URL(baseUrl);
//            // 打开一个HttpURLConnection连接
//            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//            // 设置连接超时时间
//            urlConn.setConnectTimeout(5 * 1000);
//            //设置从主机读取数据超时
//            urlConn.setReadTimeout(5 * 1000);
//            // Post请求必须设置允许输出 默认false
//            urlConn.setDoOutput(true);
//            //设置请求允许输入 默认是true
//            urlConn.setDoInput(true);
//            // Post请求不能使用缓存
//            urlConn.setUseCaches(false);
//            // 设置为Post请求
//            urlConn.setRequestMethod("POST");
//            //设置本次连接是否自动处理重定向
//            urlConn.setInstanceFollowRedirects(true);
//            // 配置请求Content-Type
//            urlConn.setRequestProperty("Content-Type", "application/json");
//            // 开始连接
//            urlConn.connect();
//            // 发送请求参数
//            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
//            dos.write(postData);
//            dos.flush();
//            dos.close();
//            // 判断请求是否成功
//            if (urlConn.getResponseCode() == 200) {
//                // 获取返回的数据
//                String result = streamToString(urlConn.getInputStream());
//                Log.e(TAG, "Post方式请求成功，result--->" + result);
//            } else {
//                Log.e(TAG, "Post方式请求失败");
//            }
//            // 关闭连接
//            urlConn.disconnect();
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        }
//}


//处理网络流：将输入流转换成字符串
///**
// * 将输入流转换成字符串
// *
// * @param is 从网络获取的输入流
// * @return
// */
//public String streamToString(InputStream is)
//{
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = is.read(buffer)) != -1) {
//                baos.write(buffer, 0, len);
//            }
//            baos.close();
//            is.close();
//            byte[] byteArray = baos.toByteArray();
//            return new String(byteArray);
//        } catch (Exception e) {
//         Log.e(TAG, e.toString());
//        return null;
//        }
//}




//文件下载：
//private void downloadFile(String fileUrl){
//        try {
//            // 新建一个URL对象
//            URL url = new URL(fileUrl);
//            // 打开一个HttpURLConnection连接
//            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//            // 设置连接主机超时时间
//            urlConn.setConnectTimeout(5 * 1000);
//            //设置从主机读取数据超时
//            urlConn.setReadTimeout(5 * 1000);
//            // 设置是否使用缓存  默认是true
//            urlConn.setUseCaches(true);
//            // 设置为Post请求
//            urlConn.setRequestMethod("GET");
//            //urlConn设置请求头信息
//            //设置请求中的媒体类型信息。
//            urlConn.setRequestProperty("Content-Type", "application/json");
//            //设置客户端与服务连接类型
//            urlConn.addRequestProperty("Connection", "Keep-Alive");
//            // 开始连接
//            urlConn.connect();
//            // 判断请求是否成功
//            if (urlConn.getResponseCode() == 200) {
//                String filePath="";
//                File  descFile = new File(filePath);
//                FileOutputStream fos = new FileOutputStream(descFile);;
//                byte[] buffer = new byte[1024];
//                int len;
//                InputStream inputStream = urlConn.getInputStream();
//                while ((len = inputStream.read(buffer)) != -1) {
//                    // 写到本地
//                    fos.write(buffer, 0, len);
//                }
//            } else {
//                Log.e(TAG, "文件下载失败");
//            }
//            // 关闭连接
//            urlConn.disconnect();
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        }
//}

//文件上传：
//private void upLoadFile(String filePath, HashMap<String, String> paramsMap)
//{
//        try {
//            String baseUrl = "https://xxx.com/uploadFile";
//            File file = new File(filePath);
//            //新建url对象
//            URL url = new URL(baseUrl);
//            //通过HttpURLConnection对象,向网络地址发送请求
//            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//            //设置该连接允许读取
//            urlConn.setDoOutput(true);
//            //设置该连接允许写入
//            urlConn.setDoInput(true);
//            //设置不能适用缓存
//            urlConn.setUseCaches(false);
//            //设置连接超时时间
//            urlConn.setConnectTimeout(5 * 1000);   //设置连接超时时间
//            //设置读取超时时间
//            urlConn.setReadTimeout(5 * 1000);   //读取超时
//            //设置连接方法post
//            urlConn.setRequestMethod("POST");
//            //设置维持长连接
//            urlConn.setRequestProperty("connection", "Keep-Alive");
//            //设置文件字符集
//            urlConn.setRequestProperty("Accept-Charset", "UTF-8");
//            //设置文件类型
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + "*****");
//            String name = file.getName();
//            DataOutputStream requestStream = new DataOutputStream(urlConn.getOutputStream());
//            requestStream.writeBytes("--" + "*****" + "\r\n");
//            //发送文件参数信息
//            StringBuilder tempParams = new StringBuilder();
//            tempParams.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + name + "\"; ");
//            int pos = 0;
//            int size=paramsMap.size();
//            for (String key : paramsMap.keySet()) {
//                tempParams.append( String.format("%s=\"%s\"", key, paramsMap.get(key), "utf-8"));
//                if (pos < size-1) {
//                    tempParams.append("; ");
//                }
//                pos++;
//            }
//            tempParams.append("\r\n");
//            tempParams.append("Content-Type: application/octet-stream\r\n");
//            tempParams.append("\r\n");
//            String params = tempParams.toString();
//            requestStream.writeBytes(params);
//            //发送文件数据
//            FileInputStream fileInput = new FileInputStream(file);
//            int bytesRead;
//            byte[] buffer = new byte[1024];
//            DataInputStream in = new DataInputStream(new FileInputStream(file));
//            while ((bytesRead = in.read(buffer)) != -1) {
//                requestStream.write(buffer, 0, bytesRead);
//            }
//            requestStream.writeBytes("\r\n");
//            requestStream.flush();
//            requestStream.writeBytes("--" + "*****" + "--" + "\r\n");
//            requestStream.flush();
//            fileInput.close();
//            int statusCode = urlConn.getResponseCode();
//            if (statusCode == 200) {
//            // 获取返回的数据
//                String result = streamToString(urlConn.getInputStream());
//                Log.e(TAG, "上传成功，result--->" + result);
//            } else {
//                Log.e(TAG, "上传失败");
//            }
//        } catch (IOException e) {
//        Log.e(TAG, e.toString());
//        }
//}

