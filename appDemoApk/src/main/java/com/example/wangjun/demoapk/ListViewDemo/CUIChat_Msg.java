package com.example.wangjun.demoapk.ListViewDemo;

/**
 * Created by wangjun on 2017/9/28.
 */

public class CUIChat_Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    private String content;

    private int type;

    public CUIChat_Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
