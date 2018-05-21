package com.timec.tag.app.test;

import com.timec.tag.app.dao.TagsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * Created by timec on 2018-05-09.
 */

@Component
@Scope("prototype")
public class WorkerThread implements Callable<String> {
    @Autowired
    TagsDao tagsDao;

    private String appGroupCode;
    private long request;
    private String category;
    private String word;

    public WorkerThread() {
    }

    public WorkerThread(String appGroupCode, String category, String word, long request){
        this.appGroupCode = appGroupCode;
        this.category = category;
        this.word = word;
        this.request = request;
    }

    @Override
    public String call() throws Exception {
//        for(long i = 2050000 ; i < 10000000 ; i++){
        for(long i = 1 ; i < 1000000 ; i++){
            tagsDao.insertTags(appGroupCode, category, i+word, String.valueOf(i));
        }
//
//        for(long i = 1 ; i < 1000000 ; i++){
//            tagsDao.insertRecommendation(appGroupCode, category, word+i);
//        }

        return doWork();
    }

    private String doWork(){
        return "";
    }


}
