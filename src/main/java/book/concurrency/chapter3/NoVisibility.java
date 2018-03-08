package book.concurrency.chapter3;

public class NoVisibility {
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while(!ready) {
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<1000; i++) {
			new ReaderThread().start();
			ready = true;
			number = 42;
		}
	}
}
