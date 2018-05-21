import com.timec.tag.app.TagAppApplication;
import com.timec.tag.app.dao.TagsDao;
import com.timec.tag.app.test.WorkerThread;
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
@SpringBootTest(classes = TagAppApplication.class)
public class TagsTest {
    @Autowired
    TagsDao tagsDao;

    @Autowired
    private ApplicationContext context;

    @Test
    public void generateTag() throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        List<WorkerThread> tasks = new ArrayList<>(100);

//        String appGroupCode, String category, String word, long request
//        for(int i = 0; i < 100 ; i++){
//            WorkerThread wt = context.getBean(WorkerThread.class, "rkKR", "bbs", "일본도");
//            tasks.add(wt);
//        }
        WorkerThread wt1 = context.getBean(WorkerThread.class, "rkKR", "bbs", "쿠사나기검", 1);
        WorkerThread wt2 = context.getBean(WorkerThread.class, "rkKR", "bbs", "커트의검", 1);
        WorkerThread wt3 = context.getBean(WorkerThread.class, "rkKR", "bbs", "나이트발드의검", 1);
        WorkerThread wt4 = context.getBean(WorkerThread.class, "rkKR", "bbs", "나발드의검", 1);
        WorkerThread wt5 = context.getBean(WorkerThread.class, "rkKR", "bbs", "불검", 1);
        WorkerThread wt6 = context.getBean(WorkerThread.class, "rkKR", "bbs", "붉검", 1);
        WorkerThread wt7 = context.getBean(WorkerThread.class, "rkKR", "bbs", "기사의검", 1);
        WorkerThread wt8 = context.getBean(WorkerThread.class, "rkKR", "bbs", "기사창", 1);
        WorkerThread wt9 = context.getBean(WorkerThread.class, "rkKR", "bbs", "요판", 1);
        WorkerThread wt10 = context.getBean(WorkerThread.class, "rkKR", "bbs", "요정판금갑옷", 1);
        tasks.add(wt1);
        tasks.add(wt2);
        tasks.add(wt3);
        tasks.add(wt4);
        tasks.add(wt5);
        tasks.add(wt6);
        tasks.add(wt7);
        tasks.add(wt8);
        tasks.add(wt9);
        tasks.add(wt10);

        executorService.invokeAll(tasks);

        executorService.shutdown();

//        for(int i = 1 ; i < 10000000 ; i++){
//            tagsDao.insertTags("rkKR", "bbs", "나이트발드의검", String.valueOf(i));
//        }
    }

//    @Test
    public void generateRecommendation()throws Exception{
        for(long i = 1 ; i < 10000000 ; i++){
            tagsDao.insertRecommendation("rkKR", "bbs", "커츠의검"+i);
            tagsDao.insertRecommendation("rkKR", "bbs", "나이트발드의 검"+i);
            tagsDao.insertRecommendation("rkKR", "bbs", "불검"+i);
        }
    }
}
