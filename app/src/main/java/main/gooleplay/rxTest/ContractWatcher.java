package main.gooleplay.rxTest;

/**
 * Created by wanghua on 2017/2/9.
 */
public class ContractWatcher implements Watcher{
    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
