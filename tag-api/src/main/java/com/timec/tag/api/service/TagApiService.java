package com.timec.tag.api.service;

import com.timec.tag.api.dao.TagApiDao;
import com.timec.tag.api.producer.TagApiProducer;
import com.timec.tag.domain.Recommendation;
import com.timec.tag.domain.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by timec on 2018-04-11.
 */
@Service
public class TagApiService {
    @Autowired
    TagApiDao tagApiDao;

    @Autowired
    TagApiProducer tagApiProducer;

    public void saveTags(Tags tags){
        tagApiProducer.saveTags(tags);
    }

    public void updateTags(Tags tags){
        tagApiProducer.updateTags(tags);
    }

    public void deleteArticle(Tags tags){
        tagApiProducer.deleteArticle(tags);
    }

    public List<String> findRecommendation(final Recommendation recommendation){
        return tagApiDao.findRecommendation(recommendation);
    }

    public List<String> findArticleByTag(final Tags tags, final int offset, final String id){
        return tagApiDao.findArticleByTag(tags, offset, id);
    }

    public List<String> autoCompleteSearching(final Recommendation recommendation){
        return tagApiDao.autoCompleteSearching(recommendation);
    }
}
