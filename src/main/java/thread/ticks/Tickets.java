package thread.ticks;


/**
 * ���߳���Ʊ����
 * @author yangzhan
 *
 */
public class Tickets implements Runnable{
	private volatile int tickets = 30;
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			synchronized(this) {
				notifyAll();
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
				/*
				 * ���otifyAll����������ô10���̻߳�ȫ������û���߳�֪ͨ�����̣߳����Լ����õ�����
				 * ���Ի����10���̴߳���10��Ʊ��Ȼ����� 
				 */
				//notifyAll(); 
				
				/*
				 * ��ִ����wait�󣬺���Ĵ���Ͳ�����ִ���ˡ�һֱҪ�ȵ��´��߳��õ��������ִ�С�
				 */
				//System.out.println(Thread.currentThread().getName()); 
			}
		}
	}
	
	public static void main(String[] args) {
		Tickets thread = new Tickets();
		for(int i=0; i<5; i++) {
			new Thread(thread).start();;
		}
	}
}
