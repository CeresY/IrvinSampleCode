package corejava.genericity;

import corejava.genericity.model.Apple;
import corejava.genericity.model.Fruit;
import corejava.genericity.model.ModelT;
import corejava.genericity.model.Orange;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.Arrays;
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
        foo.test3(String.class);
    }

    public <T> void test3(Class<T> clazz) {

        if(clazz == String.class) {
            System.out.println("String");
        } else if(clazz == Math.class) {
            System.out.println("Math");
        }


        else {
            System.out.println("other 0");
        }

        if(clazz.isInstance(new String())) {
            System.out.println("String 1");
        } else {
            System.out.println("other 1");
        }
    }

    public void takeAndPull() {
        List<? extends Fruit> fruits = new ArrayList<>();
        // fruits.add(new Apple()); // 编译无法通过
        // Apple apple = (Apple) fruits.get(0);

        List<? super Fruit> fruits2 = Arrays.asList(new Fruit(), new Apple(), new Orange());
        fruits2.stream().forEach(e -> System.out.println(((Object) e).getClass().getName()));
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
