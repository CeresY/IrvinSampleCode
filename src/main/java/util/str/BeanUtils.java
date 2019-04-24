package util.str;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

/** 
 * 对象转换工具类,包括object,json对象,json字符串转换等
 */
public class BeanUtils {
	
	/** jackson单一实例 */
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	static {
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  
		//objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}
	
	/** map转object */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws Exception {    
        if (map == null) { 
            return null;  
        }
        T instance = clazz.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(instance, map);  
        return instance;  
    }    
    
	/** object转map */
    @SuppressWarnings("unchecked")
	public static Map<String,Object> objectToMap(Object obj) {  
        if(obj == null) { 
            return null;   
        }
        //FIXME 找不到哪个jar
        //return (Map<String,Object>)new BeanMap(obj);
        return null;
    }
    
    /** 
     * json字符串转换为Object对象(不支持泛型定义)
     * 
     * <p>(1)转换为JavaBean：toObject(jsonStr, Student.class)</p> 
     * <p>(2)转换为JavaBean数组：toObject(jsonStr, Student[].class)</p> 
     * <p>(3)因不支持泛型定义，可以用Arrays.asList()方法把得到的数组转换为List<Student></p> 
     */  
    public static <T> T jsonToObject(String jsonStr, Class<T> clazz) {  
    	try {  
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }  
      
    /** 
     * json字符串转换为Object对象(支持泛型定义)
     */  
    public static <T> T jsonToObject(String jsonStr, TypeReference<T> typeRef){  
        try {  
            return objectMapper.readValue(jsonStr, typeRef);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }
    
  
    /** 
     * Object对象转换为json字符串,object 可以是javaBean、数组、集合等
     */  
    public static String objectToJson(Object object) {  
        try {  
            return objectMapper.writeValueAsString(object);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }
    
    /**
	 * 对象转为字节(序列化)
	 * @param object 对象
	 */
    public static byte[] objectToByteArray(Object object) throws Exception {
		if (object == null) {
			return null;
		}
		byte[] bytes = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			bytes = baos.toByteArray();
		}
		catch (Exception ex) {
			throw new Exception("序列化对象失败");
		}
		return bytes;
	}

	/**
	 * 字节转为对象(反序列化)
	 * @param bytes 对象的字节数组
	 */
	public static Object ByteArrayToObject(byte[] bytes) throws Exception {
		if (bytes == null) {
			return null;
		}
		Object object = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			object = ois.readObject();
		}
		catch (Exception e) {
			throw new Exception("反序列化对象失败");
		}
		return object;
	}
    
	/** 格式化输出 */
	public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr))
			return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);
			switch (current) {
			case '{':
			case '[':
				sb.append(current);
				sb.append('\n');
				indent++;
				addIndentBlank(sb, indent);
				break;
			case '}':
			case ']':
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
				break;
			case ',':
				sb.append(current);
				if (last != '\\') {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}

		return sb.toString();
	}

	/** 添加space */
	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append('\t');
		}
	}
	
	
    public static void s(boolean... f){
    	System.out.println(f.length);
    }
    
    public static void main(String[] args) {
		s();
	}

}
