package securityThink;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class MsgDigestDemo{
    public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String msg = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjcxOTE2NTYsImlzcyI6ImJhaXpoaWtlamkiLCJpYXQiOjE1NjQ1OTk2NTZ9.273nLq41iP5eymmMEHqs7CsLTyfGHVroYvRM-MX4owc";

        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        // 更新要计算的内容
        md5Digest.update(msg.getBytes());
        // 完成哈希计算，得到摘要
        byte[] md5Encoded = md5Digest.digest();

        MessageDigest shaDigest = MessageDigest.getInstance("SHA");
        // 更新要计算的内容
        shaDigest.update(msg.getBytes());
        // 完成哈希计算，得到摘要
        byte[] shaEncoded = shaDigest.digest();

        System.out.println("原文: " + msg);
        System.out.println("MD5摘要: " + Base64.encodeBase64URLSafeString(md5Encoded));
        System.out.println("SHA摘要: " + Base64.encodeBase64URLSafeString(shaEncoded));
    }
}
