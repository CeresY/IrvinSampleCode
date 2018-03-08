package thread;

public class LogicT {
	public static int swap(int i) {
		int temp = 0;
		temp = i;
		try {
			Thread.currentThread().sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return temp;
	}
}
