package com.example.wangjun.demoapk.MenuDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class MenuDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "MenuDemoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menudemo);

        Button testMenuBtn;
        testMenuBtn = (Button) findViewById(R.id.testMenuBtn);
        testMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MenuDemoActivity.this, CMenuActivity.class);
                startActivity(intent);
            }
        });
    }

}
