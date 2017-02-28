package main.gooleplay.network;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;

import main.gooleplay.R;

/**
 * Created by wanghua on 2017/2/16.
 */
public class GildeImage {
    private static GlideBuilder builder;
    private static Glide glide;
    public GildeImage(Context context){
         glide = Glide.get(context);
         builder = new GlideBuilder(context);
        //获取系统分配给应用的总内存大小
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置图片内存缓存占用八分之一
        int memoryCacheSize = maxMemory / 8;
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        //设置图片解码格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置BitmapPool缓存内存大小
        builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));
        //最多可以缓存多少字节的数据
        int diskCacheSize = 1024 * 102 * 30;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide", diskCacheSize));
        Glide.setup(builder);
    }
    public static void showImage(Context context,String url, ImageView imageView){
        glide.with(context).load(url).
                placeholder(R.mipmap.ic_img_error).
                error(R.mipmap.ic_img_error).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(imageView);

    }
    public static void showGit(Context context,String url, ImageView imageView){
        glide.with(context).load(url).
                asGif().
                placeholder(R.mipmap.ic_img_error).
                error(R.mipmap.ic_img_error).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(imageView);

    }
  /*  public static Bitmap getBitmap(Context context, String url){
    }*/
}
