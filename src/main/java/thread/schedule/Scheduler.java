package thread.schedule;

public class Scheduler {
	public static void main(String[] args) {
		final OutPutStr o = new OutPutStr();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                while(true){
                    o.out3("111111111111");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                while(true){
                    o.out3("222222222222");
                }
            }
        }).start();
	}
}
