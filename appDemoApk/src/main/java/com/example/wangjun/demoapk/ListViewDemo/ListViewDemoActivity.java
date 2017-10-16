package com.example.wangjun.demoapk.ListViewDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.wangjun.demoapk.R;

public class ListViewDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ListViewDemo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewdemo);

        Button testArrayAdapterBtn;
        testArrayAdapterBtn = (Button) findViewById(R.id.mylistview_btn);
        testArrayAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ListViewDemoActivity.this, CArrayAdapter.class);
                startActivity(intent);
            }
        });

        Button testSimpleCursorAdapterBtn;
        testSimpleCursorAdapterBtn = (Button) findViewById(R.id.simpleCursorAdapter_btn);
        testSimpleCursorAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testSimpleCursorAdapterBtn");
                Intent intent = new Intent(ListViewDemoActivity.this, CSimpleCursorAdapter.class);
                startActivity(intent);
            }
        });

        Button testSimpleAdapterBtn;
        testSimpleAdapterBtn = (Button) findViewById(R.id.simpleAdapter_btn);
        testSimpleAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testSimpleAdapterBtn");
                Intent intent = new Intent(ListViewDemoActivity.this, CSimpleAdapter.class);
                startActivity(intent);
            }
        });

        Button testMyAdapterBtn;
        testMyAdapterBtn = (Button) findViewById(R.id.myAdapter_btn);
        testMyAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "myAdapter");
                Intent intent = new Intent(ListViewDemoActivity.this, CMyListView.class);
                startActivity(intent);
            }
        });

        Button testUIChatBtn;
        testUIChatBtn = (Button) findViewById(R.id.testUIChatBtn);
        testUIChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "myAdapter");
                Intent intent = new Intent(ListViewDemoActivity.this, CUIChatActivity.class);
                startActivity(intent);
            }
        });

    }




}
