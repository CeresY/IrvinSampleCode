package corejava.enumration;

import org.junit.Test;


public class EnumTest {
	
	public enum OPER {
		ADD("add"), EDIT("edit"),DELETE("delete"),SAVE("save"),USE("use");
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
		
		// ��ӡ���
		/*OPER.ADD: ADD
		OPER.ADD.param: add
		OPER.ADD.getParam(): add
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
		MALE("��"), FEMALE("Ů");
		public String name;
		
		private Gender(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}*/
	
	//EnumerationҲ��������ͨ��һ��ʵ�ֽӿ�
	public enum GenderImpl implements GenderDesc {
		MALE("��"), FEMALE("Ů");
		public String name;
		
		private GenderImpl(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}

		@Override
		public void info() {
			System.out.println("��ʼ��ʱѡ����"+this.name);
		}
	}
	
	//ÿ��ö��ֵȥʵ�ֲ�һ���Ľӿڷ���
	/*public enum GenderImpl implements GenderDesc {
		MALE("��"){
			public void info() {
				System.out.println("MALE-ÿ��ö��ֵ��һ����ʵ�֣�"+this.name);
			}
		}, 
		FEMALE("Ů"){
			public void info() {
				System.out.println("FAMALE-ÿ��ö��ֵ��һ����ʵ�֣�"+this.name);
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
	
	//���г��󷽷���ö��
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
		
		//������󷽷�
		public abstract double eval(double x, double y);
	}*/
	
	public static void main(String[] args) {
//		System.out.println(Gender.MALE.getName());
		
		/*Gender g = Enum.valueOf(Gender.class, "MALE");
		g.name = "��";
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
