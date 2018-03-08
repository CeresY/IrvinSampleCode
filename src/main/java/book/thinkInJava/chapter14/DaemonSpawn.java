package book.thinkInJava.chapter14;

public class DaemonSpawn extends Thread {

	public DaemonSpawn(int i) {
		System.out.println("DaemonSpawn " + i + " started");
		start();
	}

	@Override
	public void run() {
		while(true) {
			yield();
		}
	}

}
