package guava;

import pojo.Man;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-24
 * @Vesion 1.0
 **/
public class GuavaCachDemoTest {
    public static void main(String[] args){
        GuavaCachDemo cachDemo = new GuavaCachDemo();
        System.out.println("(0)使用loadingCache");
        cachDemo.InitLoadingCache();

        System.out.println("(1)使用loadingCache get方法  第一次加载");
        Man man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);

        System.out.println("\n(2)使用loadingCache getIfPresent方法  第一次加载");
        man = cachDemo.getIfPresentloadingCache("002");
        System.out.println(man);

        System.out.println("\n(3)使用loadingCache get方法  第一次加载");
        man = cachDemo.getCacheKeyloadingCache("002");
        System.out.println(man);

        System.out.println("\n(4)使用loadingCache get方法  已加载过");
        man = cachDemo.getCacheKeyloadingCache("002");
        System.out.println(man);

        System.out.println("\n(5)使用loadingCache get方法  已加载过,但是已经被剔除掉,验证重新加载");
        man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);

        System.out.println("\n(6)使用loadingCache getIfPresent方法  已加载过");
        man = cachDemo.getIfPresentloadingCache("001");
        System.out.println(man);

        System.out.println("\n(7)使用loadingCache put方法  再次get");
        Man newMan = new Man();
        newMan.setId("001");
        newMan.setName("额外添加");
        cachDemo.putloadingCache("001",newMan);
        man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);
    }
}
