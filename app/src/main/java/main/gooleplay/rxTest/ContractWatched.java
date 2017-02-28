package main.gooleplay.rxTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghua on 2017/2/9.
 */
public class ContractWatched implements Watched{
    private List<Watcher> watcherList  =new ArrayList<>();
    @Override
    public void addWatcher(Watcher watcher) {
        watcherList.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
       watcherList.remove(watcher);
    }

    @Override
    public void notifceWatched(String string) {
     for (Watcher watcher:watcherList){
         watcher.update(string);
      }
    }
}
