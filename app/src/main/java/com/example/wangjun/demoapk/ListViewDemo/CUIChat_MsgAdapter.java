package com.example.wangjun.demoapk.ListViewDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangjun.demoapk.R;

import java.util.List;

/**
 * Created by wangjun on 2017/9/28.
 */

public class CUIChat_MsgAdapter extends ArrayAdapter<CUIChat_Msg> {

    // 保存了消息布局的资源 ID
    private int resourceId;

    public CUIChat_MsgAdapter(Context context, int chatLayoutResourceId, List<CUIChat_Msg> objects) {
        super(context, chatLayoutResourceId, objects);
        resourceId = chatLayoutResourceId;
    }

    @Override
    // 当列表显示时，会调用此函数，获得列表项的布局
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获得 ListView 对应项中的列表项
        CUIChat_Msg msg = getItem(position);

        View view;
        ViewHolder viewHolder;


        if (convertView == null) {
            // 获得消息布局
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);

            // 创建一个消息布局对象
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
            viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        // 针对发送/接收消息，进行不同布局处理
        if (msg.getType() == CUIChat_Msg.TYPE_RECEIVED) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE); // 左布局开
            viewHolder.rightLayout.setVisibility(View.GONE);   // 右布局关
            viewHolder.leftMsg.setText(msg.getContent());       // 设置左消息显示
        } else if(msg.getType() == CUIChat_Msg.TYPE_SENT) {
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
        return view;
    }

    // 类：表示一条消息布局
    class ViewHolder {

        LinearLayout leftLayout;    // 左消息布局
        TextView leftMsg;           // 左消息

        LinearLayout rightLayout;   // 右消息布局
        TextView rightMsg;          // 右消息

    }

}