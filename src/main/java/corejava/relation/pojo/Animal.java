package corejava.relation.pojo;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-26
 * @Vesion 1.0
 **/
public abstract class Animal {
    private String name;
    public Animal(String name) {
        this.name = name;
        System.out.println(this.getClass().getName()+"\tAnimal 构造");
    }

    public void describleName() {
        System.out.println(String.format(this.getClass().getName()+"\t%s", this.name));
    }

    public abstract void showInstance();
}
