package corejava.control.p0;

import util.log.LogService;

import java.util.List;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-27
 * @Vesion 1.0
 **/
public class Bclass extends Aclass {

    public static void main(String[] args) {
        Bclass fo = new Bclass();
        System.out.println("开始");
        fo.printB();
    }

    @Override
    public List<String> getList() {
        LogService.print("初始化: Bclass.getList");
        return super.getList("元素A");
    }

    public void printB() {
        LogService.print("执行 Bclass.printB");
        super.print();
    }

}
