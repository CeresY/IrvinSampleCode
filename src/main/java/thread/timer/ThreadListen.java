package thread.timer;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description // 监听队列里每个元素N秒，然后自己销毁 from：https://cloud.tencent.com/developer/ask/170838
 * @Author yz
 * @Date 2019-6-27
 * @Vesion 1.0
 **/
public class ThreadListen {
    public static void main(String[] args) {
        int capacity = 10;
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(capacity);

        ArrayList<String> names = new ArrayList<String>();
        names.add("T1"); names.add("T2"); names.add("T3");
        for (String name: names) {
            Worker a_worker = new Worker(queue);
            a_worker.setName(name);
            a_worker.start();
        }
        queue.add("Hello");
        queue.add("Bonjour");
    }
}
