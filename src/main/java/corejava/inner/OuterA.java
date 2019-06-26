package corejava.inner;

/**
 * @Description //
 * @Author yz
 * @Date 2019-6-26
 * @Vesion 1.0
 **/
public class OuterA {
    private String name;
    public int age;

    public static void main(String[] args) {
        OuterB foo = new OuterB();
        foo.f1();
        //OuterA.
    }

    public class InnerA {

    }

    private class InnerB {

    }
}

class OuterB{
    private String para1;
    public String para2;

    public void f1() {
        this.para1 = "para1-1";
        System.out.printf("para1 = ["+para1+"]");
    }

    public static void main(String[] args) {
        OuterB fb = new OuterB();
        OuterA fa = new OuterA();
    }
}

