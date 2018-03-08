package thread.lock;
/**
 * @描述 这是BusyFlag的升级版，因为BusyFlag在检测flag是否为null，还是会存在竞态条件。
 * @缺陷 调用getBusyflag和freeBusyflag会出现死锁
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
