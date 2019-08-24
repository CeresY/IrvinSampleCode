package guava;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import pojo.Man;

import java.util.concurrent.ExecutionException;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-24
 * @Vesion 1.0
 **/
@Slf4j(topic = "guavaCacheDome")
public class GuavaCachDemo {
    private LoadingCache<String, Man> loadingCache;

    //loadingCache
    public void InitLoadingCache() {
        //指定一个如果数据不存在获取数据的方法
        CacheLoader<String, Man> cacheLoader = new CacheLoader<String, Man>() {
            @Override
            public Man load(String key) throws Exception {
                //模拟mysql操作
                log.info("LoadingCache测试 从mysql加载缓存ing...(2s)");
                Thread.sleep(2000);
                log.info("LoadingCache测试 从mysql加载缓存成功");
                Man tmpman = new Man();
                tmpman.setId(key);
                tmpman.setName("其他人");
                if (key.equals("001")) {
                    tmpman.setName("张三");
                    return tmpman;
                }
                if (key.equals("002")) {
                    tmpman.setName("李四");
                    return tmpman;
                }
                return tmpman;
            }
        };
        //缓存数量为1，为了展示缓存删除效果
        loadingCache = CacheBuilder.newBuilder().maximumSize(1).build(cacheLoader);
    }
    //获取数据，如果不存在返回null
    public Man getIfPresentloadingCache(String key){
        return loadingCache.getIfPresent(key);
    }
    //获取数据，如果数据不存在则通过cacheLoader获取数据，缓存并返回
    public Man getCacheKeyloadingCache(String key){
        try {
            return loadingCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    //直接向缓存put数据
    public void putloadingCache(String key,Man value){
        log.info("put key :{} value : {}",key,value.getName());
        loadingCache.put(key,value);
    }
}
