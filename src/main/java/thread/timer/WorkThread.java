package thread.timer;

public class WorkThread extends Thread {
	
	public WorkThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(500);
				System.out.println("work ...");
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		WorkThread t1 = new WorkThread("work-T ");
		t1.start();
	}
}
