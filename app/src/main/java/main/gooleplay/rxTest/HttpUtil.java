package main.gooleplay.rxTest;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.https.HttpsUtils;
import com.lzy.okhttputils.interceptor.LoggerInterceptor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import main.gooleplay.MyApplation;
import main.gooleplay.util.G;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanghua on 2017/2/10.
 */
public class HttpUtil {
    private static final String baseUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting";
    /**
     * 第一步创建okHttpClient
     */
    private static OkHttpClient getHttpClient() {
        okhttp3.OkHttpClient.Builder    builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.writeTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(5, TimeUnit.SECONDS);
        builder.cache(new Cache(MyApplation.getInstance().getExternalCacheDir(), 10 * 1024 * 1024));
        builder.addInterceptor(new LoggerInterceptor("HttpUtil"));
        builder.addNetworkInterceptor(getInterceptor());
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return builder.build();
    }
    /**
     * 第二步创建Retrofit
     */
    private static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        return retrofit;
    }
    /**
     * 第三步获取定义接口类
     */
    private static ApiRequest getApiRequest() {
        Retrofit retrofit = getRetrofit();
        ApiRequest request = retrofit.create(ApiRequest.class);
        return request;
    }
    /**
     * 第四步设置观察者的处理结果
     * @param url 地址
     * @param type 类型
     * @param  listener 回调函数
     */
    public static Subscriber<ResponseBody> getSubscriber(final String url, final Type type, final HttpListener listener) {
        return new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {}
            @Override
            public void onError(Throwable e) {
                listener.onError(url, (String) new Gson().fromJson(e.getMessage(),new TypeToken<main.gooleplay.network.Result<String>>(){}.getType()));
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    Log.e("HttpUtil", "content :" + responseBody.string());
                    listener.onSuccess(url,  new Gson().fromJson(responseBody.toString(),type));
                } catch (IOException e) {
                    listener.onSuccess(url, new Gson().fromJson(e.getMessage(),new TypeToken<main.gooleplay.network.Result<String>>(){}.getType()));
                    e.printStackTrace();
                }
            }
        };
    }
    /**
     * 下载
     * @param url 下载的地址
     * @param type 类型
     * @param  listener 回调监听
     */
    public static void download(String url,Type type,HttpListener listener){
        Observable<ResponseBody> o = getApiRequest().downloadFile(url);
        o.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).
                subscribe(getSubscriber(url, type,listener));
    }
    /**
     * post请求获取数据
     * @param params   请求的参数列表
     * @param url      接口的url地址
     * @param listener 监听回调
     */
    public static void sendPost(Map<String, Object> params,Type type, final String url, final HttpListener listener) {
        ApiRequest request = getApiRequest();
        Observable<ResponseBody> observable = request.sendPost(url,params);
        observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).
                subscribe(getSubscriber(url,type, listener));

    }

    /**
     * post请求获取数据
     * @param params   请求的参数列表
     * @param url      接口的url地址
     * @param listener 监听回调
     */
    public static void sendGet(Map<String, Object> params,Type type, final String url, final HttpListener listener) {
        Retrofit retrofit = getRetrofit();
        ApiRequest request = retrofit.create(ApiRequest.class);
        Observable<ResponseBody> observable = request.sendGet(url,params);
        observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).
                subscribe(getSubscriber(url, type,listener));
    }
    /**
     * 上传
     * @param files 文件图片
     * @param url 接口url地址
     * @param listener 回调函数
     */
    public static void upLoadSingleFile(File[] files,Type type,String url,HttpListener listener) {
        for (int i = 0 ; i<files.length;i++) {
            File file = files[i];
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part p = MultipartBody.Part.createFormData("image",file.getName(),requestBody);
            Observable<ResponseBody> o = getApiRequest().uploadSingleFile(url,p);
            o.subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    unsubscribeOn(Schedulers.io()).
                    subscribe(getSubscriber(url,type,listener));
        }
    }
    /**
     * 上传图文给服务器
     * @param  url 接口的url地址
     * @param  files 上传的文件
     * @param params 文字参数
     * @param  listener 回调函数
     */
    public static void upLoadMulFile(String url,File[] files,Type type,Map<String,Object> params,HttpListener listener) {
        for (int i = 0 ; i<files.length;i++){
            File file = files[i];
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("pic",file.getName(),requestBody);
            Observable<ResponseBody> observable =  getApiRequest().sendFilePost(url,part,params);
            observable.subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    unsubscribeOn(Schedulers.io()).subscribe(getSubscriber(url,type,listener));

        }
    }

    private static Interceptor getInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String cacheControl = request.cacheControl().toString();
                if (G.isEmteny(cacheControl)){
                    cacheControl =  "public max-age = 60";
                }
                Response response = chain.proceed(request);
                return response.newBuilder().
                        addHeader("Cache-Control",cacheControl).
                        removeHeader("Pragma").
                        build();
            }
        };
        return interceptor;
    }

    private  static Interceptor getCacheInterceptor(final Context context){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response  response = null;
                Request request = chain.request();
                if (!G.isNetworkConnected(context)){//没有网络就之读取缓存
                    request =  request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                if (G.isNetworkConnected(context)){
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    response =  response.newBuilder().
                            addHeader("Cache-Control",cacheControl).
                            removeHeader("Pragma").build();
                }else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    response = response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale="+maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        return interceptor;
    }
    private static final String TAG="tag";
}
