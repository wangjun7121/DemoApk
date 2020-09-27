package com.example.wangjun.demoapk.SensorDemo;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;


/**
 * Created by hzlinxuanxuan on 2016/9/12.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class CFingerprint_FingerprintHelper extends FingerprintManager.AuthenticationCallback {

    private FingerprintManager manager;
    private CancellationSignal mCancellationSignal;
    private SimpleAuthenticationCallback callback;
    private CFingerprint_LocalSharedPreference mLocalSharedPreference;
    private CFingerprint_LocalAndroidKeyStore mLocalAndroidKeyStore;
    //PURPOSE_ENCRYPT,则表示生成token，否则为取出token
    private int purpose = KeyProperties.PURPOSE_ENCRYPT;
    private String data = "123456";

    public CFingerprint_FingerprintHelper(Context context) {
        // 获取指纹服务
        manager = context.getSystemService(FingerprintManager.class);

        // 初始化 SharedPreference 用于保存加密后数据
        mLocalSharedPreference = new CFingerprint_LocalSharedPreference(context);

        // 初始化密钥生成保存服务
        mLocalAndroidKeyStore = new CFingerprint_LocalAndroidKeyStore();
    }

    public void generateKey() {
        //在 keystore 中生成加密密钥
        mLocalAndroidKeyStore.generateKey(CFingerprint_LocalAndroidKeyStore.keyName);
        setPurpose(KeyProperties.PURPOSE_ENCRYPT);
    }

    public boolean isKeyProtectedEnforcedBySecureHardware() {
        // 判断 KeyStore 是使用安全硬件了没有
        return mLocalAndroidKeyStore.isKeyProtectedEnforcedBySecureHardware();
    }

    /**
     *
     * @param ctx
     * @return 0 支持指纹但是没有录入指纹； 1：有可用指纹； -1，手机不支持指纹
     */
    public int checkFingerprintAvailable(Context ctx) {
        if (!isKeyProtectedEnforcedBySecureHardware()) {
            return -1;
        } else if (!manager.isHardwareDetected()) {
            Toast.makeText(ctx, "该设备尚未检测到指纹硬件",Toast.LENGTH_SHORT).show();
            return -1;
        } else if (!manager.hasEnrolledFingerprints()) {
            Toast.makeText(ctx, "该设备未录入指纹，请去系统->设置中添加指纹",Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }

    // 获取保存在 SharedPreference 中的加密数据
    public boolean containsToken() {
        return mLocalSharedPreference.containsKey(mLocalSharedPreference.dataKeyName);
    }

    public void setCallback(SimpleAuthenticationCallback callback) {
        this.callback = callback;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }


    // 认证指纹
    public boolean authenticate() {
        try {
            FingerprintManager.CryptoObject object;

            // 根据配置进行加解密
            if (purpose == KeyProperties.PURPOSE_DECRYPT) {
                String IV = mLocalSharedPreference.getData(mLocalSharedPreference.IVKeyName);
                object = mLocalAndroidKeyStore.getCryptoObject(Cipher.DECRYPT_MODE, Base64.decode(IV, Base64.URL_SAFE));
                if (object == null) {
                    return false;
                }
            } else {
                object = mLocalAndroidKeyStore.getCryptoObject(Cipher.ENCRYPT_MODE, null);
            }
            mCancellationSignal = new CancellationSignal();

            // 进行指纹认证
            manager.authenticate(object, mCancellationSignal, 0, this, null);
            return true;
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 停止指纹认证
    public void stopAuthenticate() {
        if (mCancellationSignal != null) {
            mCancellationSignal.cancel();
            mCancellationSignal = null;
        }
        callback = null;
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        if (callback == null) {
            return;
        }
        if (result.getCryptoObject() == null) {
            callback.onAuthenticationFail();
            return;
        }
        final Cipher cipher = result.getCryptoObject().getCipher();

        // 解密
        if (purpose == KeyProperties.PURPOSE_DECRYPT) {
            //取出 secret key 并返回
            String data = mLocalSharedPreference.getData(mLocalSharedPreference.dataKeyName);
            if (TextUtils.isEmpty(data)) {
                callback.onAuthenticationFail();
                return;
            }
            try {
                byte[] decrypted = cipher.doFinal(Base64.decode(data, Base64.URL_SAFE));
                callback.onAuthenticationSucceeded(new String(decrypted));
            } catch (BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
                callback.onAuthenticationFail();
            }
        // 加密
        } else {
            //将前面生成的 data 包装成 secret key，存入沙盒
            try {
                byte[] encrypted = cipher.doFinal(data.getBytes());
                byte[] IV = cipher.getIV();
                String se = Base64.encodeToString(encrypted, Base64.URL_SAFE);
                String siv = Base64.encodeToString(IV, Base64.URL_SAFE);
                if (mLocalSharedPreference.storeData(mLocalSharedPreference.dataKeyName, se) &&
                        mLocalSharedPreference.storeData(mLocalSharedPreference.IVKeyName, siv)) {
                    callback.onAuthenticationSucceeded(se);
                }else{
                    callback.onAuthenticationFail();
                }
            } catch (BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
                callback.onAuthenticationFail();
            }
        }
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        if (callback != null) {
            callback.onAuthenticationFail();
        }
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
    }

    @Override
    public void onAuthenticationFailed() {
    }

    public interface SimpleAuthenticationCallback {
        void onAuthenticationSucceeded(String value);

        void onAuthenticationFail();
    }
}
