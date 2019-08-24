package guava;

import pojo.Man;

import java.util.concurrent.ExecutionException;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-24
 * @Vesion 1.0
 **/
public class GuavaCachDemo2Test {
    public static void main(String[] args) throws Exception {
        GuavaCachDemo2Test foo = new GuavaCachDemo2Test();
        foo.testCache();
    }

    public void testCache() throws ExecutionException {
        GuavaCachDemo2 cachDemo = new GuavaCachDemo2();
        cachDemo.InitCache();
        cachDemo.InitCacheRemovalListener();

        // Man man = cachDemo.getCacheKeyCache("001");
        // System.out.println(man);

        cachDemo.putCache("003", new Man("003", "三号"));
        cachDemo.putCache("004", new Man("004", "四号"));
        System.out.println(cachDemo.getIfPresentCache("003"));
        System.out.println(cachDemo.getCacheKeyCache("003"));
    }

    public void test1() {
        GuavaCachDemo2 cachDemo = new GuavaCachDemo2();
        cachDemo.InitCache();
        cachDemo.InitCacheRemovalListener();
        cachDemo.InitLoadingCache();
        Man man = null;

        System.out.println("\n\n1.使用Cache");
        System.out.println("2.使用Cache get方法  第一次加载");
        try {
            man = cachDemo.getCacheKeyCache("001");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(man);

        System.out.println("\n3.使用Cache getIfPresent方法  第一次加载");
        man = cachDemo.getIfPresentCache("002");
        System.out.println(man);

        System.out.println("\n4.使用Cache get方法  第一次加载");
        try {
            man = cachDemo.getCacheKeyCache("002");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(man);

        System.out.println("\n5.使用Cache get方法  已加载过");
        try {
            man = cachDemo.getCacheKeyCache("002");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(man);

        System.out.println("\n6.使用Cache get方法  已加载过,但是已经被剔除掉,验证重新加载");
        try {
            man = cachDemo.getCacheKeyCache("001");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(man);

        System.out.println("\n7.使用Cache getIfPresent方法  已加载过");
        man = cachDemo.getIfPresentCache("001");
        System.out.println(man);

        System.out.println("\n8.使用Cache put方法  再次get");
        Man newMan1 = new Man();
        newMan1.setId("001");
        newMan1.setName("额外添加");
        cachDemo.putloadingCache("001",newMan1);
        man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);
    }
}
