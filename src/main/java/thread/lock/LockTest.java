package thread.lock;

public class LockTest {
	public static void main(String[] args) {
		LockTest f = new LockTest();
		try {
			f.math();
		}catch(Exception e) {
			System.out.println("main: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
	public void math() {
		for(int i=0; i<10; i++) {
			if(i == 4) {
				throw new IllegalArgumentException("参数不正确");
			}
		}
	}
}
