package com.timec.tag.app.consumer;

import com.timec.tag.app.service.TagService;
import com.timec.tag.domain.Tags;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by timec on 2018-04-11.
 */
@Component
public class Consumer {
    @Autowired
    TagService tagService;

    public void saveTags(Tags tags){
        tagService.saveTags(tags);
    }

    public void updateTags(Tags tags){
        tagService.updateTags(tags);
    }

    public void deleteArticle(Tags tags){
        tagService.deleteArticle(tags);
    }
}
