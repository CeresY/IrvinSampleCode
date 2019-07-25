package corejava.genericity.model;

import org.apache.poi.ss.formula.functions.T;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-26
 * @Vesion 1.0
 **/
public class ModelT<T> {
    private T obj;

    public ModelT(T obj) {
        this.obj = obj;
    }

    public void print() {
        System.out.println("T类型 " + this.obj.getClass().getName());
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
