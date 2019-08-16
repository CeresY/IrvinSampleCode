package pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-31
 * @Vesion 1.0
 **/
@Getter
@Setter
public class Result {
    private Integer code;

    private String message;

    @JsonBackReference
    private String token;
}
