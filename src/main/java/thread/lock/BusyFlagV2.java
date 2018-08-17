package thread.lock;

/**
 * 这是BusyFlag的升级版，因为BusyFlag在检测flag是否为null，还是会存在竞态条件。
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
