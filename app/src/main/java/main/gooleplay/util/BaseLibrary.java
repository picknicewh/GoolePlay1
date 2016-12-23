package main.gooleplay.util;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * 作    者： ZLL
 * 时    间： 2016/7/8
 * 描    述：
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public class BaseLibrary {
    /**
     * 整体activity同时销毁的
     */
    private static List<Activity> activitys = null;
    /**
     * 部分activity同时销毁的
     */
    private static List<Activity> activityPartList= null;
    /**
     * 部分activity同时销毁的
     */
    public static List<Activity> activityLockList= null;
    private static Application instance;
    public static void initializer(Application application){
      //  OkHttpUtils.init(application);
        activitys=new ArrayList<>();
        instance=application;
    }
    public static Application getInstance() {
        return instance;
    }
    // 添加Activity到容器中
    public static void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if(!activitys.contains(activity)){
                activitys.add(activity);

            }
        }else{
            activitys.add(activity);
        }
    }
    public static  void addLockActivity(Activity activity){
        if (activityLockList!=null){
            activityLockList.add(activity);
        }

    }
    private  static  int count=0;
    public static  void removeLockActivity(){
        if (activityLockList != null && activityLockList.size() > 0) {
            for (Activity activity : activityLockList) {
                activity.finish();
            }
        }
    }
    public static  void removeLocFirstkActivity(){
        if (activityLockList != null && activityLockList.size() > 0) {
            activityLockList.remove(0);
        }
    }
    // 遍历所有Activity并finish
    public static void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
        System.exit(0);
    }


    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
