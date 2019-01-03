package corejava.enumration;

import org.junit.Test;


public class EnumTest {
	
	public enum OPER {
		ADD("新增"), EDIT("修改"),DELETE("删除"),SAVE("保存"),USE("使用");
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
		
		// 打印结果
		/*OPER.ADD: ADD
		OPER.ADD.param: 新增
		OPER.ADD.getParam(): 新增
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
		MALE("男"), FEMALE("女");
		public String name;
		
		private Gender(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}*/
	
	//Enumeration也可以像普通类一样实现接口
	public enum GenderImpl implements GenderDesc {
		MALE("男"), FEMALE("女");
		public String name;
		
		private GenderImpl(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}

		@Override
		public void info() {
			System.out.println("初始化时选择了"+this.name);
		}
	}
	
	//每个枚举值去实现不一样的接口方法
	/*public enum GenderImpl implements GenderDesc {
		MALE("男"){
			public void info() {
				System.out.println("MALE-每个枚举值不一样的实现："+this.name);
			}
		}, 
		FEMALE("女"){
			public void info() {
				System.out.println("FAMALE-每个枚举值不一样的实现："+this.name);
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
	
	//含有抽象方法的枚举
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
		
		//定义抽象方法
		public abstract double eval(double x, double y);
	}*/
	
	public static void main(String[] args) {
//		System.out.println(Gender.MALE.getName());
		
		/*Gender g = Enum.valueOf(Gender.class, "MALE");
		g.name = "男";
		System.out.println(g.name);*/
		
//		System.out.println(Enum.valueOf(Gender.class, "MALE"));
		
//		System.out.println(Gender.FEMALE);
		
//		System.out.println(GenderImpl.FEMALE.getName());
//		GenderImpl.FEMALE.info();
		System.out.println(GenderImpl.FEMALE);
		System.out.println(GenderImpl.FEMALE.getName());
		System.out.println(1111111);
		
//		System.out.println(Gender.MIUS.eval(1, 9));
	}

}
