package corejava.symbol;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-14
 * @Vesion 1.0
 **/
public class Car extends Maching {

    protected Car(String name, String fun) {
        super(name, fun);
    }

    public void createCar() {
        this.function();
        this.getDescrible();
    }
}
