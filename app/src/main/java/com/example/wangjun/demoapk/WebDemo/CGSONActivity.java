package com.example.wangjun.demoapk.WebDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.wangjun.demoapk.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


//toJson - 将bean里面的内容转换为son内容
//        Person person = new Person("CrazyMouse",19);
//        Gson g = new Gson();
//        String result = g.toJson(person);
//        //结果是{"name":"CrazyMouse ","age":19}
//
//fromJson - 将json转换为bean对象
//    //简单类型
//        Person person = new Person();
//        Gson g = new Gson();
//        person = g.fromJson("{name:'zhangsan',age:12}", Person.class);
//
//    //复杂类型
//        Person person=new Person();
//        Gson g = new Gson();
//        String str = "[{name:'zhangsan',age:12},{name:'lisi',age:12}]";
//        Type type = new TypeToken<List<Person>>(){}.getType();
//        List<user> data = g.fromJson(str,  type);
//        //for(Person p : data){
//        //        System.out.println(p.getName()+" "+ p.getAge());
//        //}



public class CGSONActivity extends AppCompatActivity {
    private TextView writeText, readText;

    String strJson = "[{\"id\":\"5\",\"version\":\"5.5\",\"name\":\"Angry Birds\"},\n" +
                      "{\"id\":\"6\",\"version\":\"7.0\",\"name\":\"Clash of Clans\"},\n" +
                      "{\"id\":\"7\",\"version\":\"3.5\",\"name\":\"Hey Day\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webdemo_cgson);
        readText = (TextView) findViewById(R.id.readJsonText);
        writeText = (TextView) findViewById(R.id.writeJsonText);

        /*读取Json数据*/
        findViewById(R.id.readJsonBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                List<App> appList = gson.fromJson(strJson, new TypeToken<List<App>>() {
                }.getType());
                for (App app : appList) {
                    Log.d("MainActivity", "id is " + app.getId());
                    Log.d("MainActivity", "name is " + app.getName());
                    Log.d("MainActivity", "version is " + app.getVersion());
                    readText.append("id is " + app.getId() + "\n");
                    readText.append("name is " + app.getName() + "\n");
                    readText.append("version is " + app.getVersion() + "\n");
                }

            }
        });

         /*创建Json数据并显示*/
        findViewById(R.id.writeJsonBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                App person = new App("5","qiangang","5.5");
                Gson g = new Gson();
                String result = g.toJson(person);
                //结果是{"id":"5","name":"qiangang","version":"5.5"}
                writeText.setText(result);
                Log.d("MainActivity", result);
            }
        });


    }


    public class App {

        private String id;

        private String name;

        private String version;

        App(String id,String name,String version){
            this.id = id;
            this.name = name;
            this.version = version;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

    }

}
