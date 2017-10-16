package com.example.wangjun.demoapk.IntentDemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;


public class IntentDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "IntentDemoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentdemo);

        Button testImplicitIntentBtn;
        testImplicitIntentBtn = (Button) findViewById(R.id.testImplicitIntentBtn);
        testImplicitIntentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);
            }
        });

        Button testIntetBrowserBtn;
        testIntetBrowserBtn = (Button) findViewById(R.id.testIntetBrowserBtn);
        testIntetBrowserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        Button testIntentCallBtn;
        testIntentCallBtn = (Button) findViewById(R.id.testIntentCallBtn);
        testIntentCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        Button testIntentArgsBtn;
        testIntentArgsBtn = (Button) findViewById(R.id.testIntentArgsBtn);
        testIntentArgsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                String data = "IntentDemo =args=> IntArgsActivity";
                Intent intent = new Intent(IntentDemoActivity.this, CIntentArgsActivity.class);
                intent.putExtra("extra_data", data);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnedData);
                    Toast.makeText(IntentDemoActivity.this, returnedData,
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}