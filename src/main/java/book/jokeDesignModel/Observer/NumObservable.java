package book.jokeDesignModel.Observer;

import java.util.Observable;

/**
 * @Description // 参考：https://www.cnblogs.com/xudong-bupt/p/3428108.html
 * @Author yz
 * @Date 2019-7-24
 * @Vesion 1.0
 **/
public class NumObservable extends Observable {
    String data;

    public void setData(String data) {
        this.data = data;
        super.setChanged();
        notifyObservers();
    }

    public static void main(String[] args) {
        NumObservable observable = new NumObservable();

        NumObsever server1 = new NumObsever("一号");
        NumObsever server2 = new NumObsever("二号");

        observable.addObserver(server1);
        observable.addObserver(server2);
        observable.setData("中午休息");
        observable.deleteObserver(server1);
        observable.setData("下班了");
    }
}
