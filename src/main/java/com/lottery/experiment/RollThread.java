package com.lottery.experiment;

import com.lottery.callbacks.*;
/**
 * Created by Andy-Super on 2019/3/26.
 */
public class RollThread extends Thread{
    private ThreadCallback threadFn;
    public RollThread(ThreadCallback fn){
        threadFn = fn;
    }
    @Override
    public void run(){
        if(threadFn != null)threadFn.entries();
    }

}