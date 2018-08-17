package thread.lock;

/**
 * @缺陷 因为BusyFlag在检测flag是否为null，还是会存在竞态条件。
 * @author st-yz2011 原始版本1.0
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
