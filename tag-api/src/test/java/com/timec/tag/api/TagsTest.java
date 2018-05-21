package com.timec.tag.api;

import com.timec.tag.api.dao.TagApiDao;
import com.timec.tag.domain.Recommendation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by timec on 2018-05-09.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TagApiApplication.class)
public class TagsTest {
    @Autowired
    TagApiDao tagApiDao;

    @Test
    public void autoCompleteSearching() throws Exception{
        Recommendation obj = new Recommendation();
        obj.setAppGroupCode("rkKR");
        obj.setCategory("bbs");
        obj.setWord("skqkfe");

        long m1 = System.currentTimeMillis();
        for(int i = 0 ; i < 1000 ; i++){
            tagApiDao.autoCompleteSearching(obj);
        }
        System.out.println(System.currentTimeMillis() - m1);
//        for(String ele : list){
//            System.out.println(ele);
//        }
    }
}
