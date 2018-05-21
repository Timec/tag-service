package com.timec.tag.api.controller;

import com.timec.tag.domain.Recommendation;
import com.timec.tag.domain.Tags;
import com.timec.tag.api.service.TagApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by timec on 2018-04-09.
 */
@RestController
public class TagApiController {
    @Autowired
    private TagApiService tagApiService;

    @PostMapping("/tag/v1/save")
    public void save(@RequestBody final Tags tags){
        tagApiService.saveTags(tags);
    }

    @PostMapping("/tag/v1/update")
    public void update(@RequestBody final Tags tags){
        tagApiService.updateTags(tags);
    }

    @PostMapping("/tag/v1/deleteArticle")
    public void deleteArticle(@RequestBody final Tags tags){
        tagApiService.deleteArticle(tags);
    }

    @GetMapping("/tag/v1/findRecommendation")
    public List<String> findRecommendation(final Recommendation recommendation){
        return tagApiService.findRecommendation(recommendation);
    }

    @GetMapping("/tag/v1/findArticleByTag")
    public List<String> findArticleByTag(final Tags tags, final int offset, final String id){
        return tagApiService.findArticleByTag(tags, offset, id);
    }

    @GetMapping("/tag/v1/autoCompleteSearching")
    public List<String> findTags(final Recommendation recommendation){
        return tagApiService.autoCompleteSearching(recommendation);
    }
}
