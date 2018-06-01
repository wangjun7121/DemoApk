package com.example.wangjun.demoapk.Tools;

/**
 * Created by wangjun on 2018/6/1.
 */

public class DebugInfo extends Exception {
    public int line() {
        StackTraceElement[] trace = getStackTrace();
        if (trace == null || trace.length == 0) {
            return -1;
        }
        return trace[0].getLineNumber();
    }

    public String fun() {
        StackTraceElement[] trace = getStackTrace();
        if (trace == null || trace.length == 0) {
            return "";
        }
        return trace[0].getMethodName();
    }

    public DebugInfo() {
        super();
    }

    @Override
    public String toString() {
        return line() + "|" + fun() + "|";
    }
}

//使用方法
//        Log.d(TAG, new DebugInfo() + "hello world!");