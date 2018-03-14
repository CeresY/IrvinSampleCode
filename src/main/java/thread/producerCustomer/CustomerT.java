package thread.producerCustomer;

import java.util.Queue;

public class CustomerT extends Thread{

	private Queue<Integer> queue;
	private int maxSize;
	
	public CustomerT(Queue<Integer> queue, int maxSize, String threadName) {
		super(threadName);
		this.queue = queue;
		this.maxSize = maxSize;
	}



	@Override
	public void run() {
		while(true) {
			synchronized(queue) {
				while(queue.isEmpty()) {
					System.out.println("Queue is empty, Consumer thread is waiting, for producer thread to put something in queue");
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				//����
				System.out.println(Thread.currentThread().getName() + "value : " + queue.remove());
				queue.notifyAll();
			}
		}
	}

}
