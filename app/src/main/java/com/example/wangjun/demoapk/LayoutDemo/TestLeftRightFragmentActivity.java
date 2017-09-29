package com.example.wangjun.demoapk.LayoutDemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;


//【Fragment 和 Activity 之间进行通信】:
//    Activity 中获得 Fragment:
//        RightFragment rightFragment = (RightFragment) getFragmentManager().findFragmentById(R.id.right_fragment);
//
//    Fragment 中获得 Activity:
//        MainActivity activity = (MainActivity) getActivity();

public class TestLeftRightFragmentActivity extends AppCompatActivity {
    private Button m_tmpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutdemo_testleftrightfragment);

        m_tmpButton = (Button) findViewById(R.id.button);
        m_tmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.button:
//                        动态添加碎片主要分为 5 步。
//                            1. 创建待添加的碎片实例。
//                            2. 获取到 FragmentManager，在活动中可以直接调用 getFragmentManager()方法得到。
//                            3. 开启一个事务，通过调用 beginTransaction()方法开启。
//                            4. 向容器内加入碎片， 一般使用 replace()方法实现， 需要传入容器的 id 和待添加的碎
//                                片实例。
//                            5. 提交事务，调用 commit()方法来完成。

                        TestLeftRightFragment_Right2 fragment = new TestLeftRightFragment_Right2();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.right_layout, fragment);

                        //////////////////////////////////////////////////////////
                        // 添加用于返回上一个碎片：Fragment
                        transaction.addToBackStack(null);
                        ///////////////////////////////////////////////////////////

                        transaction.commit();
                        break;
                    default:
                        break;
            }
        }});


    }
}
