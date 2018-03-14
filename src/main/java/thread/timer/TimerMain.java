package thread.timer;

public class TimerMain {

	public static void main(String[] args) {
		WorkThread t1 = new WorkThread("Work-Thread ");
		t1.start();
		try {
			t1.join(1000*3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.interrupt();
	}

}
