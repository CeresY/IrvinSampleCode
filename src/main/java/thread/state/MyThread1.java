package thread.state;

public class MyThread1 implements Runnable {

	public int i = 10;

	@Override
	public void run()
	{
		Thread currThread = Thread.currentThread();
		synchronized (currThread)
		{
			++i;
			System.out.println(this.getClass().getName() + " i = " + i);
			currThread.notify();
		}
	}
}
