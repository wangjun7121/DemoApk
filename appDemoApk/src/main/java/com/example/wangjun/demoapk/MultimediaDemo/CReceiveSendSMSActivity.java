package com.example.wangjun.demoapk.MultimediaDemo;

import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CReceiveSendSMSActivity extends Activity {

    private TextView sender;

    private TextView content;

    private EditText to;

    private EditText msgInput;

    private Button send;

    private IntentFilter receiveFilter;

    private MessageReceiver messageReceiver;

    private IntentFilter sendFilter;

    private SendStatusReceiver sendStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_creceivesendsms);

        sender = (TextView) findViewById(R.id.sender);
        content = (TextView) findViewById(R.id.content);
        to = (EditText) findViewById(R.id.to);
        msgInput = (EditText) findViewById(R.id.msg_input);
        send = (Button) findViewById(R.id.send);

        // 监听接收短信的广播，调用 MessageReceiver 处理
        receiveFilter = new IntentFilter();
        receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        receiveFilter.setPriority(100); // 提交接收广播的优先级
        messageReceiver = new MessageReceiver();
        registerReceiver(messageReceiver, receiveFilter);


        // 监听发送短信的发送短信的状态广播，调用 SendStatusReceiver 处理
        sendFilter = new IntentFilter();
        sendFilter.addAction("SENT_SMS_ACTION");
        sendStatusReceiver = new SendStatusReceiver();
        registerReceiver(sendStatusReceiver, sendFilter);
        // 发送按钮处理函数
        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//                当 Send 按钮被点击时，会先调用 SmsManager 的
//                getDefault()方法获取到 SmsManager 的实例，然后再调用它的 sendTextMessage()方法就可以
//                去发送短信了。 sendTextMessage()方法接收五个参数， 其中第一个参数用于指定接收人的手
//                机号码， 第三个参数用于指定短信的内容， 其他的几个参数我们暂时用不到， 直接传入 null
//                就可以了。



                SmsManager smsManager = SmsManager.getDefault();
                Intent sentIntent = new Intent("SENT_SMS_ACTION");
                PendingIntent pi = PendingIntent.getBroadcast(CReceiveSendSMSActivity.this, 0, sentIntent, 0);
                smsManager.sendTextMessage(to.getText().toString(), null,
                        msgInput.getText().toString(), pi, null);



//                另外， 根据国际标准， 每条短信的长度不得超过 160 个字符， 如果想要发送超出这个长
//                度的短信，则需要将这条短信分割成多条短信来发送，使用 SmsManager 的 sendMultipartTextMessage()
//                方法就可以实现上述功能。 它的用法和 sendTextMessage()方法也基本类似， 感兴趣的话你可以自己研究一下


            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
        unregisterReceiver(sendStatusReceiver);
    }

    // 收到短信时的处理函数
    class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {



//            首先我们从 Intent 参数中取出了一个 Bundle 对象， 然后使用 pdu 密钥来提取
//            一个 SMS pdus 数组，其中每一个 pdu 都表示一条短信消息。接着使用 SmsMessage 的
//            createFromPdu()方法将每一个 pdu 字节数组转换为 SmsMessage 对象，调用这个对象的
//            getOriginatingAddress()方法就可以获取到短信的发送方号码，调用 getMessageBody()方法就
//            可以获取到短信的内容，然后将每一个 SmsMessage 对象中的短信内容拼接起来，就组成了
//            一条完整的短信。最后将获取到的发送方号码和短信内容显示在 TextView 上


            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[]) bundle.get("pdus"); // 提取短信消息
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            // 获取发送方号码
            String address = messages[0].getOriginatingAddress();
            String fullMessage = "";
            for (SmsMessage message : messages) {
                fullMessage += message.getMessageBody(); // 获取短信内容
            }
            sender.setText(address);
            content.setText(fullMessage);

            // 截断短信广播，不让其他应用程序进行处理了
            abortBroadcast();
        }

    }

    // 监听发送的短信的状态回调函数
    class SendStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK) {
                // 短信发送成功
                Toast.makeText(context, "Send succeeded", Toast.LENGTH_LONG)
                        .show();
            } else {
                // 短信发送失败
                Toast.makeText(context, "Send failed", Toast.LENGTH_LONG)
                        .show();
            }
        }

    }

}