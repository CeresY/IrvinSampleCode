package corejava.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ModelReflct {
	
	private String p1;
	public int p2;
	
	@Override
	public String toString() {
		return "ModelReflct[p1:" + p1 + ", p2:" + p2 + "]";
	}

	private ModelReflct() {
		System.out.println("Construct ˽��");
	}
	
	public ModelReflct(String name) {
		System.out.println("Construct ��1����: " + name);
	}
	
	public ModelReflct(String str, Integer num) {
		System.out.println("Construct ��2����");
	}
	
	public void info(){
		System.out.println("info-0");
	};
	public void info(String str){
		System.out.println("info-1: " + str);
	};
	public void info(String str, Integer num){
		System.out.println("info-2");
	};
	
	private void privacy(String str){
		System.out.println("˽�з���-1: " + str);
	}
	
	class InnerTest {
		
	}
	
	public static void main(String[] args) {
		Class<ModelReflct> clazz = ModelReflct.class;
		
		//ȫ��������
		/*Constructor[] csc = clazz.getDeclaredConstructors();
		System.out.println("ȫ��������:");
		for(Constructor c : csc) {
			System.out.println(c);
		}
		System.out.println("\n");*/
		
		//public������:
		/*Constructor[] cs = clazz.getConstructors();
		System.out.println("public������:");
		for(Constructor c : cs) {
			System.out.println(c);
		}
		System.out.println("\n");*/
		
		//public ����
		/*Method[] ms = clazz.getMethods();
		System.out.println("public ����:");
		for(Method m : ms) {
			System.out.println(m);
		}
		System.out.println("\n");*/
		
		//ȫ��ע��
		/*Annotation[] as = clazz.getAnnotations();
		System.out.println("ȫ��ע��:");
		for(Annotation m : as) {
			System.out.println(m);
		}
		System.out.println("\n");*/
	}
}
