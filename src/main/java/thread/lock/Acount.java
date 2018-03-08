package thread.lock;

public class Acount {
	private float total;
	private BusyFlag flag = new BusyFlag();
	
	public boolean deduct(float t) {
		boolean succeed = false;
		flag.getBusyflag();
		if(t<=total) {
			total -= t;
			succeed = true;
		}
		flag.freeBusyflag();
		return succeed;
	}
}
