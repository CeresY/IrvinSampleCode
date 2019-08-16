package securityThink;

import java.security.MessageDigest;

public class MD5 {

    // test
    public static void main(String[] args) {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjcxOTE2NTYsImlzcyI6ImJhaXpoaWtlamkiLCJpYXQiOjE1NjQ1OTk2NTZ9.273nLq41iP5eymmMEHqs7CsLTyfGHVroYvRM-MX4owc";
        System.out.println(getMD5Code(jwt));
        System.out.println(getMD5Code(jwt));
        System.out.println(getMD5Code("shanghai"));
    }

    private MD5() {
    }

    // md5加密
    public static String getMD5Code(String message) {
        String md5Str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(message.getBytes());
            md5Str = bytes2Hex(md5Bytes);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    // 2进制转16进制
    public static String bytes2Hex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        int temp;
        try {
            for (int i = 0; i < bytes.length; i++) {
                temp = bytes[i];
                if(temp < 0) {
                    temp += 256;
                }
                if (temp < 16) {
                    result.append("0");
                }
                result.append(Integer.toHexString(temp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
