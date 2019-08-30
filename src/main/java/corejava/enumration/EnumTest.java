package corejava.enumration;

import org.junit.Test;


public class EnumTest {

	public static void main(String[] args) {
		System.out.println(OPER.ADD.toString());
	}
	
	public enum OPER {
		ADD("新增"), EDIT("修改"),DELETE("???"),SAVE("????"),USE("???");
		private String param;
		private OPER(String param) {
			this.param = param;
		}
		public String getParam() {
			return param;
		}
	}
	
	@Test
	public void printSome() {
		System.out.println("OPER.ADD: " + OPER.ADD);
		System.out.println("OPER.ADD.param: " + OPER.ADD.param);
		System.out.println("OPER.ADD.getParam(): " + OPER.ADD.getParam());
		System.out.println("OPER.ADD.name(): " + OPER.ADD.name());
		System.out.println("OPER.ADD.ordinal(): " + OPER.ADD.ordinal());
		System.out.println("OPER.EDIT.ordinal(): " + OPER.EDIT.ordinal());
		System.out.println("OPER.ADD.toString(): " + OPER.ADD.toString());
		
		/*OPER.ADD: ADD
		OPER.ADD.param: ????
		OPER.ADD.getParam(): ????
		OPER.ADD.name(): ADD
		OPER.ADD.ordinal(): 0
		OPER.EDIT.ordinal(): 1
		OPER.ADD.toString(): ADD*/
	}
	
	
	@Test
	public void formatter() {
		String type = "number";
		SchemaTypeEnum typeEnum = SchemaTypeEnum.fromValue(type);
	}
	
	/*public enum Gender {
		MALE("??"), FEMALE("?");
		public String name;
		
		private Gender(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}*/
	
	//Enumeration????????????????????
	public enum GenderImpl implements GenderDesc {
		MALE("??"), FEMALE("?");
		public String name;
		
		private GenderImpl(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}

		@Override
		public void info() {
			System.out.println("???????????"+this.name);
		}
	}
	
	// ??????????????????????
	/*public enum GenderImpl implements GenderDesc {
		MALE("??"){
			public void info() {
				System.out.println("MALE-?????????????????:"+this.name);
			}
		}, 
		FEMALE("??"){
			public void info() {
				System.out.println("FAMALE-??????????????????"+this.name);
			}
		};
		public String name;
		
		private GenderImpl(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}*/
	
	/*public enum Gender{
		PLUS {
			@Override
			public double eval(double x, double y) {
				return x+y;
			}
		},
		MIUS {
			@Override
			public double eval(double x, double y) {
				return x-y;
			}
		};
		
		public abstract double eval(double x, double y);
	}*/

}
