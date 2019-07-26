package corejava.relation.pojo;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-26
 * @Vesion 1.0
 **/
public class Dog extends Animal {
    private String show;
    public Dog(String name) {
        super(name);
        this.show = "小狗";
    }

    @Override
    public void showInstance() {
        super.describleName();
        System.out.println(String.format(this.getClass().getName()+"\t实例:%s", this.show));
    }

    public static void main(String[] args) {
        Dog dog = new Dog("wangwang");
        dog.showInstance();
    }
}
