package corejava.genericity;

import java.util.Date;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-4
 * @Vesion 1.0
 **/
public class ObjectChild extends Pair{
    @Override
    public Object getValue() {
        return super.getValue();
    }

    public void setValue(Date value) {
        super.setValue(value);
    }
}
