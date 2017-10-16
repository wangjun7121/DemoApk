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

public class CUIChatActivity extends Activity {

    private ListView msgListView;

    private EditText inputText;

    private Button send;

    private CUIChat_MsgAdapter adapter;

    // 聊天消息链表
    private List<CUIChat_Msg> msgList = new ArrayList<CUIChat_Msg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.listviewdemo_testuichat);

        // 添加默认的聊天信息
        initMsgs();

        // 传入参数：Context, 消息布局，消息列表
        adapter = new CUIChat_MsgAdapter(CUIChatActivity.this, R.layout.listviewdemo_cuichat_msg_item, msgList);

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
                    CUIChat_Msg msg = new CUIChat_Msg(content, CUIChat_Msg.TYPE_SENT);
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
        CUIChat_Msg msg1 = new CUIChat_Msg("Hello guy.", CUIChat_Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        CUIChat_Msg msg2 = new CUIChat_Msg("Hello. Who is that?", CUIChat_Msg.TYPE_SENT);
        msgList.add(msg2);
        CUIChat_Msg msg3 = new CUIChat_Msg("This is Tom. Nice talking to you. ", CUIChat_Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

}
