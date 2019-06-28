package guava;

import avro.shaded.com.google.common.cache.Cache;
import avro.shaded.com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Description // guava的缓存工具
 * @Author yz
 * @Date 2019-6-28
 * @Vesion 1.0
 **/
public class CacheBuilderFun {
    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10).expireAfterWrite(6, TimeUnit.SECONDS).build();
        for (int i=0; i<10; i++) {
            cache.put("k"+i, "v"+i);
        }

        while (true){
            System.out.println(cache.getIfPresent("k0"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
