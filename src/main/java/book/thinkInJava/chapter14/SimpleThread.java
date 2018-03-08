package book.thinkInJava.chapter14;

public class SimpleThread extends Thread {
	private int countDown = 4;
	private int threadNumber;
	private static int threadCount = 5;
	
	public SimpleThread() {
		threadNumber = ++threadCount;
		System.out.println("Making " + threadNumber);
		start();
	}

	@Override
	public void run() {
		while(true) {
			System.out.println("Thread " + threadNumber + "(" + countDown + ")");
			if(--countDown == 0) {
				return ;
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<3; i++) {
			//new SimpleThread().start();
			new SimpleThread();
		}
		System.out.println("All thread Started.");
	}
	
}
