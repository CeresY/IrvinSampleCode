package thread.state;

import java.util.concurrent.CountDownLatch;

public class MyNewTest {
	
	private CountDownLatch controller;
	
	public static void main(String[] args)
	{
		MyThread1 th1 = new MyThread1();
		MyThread2 th2 = new MyThread2();
		Thread t1 = new Thread(th1);
		t1.setName("t1");
		Thread t2 = new Thread(th2);
		t2.setName("t2");
		t1.start();
		t2.start();
	}
}
