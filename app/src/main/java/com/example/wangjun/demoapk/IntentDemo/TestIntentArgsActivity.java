package com.example.wangjun.demoapk.IntentDemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.wangjun.demoapk.R;

public class TestIntentArgsActivity extends Activity {
    private static String sLogcatTAG = "IntentDemoActivity";

    private TextView m_Textview1;
    private Button m_IntentDataBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.intent_demo_testintentargs);

        // 接收 intent 并获得其中的参数
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");

        Log.d("IntentArgsActivity", data);
        m_Textview1 = (TextView) findViewById(R.id.testview);
        m_Textview1.setText(data);

        m_IntentDataBtn = (Button) findViewById(R.id.m_IntentDataBtn);
        m_IntentDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent();
                intent.putExtra("data_return", "[Button]IntentArgs =args=> IntentDemo");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "[BackKey]IntentArgs =args=> IntentDemo");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
