package com.example.wangjun.demoapk.ListViewDemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.wangjun.demoapk.R;

public class TestUIChatActivity extends Activity {

    private ListView msgListView;

    private EditText inputText;

    private Button send;

    private TestUIChat_MsgAdapter  adapter;

    // 聊天消息链表
    private List<TestUIChat_Msg> msgList = new ArrayList<TestUIChat_Msg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.listviewdemo_testuichat);

        // 添加默认的聊天信息
        initMsgs();

        // 传入参数：Context, 消息布局，消息列表
        adapter = new TestUIChat_MsgAdapter(TestUIChatActivity.this, R.layout.listviewdemo_testuichat_msg_item, msgList);

        // 显示的消息列表框
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);

        // 创建发送消息编辑框及按钮
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    // 创建一条聊天消息对象
                    TestUIChat_Msg msg = new TestUIChat_Msg(content, TestUIChat_Msg.TYPE_SENT);
                    // 添加到消息列表中
                    msgList.add(msg);

                    // 通知消息列表变化了
                    adapter.notifyDataSetChanged();
                    // 定位到列表最后一行
                    msgListView.setSelection(msgList.size());

                    // 清空编辑框
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        TestUIChat_Msg msg1 = new TestUIChat_Msg("Hello guy.", TestUIChat_Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        TestUIChat_Msg msg2 = new TestUIChat_Msg("Hello. Who is that?", TestUIChat_Msg.TYPE_SENT);
        msgList.add(msg2);
        TestUIChat_Msg msg3 = new TestUIChat_Msg("This is Tom. Nice talking to you. ", TestUIChat_Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

}
