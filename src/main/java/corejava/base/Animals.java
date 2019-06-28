package corejava.base;

import com.sun.xml.internal.fastinfoset.stax.factory.StAXOutputFactory;

public class Animals implements DangerousMonster {

	@Override
	public void menace() {
		System.out.println("Animals.menace");
	}

	@Override
	public void destory() {
		System.out.println("Animals.destory");
	}

	public DangerousMonster getDangerousMonster() {
		return new Animals();
	}

	public Animals getAnimals() {
		return new Animals();
	}
	
	public static void main(String[] args) {
		DangerousMonster f1 = null;
		DangerousMonster f11 = null;

		Animals f2 = null;
		Animals f22 = null;

		Animals foo = new Animals();
		f11 = foo.getDangerousMonster();
		f1 = foo.getAnimals();

		//f2 = foo.getDangerousMonster();  // 会报编译异常,原因实现类有很多个，这样写JVM也不知道是哪一个实现类，有点泛型 ? extends 和 ? super
		f22 = foo.getAnimals();

		Monster m = new Animals();
	}
}
