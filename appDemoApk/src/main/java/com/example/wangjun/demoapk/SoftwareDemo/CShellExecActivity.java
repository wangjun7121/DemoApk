package com.example.wangjun.demoapk.SoftwareDemo;

import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.Tools.LogUtil;
import com.example.wangjun.demoapk.Tools.RunScript;
import com.example.wangjun.demoapk.Tools.ShellExec;

// 使用要关闭 selinux 的
public class CShellExecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.softwaredemo_cshellexec);


        findViewById(R.id.ShellExec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///////////////////////////////////////////////////////////
                // 同步执行命令
                ///////////////////////////////////////////////////////////
                //读取目标文件（绝对路径）指定内容“#TrustedMACList ”的那一行
                String cmd3="ls";
                //String cmd3="pwd";
                String str3 = new ShellExec().run(cmd3, 10000).getResult();
                LogUtil.d("ShellExec = " + str3);
                //Toast.makeText(CShellExecActivity.this, str3,Toast.LENGTH_SHORT).show();

                String strMeminfo = RunScript.runIt("cat /proc/meminfo");
                LogUtil.d("RunScript = " + strMeminfo);
                /*
                ///////////////////////////////////////////////////////////
                // 异步执行命令
                ///////////////////////////////////////////////////////////
                 cmd = new ExeCommand(false).run("date 022507002025.37 set", 60000);
                 while(cmd.isRunning())
                 {
                     try {
                         Thread.sleep(1000);
                     } catch (Exception e) {

                     }
                     String buf = cmd.getResult();
                     //do something
                 }*/


            }
        });

    }


}