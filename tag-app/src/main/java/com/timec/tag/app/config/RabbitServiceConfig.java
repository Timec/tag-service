package com.timec.tag.app.config;

import com.timec.tag.app.consumer.Consumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by timec on 2018-04-09.
 */
@Service
public class RabbitServiceConfig {
    static final String topicExchangeName = "spring-boot-exchange";
    static final String queueName = "spring-boot";

    @Bean
    Queue queue(){
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("tag.api.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        listenerAdapter.addQueueOrTagToMethodName("tag.api.saveTags", "saveTags");
        listenerAdapter.addQueueOrTagToMethodName("tag.api.updateTags", "updateTags");
        listenerAdapter.addQueueOrTagToMethodName("tag.api.deleteArticle", "deleteArticle");
        container.setMessageListener(listenerAdapter);
        container.setMessageConverter(jsonMessageConverter());
        return container;
    }

//    @Bean
//    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
//                                                           SimpleRabbitListenerContainerFactoryConfigurer configurer) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//        factory.setMessageConverter(jsonMessageConverter());
//        return factory;
//    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Consumer consumer){
        return new MessageListenerAdapter(consumer);
    }
}
