package util.other;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLDecoder;

/**
 * @author yz 
 * @version 1.0
 * @date 2016年5月13日
 * @describe 
 */
public class FormaterBean {
	/*public static void main(String[] args) {
		FormaterBean foo = new FormaterBean();
		LDProjectSet set = new LDProjectSet();
		set.setCountry("中国");
		try {
			LDProjectSet copy = foo.formater(set);
			System.out.println(copy.getCountry());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 把POJO的属性值(String类型)转换成UTF-8
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static <T>  T formater(T object) throws Exception{
		Field[] fields = object.getClass().getDeclaredFields();
		for(int i=0; i<fields.length; i++) {
			Field field = fields[i];
			Type type = field.getGenericType();
			if(type.toString().equals(String.class.toString())) {
				String fieldName = field.getName();
				String name = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1, fieldName.length());
				String methodName = "get" + name;
				Method method = object.getClass().getDeclaredMethod(methodName);
				String value = (String) method.invoke(object);
				if(value != null && !"".equals(value)) {
					object.getClass().getDeclaredMethod("set"+name, String.class)
					.invoke(object, new Object[]{URLDecoder.decode(value, "UTF-8")});
				} 
			}
		}
		return object;
	}
	
}
