package main.gooleplay.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;

import java.io.File;
import java.util.List;

/**
 * Created by wanghua on 2017/2/15.
 */
public class LaunchAppUtil {
    /**
     *  判断是否安装或覆盖安装的类型
     */
    private static final int NOTINSTALL = 0;// 未安装  
    private static final int INSTALLED = 1;// 已安装且为新版本  
    private static final int OLDVERSION = 2;// // 已安装但为旧版本 
    /** 
     *  * 判断应用是否安装或者是否为最新版本 
     *  * @param packageName 目标应用安装后的包名 
     *  * @param versionCode 指定的应用版本号 
     *  * @return 安装的类型 
     *  * @author zuolongsnail  
     *  
     */
    private int isInstallByread(String packageName, int versionCode, Context context) {
        // 判断是否安装  
        if (new File("/data/data/" + packageName).exists()) {
            // 获取系统中安装的所有应用包名集合  
            List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packages.size(); i++) {
                PackageInfo packageInfo = packages.get(i);
                // 找出指定的应用  
                if (packageName.equals(packageInfo.packageName)) {
                    if (packageInfo.versionCode >= versionCode) {
                        return INSTALLED;
                    } else {
                        return OLDVERSION;
                    }
                } else {
                    return NOTINSTALL;
                }
            }
        }
        return -1;
    }
    /**
     *   
     *  * 判断是否安装目标应用  
     *  * @param packageName 目标应用安装后的包名  
     *  * @return 是否已安装目标应用  
     *  * @author zuolongsnail  
     *  
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
    /**  
      * 先判断是否安装，已安装则启动目标应用程序，否则先安装  
      * @param packageName 目标应用安装后的包名  
      * @param appPath 目标应用apk安装文件所在的路径  
      * @author zuolongsnail  
      */
    private void launchApp(String packageName ,String appPath,Context context){
        boolean isinstall = new File("/data/data/" + packageName).exists();
         // 启动目标应用   
        if(isinstall){
            // 获取目标应用安装包的Intent    
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
            }else{
            // 安装目标应用   
            Intent intent = new Intent();
            intent.setDataAndType(Uri.fromFile(new  File(appPath)), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }
}
