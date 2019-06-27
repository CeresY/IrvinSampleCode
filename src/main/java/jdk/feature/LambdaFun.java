package jdk.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description //
 * @Author yz
 * @Date 2019-6-27
 * @Vesion 1.0
 **/
public class LambdaFun {
    public static void main(String[] args) {
        LambdaFun foo = new LambdaFun();
        foo.forEach();
    }
    public void forEach() {
        List<String> list = new ArrayList<>();
        Arrays.asList( "a", "b", "d" ).forEach(e -> {
            System.out.print(e + "\t");
            list.add(e);
        } );
        System.out.println("list.size="+list.size());
    }
}
