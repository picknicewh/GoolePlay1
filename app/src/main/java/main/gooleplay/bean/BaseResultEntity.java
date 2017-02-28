package main.gooleplay.bean;

/**
 * Created by wanghua on 2017/2/15.
 */
public class BaseResultEntity<T> {
    private T data;
    private String code;
    private String sign;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
