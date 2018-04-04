package web.http;

import org.junit.Test;

import okhttp3.internal.Util;

/**
  * @author yangzhan
  * 2018年4月3日
  */
public class OkhttpTest {
	
	@Test
	public void checkChar() {
		String name = "filter";
		String value = "project: VBI #sprint45_VBI5.0 and 优先级: 马上处理";
		for (int i = 0, length = value.length(); i < length; i++) {
	        char c = value.charAt(i);
	        if ((c <= '\u001f' && c != '\u0009') || c >= '\u007f') {
	          String val = Util.format("Unexpected char %#04x at %d in %s value: %s", (int) c, i, name, value);
	          System.out.println("Val="+val);
	        } else {
	        	System.out.println("C="+c);
	        }
	      }
	}
}
