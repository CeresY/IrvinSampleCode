package thread;

public class YieldTest extends Thread{

	public static void main(String[] args) {
		for(int i=0; i<3; i++) {
			new YieldTest().start();
		}
	}

	@Override
	public void run() {
		synchronized(this) {
			System.out.println(Thread.currentThread().getName() + "\t" + 1);
			yield();
			System.out.println(Thread.currentThread().getName() + "\t" + 2);
		}
	}
	
	

}
