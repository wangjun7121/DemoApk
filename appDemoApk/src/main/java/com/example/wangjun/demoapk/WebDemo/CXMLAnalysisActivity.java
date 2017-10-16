package com.example.wangjun.demoapk.WebDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class CXMLAnalysisActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private Button bt_dom, bt_sax, bt_pull;
    private CXMLAnalysisActivity_XMLUtils xmlUtils;
    private List<CXMLAnalysisActivity_Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webdemo_cxmlanalysis);

        tv = (TextView) findViewById(R.id.textView);
        bt_dom = (Button) findViewById(R.id.domBT);
        bt_sax = (Button) findViewById(R.id.saxBT);
        bt_pull = (Button) findViewById(R.id.pullBT);

        bt_dom.setOnClickListener(this);
        bt_sax.setOnClickListener(this);
        bt_pull.setOnClickListener(this);

        xmlUtils = new CXMLAnalysisActivity_XMLUtils();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.domBT:
                try {
                    students = xmlUtils.dom2xml(getAssets().open("cxmlanalysis_student.xml"));
                    tv.setText(students.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.saxBT:
                try {
                    students = xmlUtils.sax2xml(getAssets().open("cxmlanalysis_student.xml"));
                    tv.setText(students.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.pullBT:
                try {
                    students = xmlUtils.pull2xml(getAssets().open("cxmlanalysis_student.xml"));
                    tv.setText(students.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}