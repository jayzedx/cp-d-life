package com.mdc.cpfit.util.listener;

public interface iThreadPoll {

    /**
     * Remove Task in Pool
     */
    public void RemoveAllTask();

    /**
     * Add Task To Pool
     * @param r Task
     */
    public void AddTask(Runnable r);

    /**
     * Add Task To Pool
     * Task will perform after specific timeout(Delay)
     * @param r
     * @param Timeout
     */
    public void AddTask(Runnable r, int Timeout);

    /**
     * Remove The Specific Task From Pool
     * @param r
     */
    public void RemoveTask(Runnable r);
}
