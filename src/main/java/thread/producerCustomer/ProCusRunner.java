package thread.producerCustomer;

import java.util.LinkedList;
import java.util.Queue;

public class ProCusRunner {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int maxSize = 10;
		
		ProducerT p = new ProducerT(queue, maxSize, "Producer: ");
		CustomerT c1 = new CustomerT(queue, maxSize, "Customer-T1: ");
		CustomerT c2 = new CustomerT(queue, maxSize, "Customer-T2: ");
		p.start();
		c1.start();
		c2.start();
	}
}
