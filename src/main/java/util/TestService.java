package util;

public class TestService {
    public void test1() {
        System.out.println("aa");
    }

    public void test2() {
        test1();
    }
    public void test3() {
        test1();
        //无数业务操作后,再次电影test1()方法
        test1();
    }
}
