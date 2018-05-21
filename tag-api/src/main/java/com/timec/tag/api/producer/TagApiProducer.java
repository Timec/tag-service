package com.timec.tag.api.producer;

import com.timec.tag.domain.Tags;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by timec on 2018-05-15.
 */
@Component
public class TagApiProducer {
    private static final String topicExchangeName = "spring-boot-exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void saveTags(Tags tags){
        rabbitTemplate.convertAndSend(topicExchangeName, "tag.api.saveTags", tags);
    }

    public void updateTags(Tags tags){
        rabbitTemplate.convertAndSend(topicExchangeName, "tag.api.updateTags", tags);
    }

    public void deleteArticle(Tags tags){
        rabbitTemplate.convertAndSend(topicExchangeName, "tag.api.deleteContent", tags);
    }
}
