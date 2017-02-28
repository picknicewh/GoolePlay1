package main.gooleplay.rxTest;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wanghua on 2017/2/9.
 */
public class SimpleObserver implements Observer {


    public void addObserver(SimpleObserable obserable){
        obserable.addObserver(this);
    }
    public void removeObserver(SimpleObserable obserable){
        obserable.deleteObserver(this);
    }
    @Override
    public void update(Observable observable, Object o) {
     System.out.println("data has changed:" +((SimpleObserable)observable).getData());
    }
}
