package util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import securityThink.Encode2Decode;

/** 重写PropertyPlaceholderConfigurer的processProperties方法实现
  * @author yangzhan
  * 2018年4月13日
  */
public class PropertyPlaceholderConfigurerExt extends PropertyPlaceholderConfigurer{
	@Override 
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) 
                    throws BeansException { 
            String password = props.getProperty("jdbc.password"); 
            if (password != null) {
            	//转为字节
            	byte[] b = Encode2Decode.decodeHex(password);
                //解密jdbc.password属性值，并重新设置 
                props.setProperty("jdbc.password", new String(Encode2Decode.decryptAES(b, PropertiesUtil.PASSWORD))); 
            } 
            String user = props.getProperty("jdbc.user"); 
            if (user != null) {
            	//转为字节
            	byte[] b = Encode2Decode.decodeHex(user);
                //解密jdbc.password属性值，并重新设置 
                props.setProperty("jdbc.user", new String(Encode2Decode.decryptAES(b, PropertiesUtil.PASSWORD))); 
            } 
            super.processProperties(beanFactory, props);
            PropertiesUtil.setProperties(props);

    } 
    
    public static void main(String[] args) {
    	String x2 = System.getProperties().getProperty("os.name");
    	System.out.println(x2);
    	byte[] r = Encode2Decode.encryptAES("htsd", "jdbc.password");
    	String x = Encode2Decode.encodeHex(r);
    	System.out.println(x);
    	byte[] x1 = Encode2Decode.decryptAES(r, "jdbc.password");
    	System.out.println(new String(x1));
    	
    	System.out.println("a1234567".matches("[A-Za-z0-9]{8,20}"));
	}
}
