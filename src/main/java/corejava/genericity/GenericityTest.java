package corejava.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-17
 * @Vesion 1.0
 **/
public class GenericityTest {
    public static void main(String[] args) {
        GenericityTest foo = new GenericityTest();
        foo.test1();
    }

    public void test1() {
        List<? super Fruit> list = new ArrayList<>();
        list.add(new Apple());
        list.add(new Orange());

        Object f = list.get(0);
        if(f instanceof Orange) {
            System.out.println("orange");
        } else {
            System.out.println("apple");
        }
    }
}
