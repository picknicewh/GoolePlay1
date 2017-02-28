package main.gooleplay.rxTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by wanghua on 2017/2/10.
 */
public class DownloadUtil<T> {
    private static OkHttpClient okHttpClient;

    public DownloadUtil() {
        okHttpClient = new OkHttpClient();
    }

    public static Observable<byte[]> getObservable(final String path) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(path).build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                if (data != null) {
                                    subscriber.onNext(data);
                                }
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });
    }
    public  Observable<String> sendPost(final String url, final Map<String,String> params){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                FormBody.Builder builder = new  FormBody.Builder();
               if (params!=null&&!params.isEmpty()){
                   for (Map.Entry<String,String> entrys:params.entrySet()){
                       builder.add(entrys.getKey(),entrys.getValue());
                   }
               }
                RequestBody requestBody = builder.build();
                final Request request = new  Request.Builder().url(url).post(requestBody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Gson gson = new Gson();
                        Type type  = new TypeToken<T>(){}.getType();
                        String json=  gson.fromJson(response.body().string(),type);
                        subscriber.onNext(json);
                    }
                });
            }
        });
    }
}
