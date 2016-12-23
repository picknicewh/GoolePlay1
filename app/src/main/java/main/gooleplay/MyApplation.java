package main.gooleplay;

import android.app.Application;

import main.gooleplay.util.BaseLibrary;

/**
 * Created by wanghua on 2016/12/22.
 */
public class MyApplation extends Application {
    private static MyApplation instance;
    public static MyApplation getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
         instance = this ;
        BaseLibrary.initializer(this);
    }
}
