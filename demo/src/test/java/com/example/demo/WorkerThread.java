package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * Created by timec on 2018-05-09.
 */

@Component
@Scope("test")
public class WorkerThread implements Callable<String> {

    private String request;

    public WorkerThread(String request){
        this.request = request;
    }

    @Override
    public String call() throws Exception {
        return doWork();
    }

    private String doWork(){
        return "";
    }


}
