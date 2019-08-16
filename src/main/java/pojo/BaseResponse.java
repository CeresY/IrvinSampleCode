package pojo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回基础类
 *
 * @author herryzhang
 * @date 2018-7-24 14:59:54
 */
@Getter
@Setter
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 返回结果
     */
    private Object data;

    private BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private BaseResponse(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    private BaseResponse(Integer code) {
        this.code = code;
    }

    public static BaseResponse error() {
        return error(ErrorCode.SC_INTERNAL_SERVER_ERROR.getCode(), ErrorCode.SC_INTERNAL_SERVER_ERROR.getMessage());
    }

    public static BaseResponse error(String message) {
        return error(ErrorCode.SC_INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public static BaseResponse error(Integer code, String message) {
        return new BaseResponse(code, message);
    }

    public static BaseResponse bad(Integer code, String message) {
        return new BaseResponse(code, message);
    }

    public static BaseResponse bad(String message) {
        return new BaseResponse(ErrorCode.BAD_REQ.getCode(), message);
    }

    public static BaseResponse bad() {
        return new BaseResponse(ErrorCode.BAD_REQ.getCode(), ErrorCode.BAD_REQ.getMessage());
    }


    public static BaseResponse success(String message) {
        return new BaseResponse(ErrorCode.OK.getCode(), message);
    }

    public static BaseResponse success(Object data) {
        return new BaseResponse(ErrorCode.OK.getCode(), data);
    }

    public static BaseResponse success() {
        return new BaseResponse(ErrorCode.OK.getCode());
    }

}
