package corejava.symbol2;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-14
 * @Vesion 1.0
 **/
public class TvOther {
    public void test() {
        Tv tv = new Tv("电视机", "看它");
        tv.getDescrible();
        tv.test();
        // tv.function(); // 编译错误
    }

    public static void main(String[] args) {
        TvOther foo = new TvOther();
        foo.test();
    }
}
