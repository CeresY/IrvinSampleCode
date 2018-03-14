package corejava.character;

public class StringToAscii {
	
	public enum ColorEnum {
		//white, red, black;
		PULLDOWN("����"), PULLUP("����");
		private ColorEnum(String str){
			
		}
		//private static String Position = "TEST";
	} 
	
	public enum orderEnum{
		CANCEL{
			public String getName(){
				return "ȡ��";
			}
		};
		public abstract String getName();
	}
	
	public enum week{
		Mon, Wen, Thue;
	}
	
	public enum SeasonEnum {
        //ע��ö��д����ǰ�棬����������
        spring, summer, autumn, winter;

        private final static String position = "test";

        public static SeasonEnum getSeason() {
            if ("test".equals(position))
                return spring;
            else
                return winter;
        }
    }
	
	public static void main(String[] args) {
		StringToAscii fo = new StringToAscii();
		//System.out.println(orderEnum.CANCEL.getName());
		//System.out.println(week.valueOf("Mon"));
//		fo.testEnum1(orderEnum.CANCEL.getName());
		fo.testEnum1(orderEnum.CANCEL.name());
		System.out.println("����Ϊ" + SeasonEnum.getSeason());
		/*for(week obj : week.values()) {
			System.out.println(obj);
		}*/
		
	}
	
	public static void toUnicode() {
		String str = "����AB�� ";
		char[] ch = str.toCharArray();
		for(char cell : ch) {
			System.out.println((int)cell);
		}
	}

	public void testEnum(week arg) {
		System.out.println(arg);
	}
	
	public void testEnum1(String arg) {
		System.out.println(arg);
	}
}
