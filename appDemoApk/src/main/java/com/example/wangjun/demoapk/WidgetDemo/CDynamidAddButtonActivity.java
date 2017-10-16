package com.example.wangjun.demoapk.WidgetDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.R;

public class CDynamidAddButtonActivity extends AppCompatActivity {

    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_Context = this;
        final LinearLayout layout2 = new LinearLayout(this);
        layout2.setOrientation(LinearLayout.VERTICAL);


        Button addBtn = new Button(this);
        addBtn.setText("增加按钮");
        layout2.addView(addBtn);
        setContentView(layout2);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = new Button(m_Context);
                layout2.addView(btn);
                setContentView(layout2);
            }
        });






    }
}
