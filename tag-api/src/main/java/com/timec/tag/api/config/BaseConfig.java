package com.timec.tag.api.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

/**
 * Created by timec on 2018-04-09.
 */
@EnableRabbit
public class BaseConfig{
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter producerJackson2MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
