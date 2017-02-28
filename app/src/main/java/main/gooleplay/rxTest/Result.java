package main.gooleplay.rxTest;

/**
 * Created by wanghua on 2017/2/10.
 */
public class Result<T> {
    private T data;
    private String code;
    private String sign;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
