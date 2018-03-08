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
		System.out.println("Construct 私有");
	}
	
	public ModelReflct(String name) {
		System.out.println("Construct 带1参数: " + name);
	}
	
	public ModelReflct(String str, Integer num) {
		System.out.println("Construct 带2参数");
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
		System.out.println("私有方法-1: " + str);
	}
	
	class InnerTest {
		
	}
	
	public static void main(String[] args) {
		Class<ModelReflct> clazz = ModelReflct.class;
		
		//全部构造器
		/*Constructor[] csc = clazz.getDeclaredConstructors();
		System.out.println("全部构造器:");
		for(Constructor c : csc) {
			System.out.println(c);
		}
		System.out.println("\n");*/
		
		//public构造器:
		/*Constructor[] cs = clazz.getConstructors();
		System.out.println("public构造器:");
		for(Constructor c : cs) {
			System.out.println(c);
		}
		System.out.println("\n");*/
		
		//public 方法
		/*Method[] ms = clazz.getMethods();
		System.out.println("public 方法:");
		for(Method m : ms) {
			System.out.println(m);
		}
		System.out.println("\n");*/
		
		//全部注释
		/*Annotation[] as = clazz.getAnnotations();
		System.out.println("全部注释:");
		for(Annotation m : as) {
			System.out.println(m);
		}
		System.out.println("\n");*/
	}
}
