package com.example.wangjun.demoapk.SensorDemo;

import android.os.Build;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.wangjun.demoapk.R;

/*

    当你在androidkeystore中生成或导入密钥时，你可以指定密钥只在用户经过身份验证时被授权使用。用户通过他们的安全锁屏凭证（模式/pin/密码、指纹）的子集进行身份验证。

    用户身份认证授权一个秘钥相关的加解密操作。当前这样授权的唯一方法是指纹授权。

    如果将key存放在AndroidKeyStore中，可以为key设置一些保护(KeyProtection)，比如说用户验证过才可以使用key，那我们可以利用这点来增加安全性。
    首先我们生成一个key，并指定在用户验证后才可以使用这个key，并且存放在AndroidKeyStore中。说明下这个key是用来去加密content（也就是我们真正想要加密的数据，例如我们的密码），
    * 然后将加密后的内容存储。这个时候，加密Content的key已经收到保护了，只能通过身份验证才可以取出（例如指纹，当然还有其他方案，例如ConfirmCredentials，看android官网），那么key加密Content的结果（result）放哪里呢？是不是还需要放到一个隐秘的地方，将他保护起来呢？答案是不用的，因为即使result被拿到了，也是一串被加密过后的字符串而已，别人并不能通过result拿到我们的content，所以你将result放哪里都行，这里我就简单的放到sharePreference中。

    当你想要取出的时候，先使用指纹验证，然后取出AndroidKeyStore中的key，再取出sharepreference中的result，用key解密取出content。 用这种方案较为安全！

在 AndroidKeyStore 中生成key


    void generateKey() {
        //这里使用AES + CBC + PADDING_PKCS7，并且需要用户验证方能取出，这里生成加密content的key
        try {
            final KeyGenerator generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            mStore.load(null);
            final int purpose = KeyProperties.PURPOSE_DECRYPT | KeyProperties.PURPOSE_ENCRYPT;
            final KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder("key", purpose);
            builder.setUserAuthenticationRequired(true);
            builder.setBlockModes(KeyProperties.BLOCK_MODE_CBC);
            builder.setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
            generator.init(builder.build());
            generator.generateKey();
            LogUtil.d("生成加密密钥成功");
        } catch (Exception e) {
            LogUtil.d("生成加密密钥失败");
            e.printStackTrace();
        }
    }

取出key为content加密：

    mKeyStore.load(null);
    final SecretKey key = (SecretKey) mKeyStore.getKey("key", null);
    if (key == null) return;
    final Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"+ KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    final FingerprintManager.CryptoObject crypto = new FingerprintManager.CryptoObject(cipher);
    mFingerprintManager.authenticate(crypto, null, 0, new SimpleAuthenticationCallback() {
                @Override
                public void onAuthenticationSucceeded(final FingerprintManager.AuthenticationResult result) {
                    final Cipher cipher = result.getCryptoObject().getCipher();
                    byte[] encrypted = cipher.doFinal(content.getBytes());
                    byte[] IV = cipher.getIV();
                    String result= Base64.encodeToString(encrypted, Base64.URL_SAFE);
                    Log.d("tag", “result:” + result);
                }
         }, new Handler());


取出key为加密的内容解密：

    mKeyStore.load(null);
    final SecretKey key = (SecretKey) mKeyStore.getKey("key", null);
    if (key == null) return;
    final Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"+ KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
    cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(mIV));
    final FingerprintManager.CryptoObject crypto = new FingerprintManager.CryptoObject(cipher);
    mFingerprintManager.authenticate(crypto, null, 0, new SimpleAuthenticationCallback() {
                @Override
                public void onAuthenticationSucceeded(final FingerprintManager.AuthenticationResult result) {
                          final Cipher cipher = result.getCryptoObject().getCipher();
                          Log.d("tag", "Base 64 of data to decrypt is:\n" + Base64.encodeToString(encryptedToken, Base64.URL_SAFE) + "\n");
                          try {
                                     byte[] decrypted = cipher.doFinal(mEncrypted.getBytes());
                                     Log.d("tag", "Decrypted data is:\n" + Base64.encodeToString(decrypted, Base64.URL_SAFE) + "\n");
                         } catch (IllegalBlockSizeException | BadPaddingException e) {}
                }
       }, new Handler());




* */


public class CFingerprintActivity extends AppCompatActivity implements View.OnClickListener,CFingerprint_FingerprintHelper.SimpleAuthenticationCallback {

    private Button encrypt, decrypt;
    private TextView tv;
    private CFingerprint_FingerprintHelper helper;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensordemo_cfingerprint);
        encrypt = (Button) findViewById(R.id.encrypt);
        decrypt = (Button) findViewById(R.id.decrypt);
        tv = (TextView) findViewById(R.id.tv);
        encrypt.setOnClickListener(this);
        decrypt.setOnClickListener(this);
        helper = new CFingerprint_FingerprintHelper(this);
        helper.setCallback(this);
        helper.generateKey();
        tv.setText("已生成Key");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.encrypt:
                helper.setPurpose(KeyProperties.PURPOSE_ENCRYPT);
                tv.setText("开始验证指纹......");
                helper.authenticate();
                break;
            case R.id.decrypt:
                helper.setPurpose(KeyProperties.PURPOSE_DECRYPT);
                tv.setText("开始验证指纹......");
                helper.authenticate();
                break;
        }
    }

    @Override
    public void onAuthenticationSucceeded(String value) {
        tv.setText(value);
    }

    @Override
    public void onAuthenticationFail() {
        tv.setText("验证失败");
    }
}
