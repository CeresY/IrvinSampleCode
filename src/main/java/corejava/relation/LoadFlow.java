package corejava.relation;

import corejava.relation.pojo.Dog;

/**
 * @Description // 加载顺序
 * @Author yz
 * @Date 2019-7-26
 * @Vesion 1.0
 **/
public class LoadFlow {
    public static void main(String[] args) {
        Dog dog = new Dog("wangwang");
        dog.showInstance();
    }
}
