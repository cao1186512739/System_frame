package com.opendev.response;

import com.opendev.constant.ResultCode;
import lombok.Data;

@Data
public class ResponseHelper<T> {

    public int code;

    public String msg;

    private T data;

    public ResponseHelper(int code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    public ResponseHelper(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseHelper Succeed() {
        return new ResponseHelper(200, "成功");
    }

    public static ResponseHelper SpeedFast() {
        return new ResponseHelper(402, "操作速度太快");
    }

    public static ResponseHelper Faild() {
        return new ResponseHelper(500, "服务器内部错误");
    }

    public static ResponseHelper Succeed(Object object) {
        return new ResponseHelper(200, "成功",object);
    }

    public static ResponseHelper Faild(ResultCode resultCode) {
        return new ResponseHelper(resultCode.getCode(), resultCode.getMsg(), null);
    }

}
