package thread.lock;

/**
 * ����BusyFlag�������棬��ΪBusyFlag�ڼ��flag�Ƿ�Ϊnull�����ǻ���ھ�̬������
 * @author st-yz2011
 *
 */
public class BusyFlagV2 {
	protected Thread busyflag = null;
	
	public void getBusyflag() {
		while(tryGetBusyflag() == false) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized boolean tryGetBusyflag() {
		if(busyflag == null) {
			busyflag = Thread.currentThread();
			return true;
		}
		return false;
	}
	
	public synchronized void freeBusyflag() {
		if(busyflag == Thread.currentThread()) {
			busyflag = null;
		}
	}
}
