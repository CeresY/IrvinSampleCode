package corejava.symbol2;

import corejava.symbol.Maching;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-14
 * @Vesion 1.0
 **/
public class Tv extends Maching {
    protected Tv(String name, String fun) {
        super(name, fun);
    }

    public void test() {
        this.function();
        this.getDescrible();
    }
}
