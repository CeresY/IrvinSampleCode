package corejava.genericity;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-4
 * @Vesion 1.0
 **/
public class Pair<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
