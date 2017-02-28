package main.gooleplay.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import main.gooleplay.R;

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
public class G {
    /**
     * 调试信息
     */
    public static boolean DEBUG = true;
    /**
     * toast提示
     */
    private static Toast toast;

    /**
     * 尺寸
     */
    public static final class size {
        /**
         * 屏幕宽
         */
        public static int W = 480;
        /**
         * 屏幕高
         */
        public static int H = 800;
    }

    /**
     * 截屏
     */
    public static void screenshots(Activity activity, boolean isFullScreen) {
        File mFileTemp = new File(activity.getCacheDir(), "screenshots.jpg");
        try {
            //View是你需要截图的View
            View decorView = activity.getWindow().getDecorView();
            decorView.setDrawingCacheEnabled(true);
            decorView.buildDrawingCache();
            Bitmap b1 = decorView.getDrawingCache();
            // 获取状态栏高度 /
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;
            // 获取屏幕长和高 Get screen width and height
            int width = activity.getWindowManager().getDefaultDisplay().getWidth();
            int height = activity.getWindowManager().getDefaultDisplay().getHeight();
            // 去掉标题栏 Remove the statusBar Height
            Bitmap bitmap;
            if (isFullScreen) {
                bitmap = Bitmap.createBitmap(b1, 0, 0, width, height);
            } else {
                bitmap = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
            }
            decorView.destroyDrawingCache();
            FileOutputStream out = new FileOutputStream(mFileTemp);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化屏幕尺寸
     */
    public static void initDisplaySize(Activity activity) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        G.size.W = mDisplayMetrics.widthPixels;
        G.size.H = mDisplayMetrics.heightPixels;
    }

    /**
     * 提示
     *
     * @param msg 提示信息
     */
    public static void showToast(Context context, String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, size.H / 4);
        toast.show();

    }

    /**
     * 记录调试信息
     *
     * @param msg 调试信息
     */
    public static void log(Object msg) {
        if (DEBUG) {
            Log.i("TAG", String.valueOf(msg));
        }
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, double dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmteny(String values) {
        if (null == values || "".equals(values) || "null".equals(values)) {
            return true;
        }
        return false;
    }

    /**
     * 判断网络是否连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 删除缓存
     */
    public static int clearCacheFolder(File dir, long numDays) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, numDays);
                    }
                    if (child.lastModified() < numDays) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }
    /**
     * 清除WebView缓存
     */
    public static void clearWebViewCache(Context context) {
        //清理Webview缓存数据库
        try {
            context.deleteDatabase("webview.db");
            context.deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WebView 缓存文件
        File appCacheDir = context.getDir("cache", Context.MODE_PRIVATE);
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            context.deleteFile(appCacheDir.getAbsolutePath());
        }
    }

    /**
     * 5.0以下版本的沉浸式顶栏
     * //取消状态栏背景 状态栏和toolbar重合  toolbar的背景改变状态栏背景也会改变  实现了动态改变状态栏颜色  也是与主流最为相似
     * // 缺点是 由于和toolbar重合 必须设置toolbar的高度 paddingTop 让其空出位置给状态栏  但是  由于baseActivyt是先加入toolbar 再加入布局
     * //这与会让布局背toolbar遮住 所以 但是不用这个baseActivity是可行的
     * //最终方案
     */
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 全透明实现
            //getWindow.setStatusBarColor(Color.TRANSPARENT)
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4 全透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    /**
     * 进入权限管理页面
     * @param context
     */
    public static void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }
    public static int[] getHotImages(){
        int[] iamges = new int[]{R.drawable.selector_hot1,R.drawable.selector_hot2,
                R.drawable.selector_hot3,R.drawable.selector_hot4,R.drawable.selector_hot5,R.drawable.selector_hot6,R.drawable.selector_hot7,
                R.drawable.selector_hot8,R.drawable.selector_hot9,R.drawable.selector_hot10};
        return iamges;
    }
    public static String[] getHotText(){
        String[] texts = new String[]{"QQ","视频","放开那三国","电子书","酒店","单机","小说","斗地主"
        ,"优酷","网游","WFFI万能钥匙","播放器","扑鱼达人2","机票","游戏","熊出没之大熊快跑","美图秀秀","浏览器"
        ,"单机游戏","我的世界","电影电视","QQ空间","旅游","免费游戏","2048","刀塔传奇","壁纸","节奏大师","锁屏"
        ,"装机必备","天天动听","备份","网盘","海淘网","大众点评","爱奇艺视频","腾讯手机管家","百度地图","猎豹清理大师"
        ,"谷歌地图","hao123上网导航","京东","youni有你","万年历-农历黄历","支付宝钱包"};
        return  texts;
    }

    /**  
      * 先判断是否安装，已安装则启动目标应用程序，否则先安装  
      * @param packageName 目标应用安装后的包名  
      * @param appPath 目标应用apk安装文件所在的路径  
      * @author zuolongsnail  
      */
    public static void launchApp(String packageName ,String appPath,Context context){
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
