package corejava.reflect.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GenericConstruct {
	public static void main(String[] args) {
		/*new Foo("泛型参数String");
		new Foo(100);
		//也可以显示指定泛型参数类型
		new <Date>Foo(new Date());
		new <Integer>Foo(10);*/
		
//		new <Integer>Foo2<String>(100);
		
		List<Number> dest = new ArrayList<Number>();
		List<Integer> src = new ArrayList<Integer>();
		src.add(1);
		GenericConstruct foo	 = new GenericConstruct();
		//Integer i = foo.copy(dest, src);//编译引起错误，丢失了src的精度
		Integer i = foo.copySuper(dest, src);//正确编译
		System.out.println(i);
	}
	
	public <T> T copy(Collection<T> dest, Collection<? extends T> src) {
		T last = null;
		for(T t : src) {
			last = t;
			dest.add(t);
		}
		return last;
	}
	
	public <T> T copySuper(Collection<? super T> dest, Collection<T> src) {
		T last = null;
		for(T t : src) {
			last = t;
			dest.add(t);
		}
		return last;
	}
}

class Foo {
	//<T>表示声明参数类型
	public <T> Foo(T t) {
		System.out.println(t);
	}
}

class Foo2<E> {
	//<T>表示声明参数类型
	public <T> Foo2(T t) {
		System.out.println(t);
	}
}