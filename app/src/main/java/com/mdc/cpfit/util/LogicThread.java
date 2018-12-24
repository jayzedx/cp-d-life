package com.mdc.cpfit.util;

import android.app.Activity;


import com.mdc.cpfit.util.listener.iThreadPoll;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogicThread implements iThreadPoll
{

    private Activity MainActivity;
    private final ThreadPoolExecutor ThreadPool;
    private final ScheduledThreadPoolExecutor ThreadPoolScheduler;

    public LogicThread(ActivityUnit MainActivity){
        if( MainActivity == null){
            throw new NullPointerException("Main Activity Cannot Be Null Pointer");
        }
        this.MainActivity = MainActivity;

        int CoreSize = MainActivity.getTotalCpuCore();
        int MaxCoreSize = CoreSize * 2;
        int TotalQueueSize = (int) (MainActivity.getTotalMemory() / 10);
        ArrayBlockingQueue<Runnable> Queue = new ArrayBlockingQueue<Runnable>(TotalQueueSize > 500 ? 500 : TotalQueueSize);
        this.ThreadPool = new ThreadPoolExecutor(CoreSize,MaxCoreSize,30, TimeUnit.SECONDS,Queue);
        this.ThreadPool.allowCoreThreadTimeOut(true);
        this.ThreadPoolScheduler = new ScheduledThreadPoolExecutor(MaxCoreSize);
    }

    @Override
    public void RemoveAllTask() {
        this.ThreadPool.purge();
        this.ThreadPoolScheduler.purge();
    }

    @Override
    public void AddTask(Runnable r) {
        if( r != null ){
            if( !this.ThreadPool.isTerminated() && !this.ThreadPool.isTerminated() ){
                this.ThreadPool.execute(r);
            }
        }
    }

    @Override
    public void AddTask(Runnable r, int Timeout) {
        if( r != null ){
            if( !this.ThreadPoolScheduler.isTerminated() && !this.ThreadPoolScheduler.isTerminated() ){
                this.ThreadPoolScheduler.schedule(r,Timeout,TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public void RemoveTask(Runnable r) {
        if( r != null ){
            this.ThreadPool.remove(r);
            this.ThreadPoolScheduler.remove(r);
        }
    }
}
