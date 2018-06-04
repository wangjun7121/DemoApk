package com.example.wangjun.demoapk.WebDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class CSocketCSSwActivity extends AppCompatActivity
{

    private Button btn_server;
    private Button btn_client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.webdemo_csocketcssw);
        btn_server=(Button)findViewById ( R.id.btn_server );
        btn_client=(Button)findViewById ( R.id.btn_client );

        btn_server.setOnClickListener ( new View.OnClickListener ( )
        {
            @Override
            public void onClick(View v)
            {
                startActivity ( new Intent ( CSocketCSSwActivity.this,CSocketCS_ServerActivity.class ) );
            }
        } );
        btn_client.setOnClickListener ( new View.OnClickListener ( )
        {
            @Override
            public void onClick(View v)
            {
                startActivity ( new Intent ( CSocketCSSwActivity.this,CSocketCS_ClientActivity.class ) );
            }
        } );

    }
}