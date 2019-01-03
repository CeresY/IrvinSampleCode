package corejava.construct;

public class SampleClass {
    // SampleClass 不能包含任何主类InitialOrderWithoutExtend的成员变量
    // 否则导致循环引用，循环初始化，调用栈深度过大
    // 抛出 StackOverFlow 异常
    // static InitialOrderWithoutExtend iniClass1 = new InitialOrderWithoutExtend("静态成员iniClass1初始化");
    // InitialOrderWithoutExtend iniClass2 = new InitialOrderWithoutExtend("普通成员成员iniClass2初始化");

    String s;

    SampleClass(String s) {
        this.s = s;
        System.out.println(s);
    }

    SampleClass() {
        System.out.println("SampleClass默认构造函数被调用");
    }

    @Override
    public String toString() {
        return this.s;
    }
}
