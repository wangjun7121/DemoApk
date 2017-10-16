package com.example.wangjun.demoapk.IntentDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.wangjun.demoapk.R;



//Intent 传递自定义对象：
//    1. Serializable
//        Serializable 是序列化的意思， 表示将一个对象转换成可存储或可传输的状态。 序列化后
//        的对象可以在网络上进行传输， 也可以存储到本地。 至于序列化的方法也很简单， 只需要让
//        一个类去实现 Serializable 这个接口就可以了
//            public class Person implements Serializable{
//                private String name;
//                private int age;
//                public String getName() {
//                    return name;
//                }
//                public void setName(String name) {
//                    this.name = name;
//                }
//                public int getAge() {
//                    return age;
//                }
//                public void setAge(int age) {
//                    this.age = age;
//                }
//            }
//
//        传参：
//                Person person = new Person();
//                person.setName("Tom");
//                person.setAge(20);
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                intent.putExtra("person_data", person);
//                startActivity(intent);
//
//        接收参数：
//            SecondActivity 中获取这个对象也很简单，写法如下：
//                Person person = (Person) getIntent().getSerializableExtra("person_data");
//    2. Parcelable 方式
//            Parcelable 方式的实现原理是将一个完整的对象进行分解，而分解后的每一部分都
//            是 Intent 所支持的数据类型，这样也就实现传递对象的功能了
//
//                public class Person implements Parcelable {
//                    private String name;
//                    private int age;
//                        ……
//
//                    //////////////////////////////////////////////////////////////////////////////
//                    // 首先我们让 Person 类去实现了 Parcelable 接口，这样就必须重写
//                    //     describeContents()和 writeToParcel()这两个方法
//                    @Override
//                    public int describeContents() {
//                        return 0;
//                    }
//                    @Override
//                    public void writeToParcel(Parcel dest, int flags) {
//                        dest.writeString(name); // 写出 name
//                        dest.writeInt(age);     // 写出 age
//                    }
//
//                   // 我们还必须在 Person 类中提供一个名为 CREATOR 的常量，这里创建了
//                   // Parcelable.Creator 接口的一个实现， 并将泛型指定为 Person。接着需要重写 createFromParcel()
//                   // 和 newArray()这两个方法， 在 createFromParcel()方法中我们要去读取刚才写出的 name 和 age
//                   // 字段， 并创建一个 Person 对象进行返回， 其中 name 和 age 都是调用 Parcel 的 readXxx()方法
//                   // 读取到的， 注意这里读取的顺序一定要和刚才写出的顺序完全相同
//                    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.
//                            Creator<Person>() {
//                        @Override
//                        public Person createFromParcel(Parcel source) {
//                            Person person = new Person();
//                            person.name = source.readString(); // 读取name
//                            person.age = source.readInt(); // 读取age
//                            return person;
//                        }
//                        @Override
//                        public Person[] newArray(int size) {
//                            return new Person[size];
//                        }
//                    };
//                    //////////////////////////////////////////////////////////////////////////////
//                }
//
//            传递参数：
//                    Person person = new Person();
//                    person.setName("Tom");
//                    person.setAge(20);
//                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                    intent.putExtra("person_data", person);
//                    startActivity(intent);
//            接收参数：
//                    Person person = (Person) getIntent().getParcelableExtra("person_data");



public class CIntentArgsActivity extends Activity {
    private static String sLogcatTAG = "IntentDemoActivity";

    private TextView m_Textview1;
    private Button m_IntentDataBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.intentdemo_cintentargs);

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
