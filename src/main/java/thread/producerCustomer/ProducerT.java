package thread.producerCustomer;

import java.util.Queue;
import java.util.Random;

public class ProducerT extends Thread {
	
	private Queue<Integer> queue;
	private int maxSize;
	
	public ProducerT(Queue<Integer> queue, int maxSize, String threadName) {
		super(threadName);
		this.queue = queue;
		this.maxSize = maxSize;
	}



	@Override
	public void run() {
		while(true) {
			synchronized(queue) {
				while(queue.size() == maxSize) {
					try {
						System.out.println("Queue is full, Producer thread waiting for, consumer to take something from queue");
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int i = new Random().nextInt();
				queue.add(i);
				System.out.println("Producing value : " + i);
				queue.notifyAll();
			}
		}
	}

}
