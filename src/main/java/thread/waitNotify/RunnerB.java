package thread.waitNotify;

import java.util.Random;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-2
 * @Vesion 1.0
 **/
public class RunnerB {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        System.out.println("start ...");
        Thread t = new Thread(() -> {
            System.out.println("running ...");
            try {
                int r = new Random().nextInt(20);
                System.out.println("r=["+r+"]");
                Thread.sleep(r*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /*try {
            //
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("start thread");
        t.start();
        Thread.yield();
        System.out.println("end main");
    }
}
