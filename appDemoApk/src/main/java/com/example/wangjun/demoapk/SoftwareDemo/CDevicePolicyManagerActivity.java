package com.example.wangjun.demoapk.SoftwareDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class CDevicePolicyManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_regDivice,btn_unRegDivice,btn_lockScreen;
    private DevicePolicyManager devicePolicyManager;
    public ComponentName componentName;//权限监听器
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.softwaredemo_cdevicepolicymanager);
        btn_regDivice = (Button)findViewById(R.id.btn_regDivice);
        btn_regDivice.setOnClickListener(this);
        btn_unRegDivice = (Button)findViewById(R.id.btn_unRegDivice);
        btn_unRegDivice.setOnClickListener(this);
        btn_lockScreen = (Button)findViewById(R.id.btn_lockScreen);
        btn_lockScreen.setOnClickListener(this);


        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        componentName =  new ComponentName(this,CDevicePolicyManager_MyAdmin.class);//用广播接收器实例化一个系统组件
        flag = devicePolicyManager.isAdminActive(componentName);//判断这个应用是否激活了设备管理器
        if(flag){
            btn_regDivice.setVisibility(View.GONE);
            btn_lockScreen.setVisibility(View.VISIBLE);
            btn_unRegDivice.setVisibility(View.VISIBLE);
        }else{
            btn_regDivice.setVisibility(View.VISIBLE);
            btn_lockScreen.setVisibility(View.GONE);
            btn_unRegDivice.setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_regDivice:
                Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);//激活系统设备管理器
                i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);//注册系统组件
                startActivity(i);
                break;
            case R.id.btn_unRegDivice:
                devicePolicyManager.removeActiveAdmin(componentName);//注销系统组件
                this.finish();
                break;
            case R.id.btn_lockScreen:
                devicePolicyManager.lockNow();
                //devicePolicyManager.resetPassword("123", 0); // 设置锁屏密码
                //devicePolicyManager.wipeData(0);  恢复出厂设置  (建议大家不要在真机上测试) 模拟器不支持该操作
                this.finish();
                break;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        boolean flagChanged = devicePolicyManager.isAdminActive(componentName);//判断这个应用是否激活了设备管理器
        if(flagChanged){
            btn_regDivice.setVisibility(View.GONE);
            btn_lockScreen.setVisibility(View.VISIBLE);
            btn_unRegDivice.setVisibility(View.VISIBLE);
        }else{
            btn_regDivice.setVisibility(View.VISIBLE);
            btn_lockScreen.setVisibility(View.GONE);
            btn_unRegDivice.setVisibility(View.GONE);
        }
    }

}