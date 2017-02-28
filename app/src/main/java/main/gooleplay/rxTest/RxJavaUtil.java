package main.gooleplay.rxTest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import main.gooleplay.bean.User;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wanghua on 2017/2/9.
 */
public class RxJavaUtil {
    public static   String TAG ="RxJavaUtil";
    public static void createObservable(){
        //创建被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()){
                    subscriber.onNext("1");
                    subscriber.onNext("2");
                    subscriber.onNext("3");
                    subscriber.onNext("4");
                    subscriber.onCompleted();
                }
            }
        });
        //创建观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"====onCompleted====");
            }
            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"====onError====");
            }
            @Override
            public void onNext(String s) {
                Log.i(TAG,"====onNext===="+s);
            }
        };
        //订阅
        observable.subscribe(subscriber);
    }
    public static void createPrint(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()){
                    for (int i = 1;i<10;i++){
                        subscriber.onNext(i);
                    }
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"====onCompleted====");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"====onError====");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG,"====onNext===="+integer);
            }
        });
    }
    /**
     * 一般用于被观察者，返回的数据类型都是数值类型
     */
    public static void  createFrom(){
        Integer[] data = {1,3,5,7,8,9};
        Observable<Integer> observable = Observable.from(data);
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG,"====onNext===="+integer);
            }
        });
    }
    public static void intraval(){
        Observable observable = Observable.interval(1,1, TimeUnit.SECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG,"====onNext===="+o.toString());
            }
        });
    }
    public static void just(){
        Integer[] data = {1,3,5,7,8,9};
        Integer[] data1 = {2,4,6,8,2,2};
        Observable<Integer[]> observable = Observable.just(data,data1);
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer[] integers) {
             for (int i = 0 ;i <integers.length;i++){
                 Log.i(TAG,"====onNext===="+integers[i]);
             }
            }
        });
        List<User> user1 = new ArrayList<>();
        List<User> user2 = new ArrayList<>();
        Observable<List<User>> observable2 = Observable.just(user1,user2);
        observable2.subscribe(new Subscriber<List<User>>() {
            public void onCompleted() {
                Log.i(TAG,"====onCompleted====");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"====onError====");
            }

            @Override
            public void onNext(List<User> users) {

            }
        });
    }
    /**
     * 指定输出数据的范围
     */
    public static void range(){
        //range从1到20计算
        Observable  observable = Observable.range(1,20);
        observable.subscribe(new Subscriber() {
            public void onCompleted() {
                Log.i(TAG,"====onCompleted====");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"====onError====");
            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG,"====onNext===="+o.toString());
            }
        });
    }
    public static void  filter(){
        Observable<Integer> observable = Observable.just(1,2,4,5,6,7);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer>5;
            }
        });
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG,"====onNext===="+integer);
            }
        });
    }

}
