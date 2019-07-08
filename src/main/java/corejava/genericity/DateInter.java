package corejava.genericity;

import java.util.Date;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-4
 * @Vesion 1.0
 **/
public class DateInter extends Pair<Date> {
    /*@Override
    public Date getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Date value) {
        super.setValue(value);
    }*/

    public static void main(String[] args) {
        DateInter foo = new DateInter();
        //foo.setValue(new Date());
        //foo.setValue(new Object()); // 编译错误
    }
}
