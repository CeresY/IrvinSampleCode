package thread;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DaemonThread t = new DaemonThread("", 3);
		t.process();
	}

}
