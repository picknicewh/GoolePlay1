package main.gooleplay.network;

import java.io.Serializable;

/**
 * ================================================
 * 作    者：ZLL
 * 时    间：2016/7/19
 * 描    述：服务端返回基本数据格式
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public class Result<T> implements Serializable {
    private T data;      //返回数据
    private int enable_ak_ad;
    private  boolean is_new;
    private int time;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean is_new() {
        return is_new;
    }

    public void setIs_new(boolean is_new) {
        this.is_new = is_new;
    }

    public int getEnable_ak_ad() {
        return enable_ak_ad;
    }

    public void setEnable_ak_ad(int enable_ak_ad) {
        this.enable_ak_ad = enable_ak_ad;
    }
}
