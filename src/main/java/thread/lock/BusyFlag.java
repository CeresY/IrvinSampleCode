package thread.lock;

/**
 * @ȱ�� ��ΪBusyFlag�ڼ��flag�Ƿ�Ϊnull�����ǻ���ھ�̬������
 * @author st-yz2011 ԭʼ�汾1.0
 *
 */
public class BusyFlag {
	protected Thread busyflag = null;
	
	public void getBusyflag() {
		while(busyflag != Thread.currentThread()) {
			if(busyflag == null) {
				busyflag = Thread.currentThread();
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
}
