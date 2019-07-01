package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TextExecutor {
    public ExecutorService fixedExecutorService = Executors.newFixedThreadPool(5);
    public ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
    public ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();

    public void testExecutorException() {
        for (int i = 0; i < 10; i ++) {
            if (!fixedExecutorService.isShutdown()) {
                fixedExecutorService.execute(new SayHelloRunnable());
            }
            fixedExecutorService.shutdown();
        }
    }

    private class SayHelloRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("hello world!");
            }

        }
    }

    public static void main(String[] args) {
        TextExecutor testExecutor = new TextExecutor();
        testExecutor.testExecutorException();
    }
}
