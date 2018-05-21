package com.example.demo.rabbitmq.service;

import org.apache.http.impl.client.HttpClients;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by timec on 2018-04-11.
 */
@Component
public class TestOperation {
    public void saveTag(final String appGroupCode, final String category, final String articleId, final String message){
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        final String url = "http://localhost:33334/tag/v1/save";
        Map<String, String> map = new HashMap<>();
        map.put("appGroupCode", appGroupCode);
        map.put("category", category);
        map.put("articleId", articleId);
        map.put("message", message);

        restTemplate.postForObject(url, map, Void.class);
    }

    public void updateTag(final String appGroupCode, final String category, final String articleId, final String message){
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        final String url = "http://localhost:33334/tag/v1/update";
        Map<String, String> map = new HashMap<>();
        map.put("appGroupCode", appGroupCode);
        map.put("category", category);
        map.put("articleId", articleId);
        map.put("message", message);

        restTemplate.postForObject(url, map, Void.class);
    }

    public void deleteArticle(final String appGroupCode, final String category, final String articleId){
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        final String url = "http://localhost:33334/tag/v1/deleteArticle";
        Map<String, String> map = new HashMap<>();
        map.put("appGroupCode", appGroupCode);
        map.put("category", category);
        map.put("articleId", articleId);

        restTemplate.postForObject(url, map, Void.class);
    }

    public List<String> findRecommendation(final String appGroupCode, final String category, final String word){
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        final String url = "http://localhost:33334/tag/v1/findRecommendation?appGroupCode={appGroupCode}&category={category}&word={word}";
        return restTemplate.getForObject(url, List.class, appGroupCode, category, word);
    }

    public List<String> findArticleByTag(final String appGroupCode, final String category, final String word){
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        final String url = "http://localhost:33334/tag/v1/findArticleByTag?appGroupCode={appGroupCode}&category={category}&word={word}";
        return restTemplate.getForObject(url, List.class, appGroupCode, category, word);
    }

    public List<String> autoCompleteSearching(final String appGroupCode, final String category, final String word){
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        final String url = "http://localhost:33334/tag/v1/autoCompleteSearching?appGroupCode={appGroupCode}&category={category}&word={word}";
        return restTemplate.getForObject(url, List.class, appGroupCode, category, word);
    }
}
