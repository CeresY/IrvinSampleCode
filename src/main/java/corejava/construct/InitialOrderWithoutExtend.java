package corejava.construct;

public class InitialOrderWithoutExtend {
    static SampleClass sam = new SampleClass("(1) 静态成员sam初始化");

    SampleClass sam1 = new SampleClass("(6) 普通成员sam1初始化");

    static {
        System.out.println("(3) static块执行");
        if (sam == null)
            System.out.println("sam is null");
        sam = new SampleClass("(4) 静态块内初始化sam成员变量");
    }

    SampleClass sam2 = new SampleClass("(7) 普通成员sam2初始化");

    InitialOrderWithoutExtend() {
        System.out.println("(8)(10) InitialOrderWithoutExtend默认构造函数被调用");
    }

    public static void main(String[] args) {
        // 创建第1个主类对象
        System.out.println("(5) 第1个主类对象：");
        InitialOrderWithoutExtend ts = new InitialOrderWithoutExtend();

        // 创建第2个主类对象
        System.out.println("(9) 第2个主类对象：");
        InitialOrderWithoutExtend ts2 = new InitialOrderWithoutExtend();

        // 查看两个主类对象的静态成员：
        System.out.println("(11) 2个主类对象的静态对象：");
        System.out.println("第1个主类对象, 静态成员sam.s: " + ts.sam);
        System.out.println("第2个主类对象, 静态成员sam.s: " + ts2.sam);
    }
}
