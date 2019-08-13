package corejava.symbol;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-14
 * @Vesion 1.0
 **/
public class Maching {

    private String name;

    private String fun;

    /*public Maching() {
    }*/

    protected Maching(String name, String fun) {
        this.name = name;
        this.fun = fun;
    }

    protected void function() {
        System.out.println("保护方法：" + this.fun);
    }

    public void getDescrible() {
        System.out.println("什么描述:"+this.name);
    }
}
