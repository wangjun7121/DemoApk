package com.example.wangjun.demoapk.WebDemo;

/**
 * Created by wangjun on 2018/6/1.
 */

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.Tools.LogUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

/**
 * Created by kys-29 on 2016/10/18.
 */

public class CSocketCS_ClientActivity extends AppCompatActivity
{
    private CSocketCS_Client client;
    private TextView txt;
    private TextView hostip;
    private EditText edit;
    private Button btn;
    private EditText edit_server;
    private Button server_OK;
    private EditText edit_ip;
    private LinearLayout mLinearLayout;
    private LinearLayout mLinearLayout_client;

    private int pite=6666;
    private String ip="";


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
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.webdemo_csocketcsactivity);
        txt=(TextView)findViewById ( R.id.textView );
        edit=(EditText)findViewById ( R.id.edit );
        btn=(Button)findViewById ( R.id.btn );


        hostip=(TextView)findViewById ( R.id.hostip );

        edit_server=(EditText)findViewById ( R.id.editText_server );
        edit_ip=(EditText)findViewById ( R.id.client_ip );
        server_OK=(Button)findViewById ( R.id.server_OK );
        mLinearLayout=(LinearLayout)findViewById ( R.id.lin_1 ) ;
        mLinearLayout_client=(LinearLayout)findViewById ( R.id.lin_ip ) ;

        client=new CSocketCS_Client ();

        hostip.setText(getHostIP());

        server_OK.setOnClickListener ( new View.OnClickListener ( )
        {
            @Override
            public void onClick(View v)
            {
                mLinearLayout.setVisibility ( View.GONE );
                mLinearLayout_client.setVisibility ( View.GONE );
                try {
                    pite= Integer.parseInt(edit_server.getText ().toString ());
                    ip=edit_ip.getText ().toString ();

                    //服务端的IP地址和端口号
                    client.clintValue (CSocketCS_ClientActivity.this,ip ,pite);

                    //开启客户端接收消息线程
                    client.openClientThread ();


                }catch (Exception e){
                    Toast.makeText ( CSocketCS_ClientActivity.this,"请检查ip及地址", Toast.LENGTH_SHORT ).show ();
                    mLinearLayout.setVisibility ( View.VISIBLE );
                    mLinearLayout_client.setVisibility ( View.VISIBLE );
                    e.printStackTrace ();
                }

            }
        } );


        /**
         * 发送消息
         * */
        btn.setOnClickListener ( new View.OnClickListener ( )
        {
            @Override
            public void onClick(View v)
            {
                client.sendMsg ( edit.getText ().toString () );

            }
        } );
        /**
         *  接受消息
         *
         **/

        CSocketCS_Client.mHandler=new Handler (  ){
            @Override
            public void handleMessage(Message msg)
            {
                txt.setText ( msg.obj.toString ());
                Log.i ( "msghh",msg.obj.toString ());
            }
        };


    }

}
