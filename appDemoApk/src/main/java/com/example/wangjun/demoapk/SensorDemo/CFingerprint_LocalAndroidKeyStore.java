package com.example.wangjun.demoapk.SensorDemo;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyInfo;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;

/**
 *  KeyStore 类介绍
 *
 *
 * This class represents a storage facility for cryptographic
 * keys and certificates.
 *
 * <p> A <code>KeyStore</code> manages different types of entries.
 * Each type of entry implements the <code>KeyStore.Entry</code> interface.
 * Three basic <code>KeyStore.Entry</code> implementations are provided:
 *
 * <ul>
 * <li><b>KeyStore.PrivateKeyEntry</b>
 * <p> This type of entry holds a cryptographic <code>PrivateKey</code>,
 * which is optionally stored in a protected format to prevent
 * unauthorized access.  It is also accompanied by a certificate chain
 * for the corresponding public key.
 *
 * <p> Private keys and certificate chains are used by a given entity for
 * self-authentication. Applications for this authentication include software
 * distribution organizations which sign JAR files as part of releasing
 * and/or licensing software.
 *
 * <li><b>KeyStore.SecretKeyEntry</b>
 * <p> This type of entry holds a cryptographic <code>SecretKey</code>,
 * which is optionally stored in a protected format to prevent
 * unauthorized access.
 *
 * <li><b>KeyStore.TrustedCertificateEntry</b>
 * <p> This type of entry contains a single public key <code>Certificate</code>
 * belonging to another party. It is called a <i>trusted certificate</i>
 * because the keystore owner trusts that the public key in the certificate
 * indeed belongs to the identity identified by the <i>subject</i> (owner)
 * of the certificate.
 *
 * <p>This type of entry can be used to authenticate other parties.
 * </ul>
 *
 * <p> Each entry in a keystore is identified by an "alias" string. In the
 * case of private keys and their associated certificate chains, these strings
 * distinguish among the different ways in which the entity may authenticate
 * itself. For example, the entity may authenticate itself using different
 * certificate authorities, or using different public key algorithms.
 *
 * <p> Whether aliases are case sensitive is implementation dependent. In order
 * to avoid problems, it is recommended not to use aliases in a KeyStore that
 * only differ in case.
 *
 * <p> Whether keystores are persistent, and the mechanisms used by the
 * keystore if it is persistent, are not specified here. This allows
 * use of a variety of techniques for protecting sensitive (e.g., private or
 * secret) keys. Smart cards or other integrated cryptographic engines
 * (SafeKeyper) are one option, and simpler mechanisms such as files may also
 * be used (in a variety of formats).
 *
 * <p> Typical ways to request a KeyStore object include
 * relying on the default type and providing a specific keystore type.
 *
 * <ul>
 * <li>To rely on the default type:
 * <pre>
 *    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
 * </pre>
 * The system will return a keystore implementation for the default type.
 * <p>
 *
 * <li>To provide a specific keystore type:
 * <pre>
 *      KeyStore ks = KeyStore.getInstance("JKS");
 * </pre>
 * The system will return the most preferred implementation of the
 * specified keystore type available in the environment. <p>
 * </ul>
 *
 * <p> Before a keystore can be accessed, it must be
 * {@link #load(java.io.InputStream, char[]) loaded}.
 * <pre>
 *    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
 *
 *    // get user password and file input stream
 *    char[] password = getPassword();
 *
 *    java.io.FileInputStream fis = null;
 *    try {
 *        fis = new java.io.FileInputStream("keyStoreName");
 *        ks.load(fis, password);
 *    } finally {
 *        if (fis != null) {
 *            fis.close();
 *        }
 *    }
 * </pre>
 *
 * To create an empty keystore using the above <code>load</code> method,
 * pass <code>null</code> as the <code>InputStream</code> argument.
 *
 * <p> Once the keystore has been loaded, it is possible
 * to read existing entries from the keystore, or to write new entries
 * into the keystore:
 * <pre>
 *    KeyStore.ProtectionParameter protParam =
 *        new KeyStore.PasswordProtection(password);
 *
 *    // get my private key
 *    KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry)
 *        ks.getEntry("privateKeyAlias", protParam);
 *    PrivateKey myPrivateKey = pkEntry.getPrivateKey();
 *
 *    // save my secret key
 *    javax.crypto.SecretKey mySecretKey;
 *    KeyStore.SecretKeyEntry skEntry =
 *        new KeyStore.SecretKeyEntry(mySecretKey);
 *    ks.setEntry("secretKeyAlias", skEntry, protParam);
 *
 *    // store away the keystore
 *    java.io.FileOutputStream fos = null;
 *    try {
 *        fos = new java.io.FileOutputStream("newKeyStoreName");
 *        ks.store(fos, password);
 *    } finally {
 *        if (fos != null) {
 *            fos.close();
 *        }
 *    }
 * </pre>
 *
 * Note that although the same password may be used to
 * load the keystore, to protect the private key entry,
 * to protect the secret key entry, and to store the keystore
 * (as is shown in the sample code above),
 * different passwords or other protection parameters
 * may also be used.
 *
 * <p> Android provides the following <code>KeyStore</code> types:
 * <table>
 *     <thead>
 *         <tr>
 *             <th>Name</th>
 *             <th>Supported (API Levels)</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td>AndroidCAStore</td>
 *             <td>14+</td>
 *         </tr>
 *         <tr>
 *             <td>AndroidKeyStore</td>
 *             <td>18+</td>
 *         </tr>
 *         <tr>
 *             <td>BCPKCS12</td>
 *             <td>1&ndash;8</td>
 *         </tr>
 *         <tr>
 *             <td>BKS</td>
 *             <td>1+</td>
 *         </tr>
 *         <tr>
 *             <td>BouncyCastle</td>
 *             <td>1+</td>
 *         </tr>
 *         <tr>
 *             <td>PKCS12</td>
 *             <td>1+</td>
 *         </tr>
 *         <tr>
 *             <td>PKCS12-DEF</td>
 *             <td>1&ndash;8</td>
 *         </tr>
 *     </tbody>
 * </table>
 *
 * These types are described in the <a href=
 * "{@docRoot}openjdk-redirect.html?v=8&path=/technotes/guides/security/StandardNames.html#KeyStore">
 * KeyStore section</a> of the
 * Java Cryptography Architecture Standard Algorithm Name Documentation.
 *
 * @author Jan Luehe
 *
 * @see java.security.PrivateKey
 * @see javax.crypto.SecretKey
 * @see java.security.cert.Certificate
 *
 * @since 1.2
 */

public class CFingerprint_LocalAndroidKeyStore {

    private KeyStore mStore;
    public static final String keyName = "key";

    CFingerprint_LocalAndroidKeyStore() {
        try {
            // 获取 KeyStore 实例
            mStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    // 使用 KeyGenerator 生成密钥，会在 keyAlias 名称保存到 KeyStore 中
    void generateKey(String keyAlias) {
        //这里使用 AES + CBC + PADDING_PKCS7，并且需要用户验证方能取出
        try {
            // 使用 KeyGenerator 生成密钥
            final KeyGenerator generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            mStore.load(null);
            final int purpose = KeyProperties.PURPOSE_DECRYPT | KeyProperties.PURPOSE_ENCRYPT;
            final KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(keyAlias, purpose);
            builder.setUserAuthenticationRequired(true);
            builder.setBlockModes(KeyProperties.BLOCK_MODE_CBC);
            builder.setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
            generator.init(builder.build());
            generator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    FingerprintManager.CryptoObject getCryptoObject(int purpose, byte[] IV) {
        try {
            mStore.load(null);

            // 获取密钥
            final SecretKey key = (SecretKey) mStore.getKey(keyName, null);
            if (key == null) {
                return null;
            }

            // 加解密套件选择
            final Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC
                    + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);

            if (purpose == KeyProperties.PURPOSE_ENCRYPT) {
                cipher.init(purpose, key);
            } else {
                cipher.init(purpose, key, new IvParameterSpec(IV));
            }
            // 设置指纹加密套件？
            return new FingerprintManager.CryptoObject(cipher);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    // 判断密钥是否由安全硬件保存，还是需要用户认证的安全用户保护
    public boolean isKeyProtectedEnforcedBySecureHardware() {
        try {
            // 使用 KeyGenerator 这里随便生成一个key，检查是不是受保护即可
            generateKey("temp");
            // 从 KeyStore 中取出密钥
            final SecretKey key = (SecretKey) mStore.getKey("temp", null);
            if (key == null) {
                return false;
            }
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            KeyInfo keyInfo;
            keyInfo = (KeyInfo) factory.getKeySpec(key, KeyInfo.class);
            return keyInfo.isInsideSecureHardware() && keyInfo.isUserAuthenticationRequirementEnforcedBySecureHardware();
        } catch (Exception e) {
            // Not an Android KeyStore key.
            return false;
        }
    }
}
