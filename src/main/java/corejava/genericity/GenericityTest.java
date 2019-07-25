package corejava.genericity;

import corejava.genericity.model.Apple;
import corejava.genericity.model.Fruit;
import corejava.genericity.model.ModelT;
import corejava.genericity.model.Orange;

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
        foo.test2();
    }

    public void take(ModelT<?> model) {
        model.print();
    }
    public void test2() {
        ModelT<Apple> apple = new ModelT<>(new Apple());
        ModelT<Orange> orange = new ModelT<>(new Orange());
        ModelT<Fruit> fruit = new ModelT<>(new Fruit());
        take(apple);
        take(orange);
        take(fruit);
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
