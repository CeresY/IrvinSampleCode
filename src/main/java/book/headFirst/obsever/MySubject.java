package book.headFirst.obsever;

public class MySubject extends AbsSubject {

	@Override
	public void operator() {
		System.out.println("update operator...");
		super.notifyObserver();
	}

	public static void main(String[] args) {
		Subject sub = new MySubject();
		sub.add(new Observer1());
		sub.add(new Observer2());
		sub.operator();
	}

	@Override
	public void method1() {
		
	}
}
