package thread.lock;
/**
 * @���� ����BusyFlag�������棬��ΪBusyFlag�ڼ��flag�Ƿ�Ϊnull�����ǻ���ھ�̬������
 * @ȱ�� ����getBusyflag��freeBusyflag���������
 * @author st-yz2011
 *
 */
public class BusyFlagV1_1 {
	protected Thread busyflag = null;
	
	public static void main(String[] args) {
		BusyFlagV1_1 f = new BusyFlagV1_1();
		f.getBusyflag();
		for(int i=0; i<10; i++) {
			System.out.println(i);
		}
		f.freeBusyflag();
		System.out.println("end");
	}
	
	public synchronized void getBusyflag() {
		while(true) {
			if(busyflag == null) {
				busyflag = Thread.currentThread();
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void freeBusyflag() {
		if(busyflag == Thread.currentThread()) {
			busyflag = null;
		}
	}
	
	public void num() {
		
	}
}
