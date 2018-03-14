package thread;


/**
 * ��ʧ�ܰ�����
 * ���߳���Ʊ����
 * @author yangzhan
 *
 */
public class SortTickets implements Runnable{
	private volatile int tickets = 40;
	private static int LEN;
	static Thread[] threads;
	volatile int target;
	
	public SortTickets(int i) {
		LEN = i;
		threads = new Thread[LEN];
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			synchronized(this) {
				//notifyAll();
				if(target == target%LEN + 1) {
					if(tickets < 1 ) {
						Thread.currentThread().interrupt();
					} else {
						System.out.println(Thread.currentThread().getName() + "������" + tickets-- + "��Ʊ");
						try {
							wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				else {
					target++;
					notifyAll();
				}
			}//end synchronized
		}//end while
	}
	
	public static void main(String[] args) {
		SortTickets thread = new SortTickets(6);
		for(int i=0; i<LEN; i++) {
			threads[i] = new Thread(thread);
			threads[i].start();;
		}
	}
}
