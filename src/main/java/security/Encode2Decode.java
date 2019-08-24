package security;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.text.StringEscapeUtils;
import util.Exceptions;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/** 封装各种[对称加密]及[摘要算法]的编码解码工具类.
 * 1.Commons-Codec的 hex/base64 编码
 * 2.自制的base62 编码
 * 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 * 5.SHA-1,SHA1,MD5摘要算法
 * 6.AES高级加密标准算法
 *
  * @author yangzhan
  * 2018年4月13日
  */
public class Encode2Decode {
	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * Base64编码.
	 */
	public static String encodeBase64(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 */
	public static String encodeUrlSafeBase64(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
		}
		return new String(chars);
	}

	/**
	 * Html 转码.
	 */
	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * Html 解码.
	 */
	public static String unescapeHtml(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String escapeXml(String xml) {
		return StringEscapeUtils.escapeXml10(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String unescapeXml(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8. 
	 */
	public static String urlDecode(String part) {

		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}
	
	/**
	 * SHA-1摘要算法
	 */
	public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
 
	/**
	 * SHA摘要算法
	 */
    public static String SHA(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
	 * MD5摘要算法
	 */
    public static String MD5(String input) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * AES加密
     *
     * @param content
     *            需要加密的内容
     * @param password
     *            加密密码
     * @return
     */
    public static byte[] encryptAES(String content, String password) {
    	 try {              
             KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
             //防止linux下 随机生成key
             SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");   
             secureRandom.setSeed(password.getBytes());   
             kgen.init(128, secureRandom);
             SecretKey secretKey = kgen.generateKey();   
             byte[] enCodeFormat = secretKey.getEncoded();   
             SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");   
             Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
             byte[] byteContent = content.getBytes("utf-8");   
             cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
             byte[] result = cipher.doFinal(byteContent);   
             return result; // 加密   
         } catch (NoSuchAlgorithmException e) {   
             e.printStackTrace();   
         } catch (NoSuchPaddingException e) {   
             e.printStackTrace();   
         } catch (InvalidKeyException e) {   
             e.printStackTrace();   
         } catch (UnsupportedEncodingException e) {   
             e.printStackTrace();   
         } catch (IllegalBlockSizeException e) {   
             e.printStackTrace();   
         } catch (BadPaddingException e) {   
             e.printStackTrace();   
         }   
         return null;  
    }
 
    /**
     * AES解密
     *
     * @param content
     *            待解密内容
     * @param password
     *            解密密钥
     * @return
     */
    public static byte[] decryptAES(byte[] content, String password) {
    	try {   
            KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
            //防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();   
            byte[] enCodeFormat = secretKey.getEncoded();   
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");               
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
            byte[] result = cipher.doFinal(content);   
            return result; // 加密   
        } catch (NoSuchAlgorithmException e) {   
            e.printStackTrace();   
        } catch (NoSuchPaddingException e) {   
            e.printStackTrace();   
        } catch (InvalidKeyException e) {   
            e.printStackTrace();   
        } catch (IllegalBlockSizeException e) {   
            e.printStackTrace();   
        } catch (BadPaddingException e) {   
            e.printStackTrace();   
        }   
        return null; 
    }
    
    /** AES demo */
    public static void main(String[] args) throws Exception {
    	byte[] a1 = encryptAES("hello abcdefggsdfasdfasdf","abcdefgabcdefg12");
    	String b1 = encodeHex(a1);
    	byte[] b2 = decodeHex(b1);
		System.out.println(b1.toString());
		byte[] a2 = decryptAES(b2,"abcdefgabcdefg12");
		System.out.println(new String(a2,"utf-8"));
	}
}
