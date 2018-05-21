package com.timec.tag.api.dao;

import com.timec.tag.api.util.TextDivider;
import com.timec.tag.domain.Recommendation;
import com.timec.tag.domain.Tags;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by timec on 2018-05-15.
 */
@Repository
public class TagApiDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<String> findRecommendation(final Recommendation recommendation){
        Query query = new Query();

        query.addCriteria(Criteria.where("appGroupCode").is(recommendation.getAppGroupCode()).and("category").is(recommendation.getCategory()).and("word").is(recommendation.getWord()));
        query.fields().exclude("appGroupCode").exclude("category").exclude("initialConsonant").exclude("consonantNvowel").exclude("_id").exclude("version");

        Recommendation result = mongoTemplate.findOne(query, Recommendation.class);

        if(result == null){
            return Collections.emptyList();
        }

        return Arrays.asList(result.getWords());
    }

    public List<String> findArticleByTag(final Tags tags, final int offset, final String id){
        Query query = new Query();

        Criteria criteria = Criteria.where("appGroupCode").is(tags.getAppGroupCode()).and("category").is(tags.getCategory()).and("word").is(tags.getWord());

        if(!"".equals(id)){
            criteria.and("_id").lt(new ObjectId(id));
        }

        query.addCriteria(criteria);
        query.fields().exclude("appGroupCode").exclude("category").exclude("initialConsonant").exclude("consonantNvowel").exclude("_id").exclude("word");
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        query.limit(offset);

//        query.addCriteria(Criteria.where("appGroupCode").is(tags.getAppGroupCode()).and("category").is(tags.getCategory()).and("consonantNvowel").regex('^'+TextDivider.toConsonantNvowel(tags.getWord())));
//        query.fields().exclude("appGroupCode").exclude("category").exclude("initialConsonant").exclude("consonantNvowel").exclude("_id").exclude("word");
//        query.with(new Sort(Sort.Direction.DESC, "articleId"));

        List<Tags> list = mongoTemplate.find(query, Tags.class);

        if(list == null){
            return Collections.emptyList();
        }

        return list.stream().map(x -> String.valueOf(x.getId()))
                .collect(Collectors.toList());
    }

    public List<String> autoCompleteSearching(final Recommendation recommendation){
        Query query = new Query();
        final String word = recommendation.getWord();

        Criteria criteria = Criteria.where("appGroupCode").is(recommendation.getAppGroupCode()).and("category").is(recommendation.getCategory());

        if(TextDivider.isInitialConsonant(word)){
            criteria.and("initialConsonant").regex('^'+TextDivider.toInitialConsonant(word));
        }else{
            criteria.and("consonantNvowel").regex('^'+TextDivider.toConsonantNvowel(word));
        }

        query.addCriteria(criteria);
        query.fields().exclude("words").exclude("appGroupCode").exclude("category").exclude("initialConsonant").exclude("consonantNvowel").exclude("_id").exclude("version");
        query.limit(10);

        List<Recommendation> list = mongoTemplate.find(query, Recommendation.class);

        if(list == null){
            return Collections.emptyList();
        }

        return list.stream().map(x -> x.getWord())
                .collect(Collectors.toList());
    }
}
