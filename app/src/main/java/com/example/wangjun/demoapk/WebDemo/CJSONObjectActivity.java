package com.example.wangjun.demoapk.WebDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.wangjun.demoapk.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CJSONObjectActivity extends AppCompatActivity {
    private TextView writeText, readText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webdemo_cjsonobject);
        readText = (TextView) findViewById(R.id.readJsonText);
        writeText = (TextView) findViewById(R.id.writeJsonText);
        /*读取Json数据*/
        findViewById(R.id.readJsonBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*获取到assets文件下的 cjsonanalysis.json文件的数据，并以输出流形式返回。*/
                InputStream is = CJSONObjectActivity.this.getClass().getClassLoader().getResourceAsStream("assets/" + "cjsonobject.json");
                InputStreamReader streamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    while ((line = reader.readLine()) != null) {
                        // stringBuilder.append(line);
                        stringBuilder.append(line);
                    }
                    reader.close();
                    reader.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject person = new JSONObject(stringBuilder.toString());
                    JSONArray infArray = person.getJSONArray("inf");
                    for (int i = 0; i < infArray.length(); i++) {
                        JSONObject inf_Array = infArray.getJSONObject(i);
                        readText.append("name:" + inf_Array.getString("name") + "\n");
                        readText.append("IdCard:" + inf_Array.getString("IdCard"));
                        readText.append("age:" + inf_Array.getInt("age"));
                        readText.append("married:" + inf_Array.getBoolean("married"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

         /*创建Json数据并显示*/
        findViewById(R.id.writeJsonBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject inf = new JSONObject();
                    inf.put("number", "5");

                    JSONArray array = new JSONArray();
                    JSONObject arr_ = new JSONObject();
                    arr_.put("name", "张三");
                    arr_.put("age", "11");
                    arr_.put("IdCard", "XC");
                    arr_.put("married", true);

                    JSONObject arr_1 = new JSONObject();
                    arr_1.put("name", "李四");
                    arr_1.put("age", "55");
                    arr_1.put("IdCard", "@DC");
                    arr_1.put("married", true);

                    array.put(arr_);
                    array.put(arr_1);
                    inf.put("inf", array);
                    writeText.setText(inf.toString());
                    Log.v("WJWind",inf.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}