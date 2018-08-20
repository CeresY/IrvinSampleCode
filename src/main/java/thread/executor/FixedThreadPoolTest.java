package thread.executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest extends Thread {
    public int ThreadId;

    public FixedThreadPoolTest(int ThreadId) {
        this.ThreadId = ThreadId;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println("pool" + ThreadId + ":" + i);
        super.run();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            FixedThreadPoolTest th = new FixedThreadPoolTest(i);
            exec.execute(th);
        }
        exec.shutdown();
    }
}