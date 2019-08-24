package security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @Description // https://blog.csdn.net/qq_36565494/article/details/79005230
 * @Author yz
 * @Date 2019-8-24
 * @Vesion 1.0
 **/
public class RSAUtil2 {

    /**
     * 产生RSA公私钥对
     * @return
     */
    public static KeyPair genRSAKeyPair() {
        KeyPairGenerator rsaKeyGen = null;
        KeyPair rsaKeyPair = null;
        try {
            System.out.println("Generating a pair of RSA key ... ");
            rsaKeyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = new SecureRandom();
            random.nextBytes(new byte[1]);
            rsaKeyGen.initialize(1024, new SecureRandom());
            rsaKeyPair = rsaKeyGen.genKeyPair();
            //获取公钥
            PublicKey rsaPublic = rsaKeyPair.getPublic();
            //获取私钥
            PrivateKey rsaPrivate = rsaKeyPair.getPrivate();
            System.out.println("1024-bit RSA key GENERATED.");
        } catch (Exception e) {
            System.out.println("Exception in keypair generation. Reason: " + e);
        }
        return rsaKeyPair;
    }
    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey1(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }
    /**
     * 解密
     *
     * @param plainText  加密后的加密文件
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String encodeString(byte[] plainText, String privateKey) throws Exception {
        //加解密类
        Cipher cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");
        PrivateKey privateKey1 = getPrivateKey1(privateKey);
        cipher.init(Cipher.DECRYPT_MODE, privateKey1);
        byte[] deBytes = cipher.doFinal(plainText);
        String s = new String(deBytes);
        return s;
    }
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = RSAUtil2.genRSAKeyPair();
        PublicKey aPublic = keyPair.getPublic(); // 公钥
        PrivateKey aPrivate = keyPair.getPrivate(); // 私钥

        String encodePublic = new BASE64Encoder().encode(aPublic.getEncoded());
        String encodePrivate = new BASE64Encoder().encode(aPrivate.getEncoded());
        System.out.println(encodePublic.replaceAll("\r\n",""));
        System.out.println(encodePrivate.replaceAll("\r\n",""));

        //待加密文件
        String str = "测试解密ceuijxmi123";
        byte[] s = str.getBytes();
        Cipher cipher=Cipher.getInstance("RSA");

        //加密
        cipher.init(Cipher.ENCRYPT_MODE, aPublic);
        byte[] enBytes = cipher.doFinal(s);
        System.out.println("加密后的密文：" + new String(enBytes));
        //解密
        String s1 = RSAUtil2.encodeString(enBytes, encodePrivate);
        System.out.println("解密后的密文：" +s1);
    }
}