package pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-24
 * @Vesion 1.0
 **/
@Setter
@Getter
public class Man {
    //身份证号
    private String id;
    //姓名
    private String name;

    public Man(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Man() {
    }

    @Override
    public String toString() {
        return "Man{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
