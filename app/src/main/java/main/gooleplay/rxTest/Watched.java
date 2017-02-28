package main.gooleplay.rxTest;

/**
 * Created by wanghua on 2017/2/9.
 */
public interface Watched {
    void addWatcher(Watcher watcher);
    void removeWatcher(Watcher watcher);
    void notifceWatched(String string);
}
