package ots.layim.bean;

import java.io.Serializable;

public class ResultModel<T> implements Serializable {

    // 状态码
    private Integer code ;

    // 信息
    private String msg;

    // 返回数据
    private T data;

    public ResultModel() {
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
