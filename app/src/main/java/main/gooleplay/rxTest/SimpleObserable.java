package main.gooleplay.rxTest;

import java.util.Observable;

/**
 * Created by wanghua on 2017/2/9.
 */
public class SimpleObserable extends Observable {
    private int data=0;
    public void setData(int data){
        if (this.data!=data){
            this.data = data;
            setChanged();
            notifyObservers();
        }
    }
    public int getData(){
        return  this.data;
    }
}
