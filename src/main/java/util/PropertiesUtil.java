package util;

import security.Encode2Decode;

import java.util.Properties;

/** 解析properties工具类
  * @author yangzhan
  * 2018年4月13日
  */
public class PropertiesUtil {
	/** 属性文件加载对象 */
	private static PropertiesLoader propertiesLoader;
	
	/** 定义的加密字符串 */
	public static final String PASSWORD = "jdbc.password";
	
	public static void setProperties(Properties p) {
		if (propertiesLoader == null){
			propertiesLoader = new PropertiesLoader(p);
		}
	}
	
	/** 获取String类型配置 */
	public static String getConfig(String key) {
		if (propertiesLoader == null){
			propertiesLoader = new PropertiesLoader("application.properties");
		}
		return propertiesLoader.getProperty(key);
	}
	
	/** 获取String类型配置(如果值为加密的，使用该方法) */
	public static String getDecryptConfig(String key) {
		if (propertiesLoader == null){
			propertiesLoader = new PropertiesLoader("application.properties");
		}
		String value = propertiesLoader.getProperty(key);
        if (value != null) {
        	//转为字节
        	byte[] b = Encode2Decode.decodeHex(value);
            //解密jdbc.password属性值，并重新设置 
            value = new String(Encode2Decode.decryptAES(b, PASSWORD));
        } 
		return value;
	}
	
	
	/** 获取boolean类型 */
	public static boolean getBoolean(String key) {
		if (propertiesLoader == null){
			propertiesLoader = new PropertiesLoader("application.properties");
		}
		return propertiesLoader.getBoolean(key);
	}
	
	/** 获取boolean类型,不存在key返回默认值 */
	public static boolean getBoolean(String key,boolean defaultValue) {
		if (propertiesLoader == null){
			propertiesLoader = new PropertiesLoader("application.properties");
		}
		return propertiesLoader.getBoolean(key,defaultValue);
	}
}
