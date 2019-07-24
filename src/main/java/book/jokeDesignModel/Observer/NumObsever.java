package book.jokeDesignModel.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-24
 * @Vesion 1.0
 **/
public class NumObsever implements Observer {
    private String name;

    public NumObsever(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        NumObservable observable = (NumObservable) o;
        System.out.println("观察者: "+name+" 已接收到服务更新: " + observable.data);
    }
}
