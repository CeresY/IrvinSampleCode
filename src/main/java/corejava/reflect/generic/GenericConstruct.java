package corejava.reflect.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GenericConstruct {
	public static void main(String[] args) {
		/*new Foo("���Ͳ���String");
		new Foo(100);
		//Ҳ������ʾָ�����Ͳ�������
		new <Date>Foo(new Date());
		new <Integer>Foo(10);*/
		
//		new <Integer>Foo2<String>(100);
		
		List<Number> dest = new ArrayList<Number>();
		List<Integer> src = new ArrayList<Integer>();
		src.add(1);
		GenericConstruct foo	 = new GenericConstruct();
		//Integer i = foo.copy(dest, src);//����������󣬶�ʧ��src�ľ���
		Integer i = foo.copySuper(dest, src);//��ȷ����
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
	//<T>��ʾ������������
	public <T> Foo(T t) {
		System.out.println(t);
	}
}

class Foo2<E> {
	//<T>��ʾ������������
	public <T> Foo2(T t) {
		System.out.println(t);
	}
}