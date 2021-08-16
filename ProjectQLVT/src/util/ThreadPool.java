/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Mun Chan
 */
public class ThreadPool {
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private ThreadPool() {}

    private static class InnerExecutor{
        private static ThreadPool pool = new ThreadPool();
    }

    public static ThreadPool getInstance(){
        return InnerExecutor.pool;
    }

    public void initNewPool(){
        if(executorService != null) return;
        executorService = Executors.newFixedThreadPool(10);
    }

    public void submitTask(Task task){
        if(executorService == null) return;
        executorService.submit(task);
    }

    public void shutdownPool(){
        if(executorService == null) return;
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)){
                executorService.shutdownNow();
            }
        }catch(InterruptedException e){
            executorService.shutdownNow();
        }
    }
}
