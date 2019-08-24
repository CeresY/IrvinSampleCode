package guava;

import avro.shaded.com.google.common.cache.Cache;
import avro.shaded.com.google.common.cache.CacheBuilder;
import com.google.common.base.Strings;

import java.util.concurrent.TimeUnit;

/**
 * @Description // guava的缓存工具
 * @Author yz
 * @Date 2019-6-28
 * @Vesion 1.0
 **/
public class CacheBuilderFun {

    private static Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10)
            .expireAfterWrite(6, TimeUnit.SECONDS).build();

    public static void main(String[] args) {
        CacheBuilderFun foo = new CacheBuilderFun();
        foo.cacheBuilderFun();
        // foo.interruptSleep();
    }

    public void cacheBuilderFun() {
        for (int i=0; i<10; i++) {
            cache.put("k"+i, "v"+i);
        }

        while (!Thread.currentThread().isInterrupted()){
            String key = "k0";
            String value = cache.getIfPresent(key);
            if(Strings.isNullOrEmpty(value)) {
                Thread.currentThread().interrupt();
            }
            System.out.println(cache.getIfPresent(key));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void interruptSleep() {
        String key = "k1", value = "v1";
        cache.put(key,value);
        long star = System.currentTimeMillis();
        long end = 5 * 1000 + star;

        Thread t1 = new Thread(() -> {
            System.out.println("step run");
            try {
                Thread.sleep(8 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            System.out.println("end run");
        });
        t1.start();

        System.out.println("--- while ---");

        while (!Thread.currentThread().isInterrupted()) {
            String value_ = cache.getIfPresent(key);
            if (Strings.isNullOrEmpty(value_)) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread() + "\t" + this.getClass() + "\tend");
            } /*else if (System.currentTimeMillis() > end) {
                Thread.currentThread().interrupt();
//                t1.interrupt();
            }*/
        }

        System.out.println("... finished");
    }
}
