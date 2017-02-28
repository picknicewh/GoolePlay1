package main.gooleplay.rxTest;

/**
 * Created by wanghua on 2017/2/9.
 */
public class Test {
    public static void main(String[] agrs) {
        Watcher watcher = new ContractWatcher();
        Watched watched = new ContractWatched();
        watched.addWatcher(watcher);
        watched.notifceWatched("sssssss");
        SimpleObserable observable = new SimpleObserable();
        SimpleObserver observer = new SimpleObserver();
        observer.addObserver(observable);
        observable.setData(1);
        observable.setData(2);
        observable.setData(2);
        observable.setData(3);
    }
}
