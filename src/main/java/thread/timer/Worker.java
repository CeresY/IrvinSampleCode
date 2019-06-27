package thread.timer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description //
 * @Author yz
 * @Date 2019-6-27
 * @Vesion 1.0
 **/
public class Worker extends Thread{
    BlockingQueue<String> q;

    Worker(BlockingQueue<String> q) {
        this.q = q;
    }

    /*public void run() {
        long start = System.currentTimeMillis();
        long end = start + 2; //just wait for two milliseconds

        while (true) {
            String x = q.poll();
            if(x != null) {
                System.out.println(x + " from " + this.getName());
            } else if(System.currentTimeMillis() >= end){
                System.out.println("No new message since two milliseconds, killing thread " + this.getName());
                interrupt();
            }

            if(interrupted()) {
                break;
            }
        }
    }*/

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String x = q.poll(2, TimeUnit.SECONDS);
                if (x == null)
                    System.out.println("No new message since two seconds, killing thread " + this.getName());
                Thread.currentThread().interrupt();
                System.out.println(x + "\tfrom\t" + this.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
