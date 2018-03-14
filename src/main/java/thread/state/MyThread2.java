package thread.state;
/**
 * ����һ�������ʾ������Ҫģ��
 * @author yangzhan
 *
 */
public class MyThread2 implements Runnable {
	@Override
	public void run()
	{
		Thread currThread = Thread.currentThread();
		synchronized (currThread)
		{
			while ("t1".equals(currThread.getName()))
			{
				System.out.println("thread2");
				try
				{
					currThread.wait(0);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			done();
		}
	}

	public synchronized void done()
	{
		System.out.println("�������");
	}
}
