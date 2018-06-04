package com.example.wangjun.demoapk.WebDemo;

/**
 * Created by wangjun on 2018/6/1.
 */
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.Tools.LogUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class CSocketCS_ServerActivity extends AppCompatActivity
{
    private TextView txt;
    private TextView hostip;
    private EditText edit;
    private Button btn;
    private EditText edit_server;
    private EditText edit_ip;
    private Button server_OK;
    private LinearLayout mLinearLayout;
    private LinearLayout mLinearLayout_client;

    /**启动服务端端口
     * 服务端IP为手机IP
     * */
    private int pite;
    private CSocketCS_Server server;

    // 获取本机 IP 地址
    public  String getHostIP() {

        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            LogUtil.d("SocketException");
            e.printStackTrace();
        }
        return hostIp;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.webdemo_csocketcsactivity);
        txt = (TextView) findViewById ( R.id.textView );
        edit = (EditText) findViewById ( R.id.edit );
        btn = (Button) findViewById ( R.id.btn );
        edit_server=(EditText)findViewById ( R.id.editText_server );
        edit_ip=(EditText)findViewById ( R.id.client_ip );
        server_OK=(Button)findViewById ( R.id.server_OK );
        mLinearLayout=(LinearLayout)findViewById ( R.id.lin_1 ) ;
        mLinearLayout_client=(LinearLayout)findViewById ( R.id.lin_ip ) ;
        hostip=(TextView)findViewById ( R.id.hostip );
        mLinearLayout_client.setVisibility ( View.GONE );

        hostip.setText(getHostIP());

        server_OK.setOnClickListener ( new View.OnClickListener ( )
        {
            @Override
            public void onClick(View v)
            {
                mLinearLayout.setVisibility ( View.GONE );
                try {
                    pite= Integer.parseInt(edit_server.getText ().toString ());

                    server=new CSocketCS_Server ( pite );
                    /**socket服务端开始监听*/
                    server.beginListen ( );

                }catch (Exception e){
                    Toast.makeText ( CSocketCS_ServerActivity.this,"请输入数字", Toast.LENGTH_SHORT ).show ();
                    mLinearLayout.setVisibility ( View.VISIBLE );
                    e.printStackTrace ();
                }

            }
        } );


        btn.setOnClickListener ( new View.OnClickListener ( )
        {
            @Override
            public void onClick(View v)
            {
                /**socket发送数据*/
                server.sendMessage ( edit.getText ().toString () );
            }
        } );

        /**socket收到消息线程*/
        CSocketCS_Server.ServerHandler=new Handler (  ){
            @Override
            public void handleMessage(Message msg)
            {
                txt.setText ( msg.obj.toString ());
            }
        };

    }

}
