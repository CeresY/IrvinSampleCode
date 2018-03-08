package corejava.reflect.generic;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

public class CreateGeneric {
	
	private String p1;
	private int p2;
	public Date p3;
	public Map<String, Integer> p4;
	//public T p5;
	
	public static void main(String[] args) {
		//����һά����
		/*String[] strs = arrayInstance(String.class, 10);
		strs[0] = "java����";
		strs[5] = "��������";
		System.out.println(strs[0]);
		System.out.println(strs[3]);*/
		
		//������ά����
		/*int[][] is = arrayInstance(int[].class, 5);
		is[1] = new int[]{1,11, 111, 1111};
		is[4] = new int[]{5,55, 555};
		System.out.println(is[1][3]);
		System.out.println(is[4][2]);*/
		CreateGeneric foo = new CreateGeneric();
		foo.getFieldType();
	}
	
	public CreateGeneric() {
		
	}

	/**
	 * ��������
	 * @param <T> ��������
	 * @param componentType ��������
	 * @param length ���鳤��
	 * @return
	 */
	@SuppressWarnings(value = { "unchecked" })
	public static <T>T[] arrayInstance(Class<T> componentType, int length) {
		return (T[])Array.newInstance(componentType, length);
	}
	
	/**
	 * ��ȡField��������
	 */
	public void getFieldType() {
		try{
			Field f3 = CreateGeneric.class.getDeclaredField("p3");
			Class<?> f3t = f3.getType();
			System.out.println("f3t: " + f3t + "\n");
			
			Field f1 = CreateGeneric.class.getDeclaredField("p1");
			//Class<?> f1t = f1.getType();
			Type f1t = f1.getGenericType();
			System.out.println("f1t: " + f1t + "\n");
			
			//��Ϊp4�Ƿ���Field�����Բ�����getType();
			Field f4 = CreateGeneric.class.getDeclaredField("p4");
			Type f4t = f4.getGenericType();
			//Class<?> f4t = f4.getType();
			if(f4t instanceof ParameterizedType) {//���ͻ�����
				ParameterizedType pType = (ParameterizedType) f4t;
				System.out.println("f4t: " + f4t + "\n");
				//��ȡԭʼ����
				Type t1 = pType.getRawType();
				System.out.println("ԭʼ����pType.getRawType: " + t1 + "\n");
				//���������
				Type[] t2 = pType.getActualTypeArguments();
				System.out.println("�����������" );
				for(Type t : t2) {
					System.out.println(t);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

}
