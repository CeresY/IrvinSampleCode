package securityThink;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description // 信息摘要工具
 * @Author yz
 * @Date 2019-8-16
 * @Vesion 1.0
 **/
public class MsgDigestUtil {

    public static String md5(String msg) throws NoSuchAlgorithmException {
        return digest(msg, AlgorithmE.MD5);
    }

    public static String sha(String msg) throws NoSuchAlgorithmException {
        return digest(msg, AlgorithmE.SHA);
    }

    private static String digest(String msg, AlgorithmE algorithmE) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithmE.name());
        // 更新要计算的内容
        digest.update(msg.getBytes());
        // 完成哈希计算，得到摘要
        byte[] msgEncoded = digest.digest();
        // base64加密
        return Base64.encodeBase64URLSafeString(msgEncoded);
    }

    enum AlgorithmE {
        MD5,SHA;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String msg = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjcxOTE2NTYsImlzcyI6ImJhaXpoaWtlamkiLCJpYXQiOjE1NjQ1OTk2NTZ9.273nLq41iP5eymmMEHqs7CsLTyfGHVroYvRM-MX4owc";
        System.out.println(md5(msg));
        System.out.println(sha(msg));
    }

}
