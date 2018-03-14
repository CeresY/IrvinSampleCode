package book.headFirst.obsever;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbsSubject implements Subject {

	private Vector<Observer> vector = new Vector<>();
	
	@Override
	public void add(Observer obj) {
		vector.add(obj);
	}

	@Override
	public void del(Observer obj) {
		vector.remove(obj);
	}

	@Override
	public void notifyObserver() {
		Enumeration<Observer> en = vector.elements();
		while(en.hasMoreElements()) {
			en.nextElement().update();
		}
	}

	public abstract void method1();

}
