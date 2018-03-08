package corejava.enumration;

import org.junit.Test;

/**
 * 如何使用枚举
 * @author yangzhan-xps13
 * @date 2017年4月25日-上午9:35:24
 */
public class EnumExp {
	public enum FontColor {
		BLACK((short)11), BLUE((short)22), RED((short)33);
		private short index;
		private FontColor(short index){
			this.index = index;
		}
	}
	
	public enum DATESCOPE {
		DAY7;
	}
	
	public enum WEEK {
		SUN, FRI;
	}
	
	@Test
	public void testFontColor() {
		/*System.out.println(FontColor.BLACK);
		System.out.println(FontColor.BLACK.name());
		System.out.println(FontColor.BLACK.ordinal());
		System.out.println(FontColor.BLACK.index);
		System.out.println(FontColor.BLACK.toString());*/
		System.out.println(DATESCOPE.DAY7);
		System.out.println(DATESCOPE.DAY7);
		System.out.println(DATESCOPE.DAY7.name());
		System.out.println(DATESCOPE.DAY7.toString());
		System.out.println(DATESCOPE.DAY7.ordinal());
	}
	
	public enum Color {  
	    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);  
	    // 成员变量  
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private Color(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    /*public static String getName(int index) {  
	        for (Color c : Color.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    } */ 
	}  
	
	@Test
	public void printElement() {
		System.out.println(FontColor.BLACK.index);
		//System.out.println(Color.GREEN.index);
	}
}
