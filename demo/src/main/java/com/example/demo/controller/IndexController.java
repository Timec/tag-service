package com.example.demo.controller;

import com.example.demo.rabbitmq.service.TestOperation;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by timec on 2018-04-09.
 */
@RestController
public class IndexController {
    @Autowired
    private TestOperation testOperation;

    @RequestMapping("/save")
    public String save(@RequestParam("category") String category, @RequestParam("articleId") String articleId, @RequestParam("message") String message) throws Exception{
        testOperation.saveTag("rkKR", category, articleId, message);

        return message;
    }

    @RequestMapping("/update")
    public String update(@RequestParam("category") String category, @RequestParam("articleId") String articleId,@RequestParam("message") String message) throws Exception{
        testOperation.updateTag("rkKR", category, articleId, message);

        return message;
    }

    @RequestMapping("/deleteArticle")
    public String deleteArticle(@RequestParam("category") String category, @RequestParam("articleId") String articleId,@RequestParam("message") String message) throws Exception{
        testOperation.deleteArticle("rkKR", category, articleId);

        return message;
    }

    @RequestMapping("/findRecommendation")
    public List<String> findRecommendation(@RequestParam("category") String category,String word) throws Exception{
        return testOperation.findRecommendation("rkKR", category, word);
    }

    @RequestMapping("/findArticleByTag")
    public List<String> findArticleByTag(@RequestParam("category") String category,String word) throws Exception{
        return testOperation.findArticleByTag("rkKR", category, word);
    }

    @RequestMapping("/autoCompleteSearching")
    public List<String> autoCompleteSearching(@RequestParam("category") String category,String word) throws Exception{
        return testOperation.autoCompleteSearching("rkKR", category, word);
    }
}
