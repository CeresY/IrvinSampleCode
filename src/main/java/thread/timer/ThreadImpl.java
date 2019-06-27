package thread.timer;

/**
 * @Description // 使用线程来实现定时任务
 * @Author yz
 * @Date 2019-6-27
 * @Vesion 1.0
 **/
public class ThreadImpl {
    public static void main(String[] args) {
        new Thread(()->{
            while (true) {
                System.out.println("hello");
                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello end");
            }
        }).start();

        System.out.println("main end");
    }
}
