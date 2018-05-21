package com.timec.tag.app.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.timec.tag.app.util.TextDivider;
import com.timec.tag.domain.Recommendation;
import com.timec.tag.domain.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by timec on 2018-05-08.
 */
@Repository
public class TagsDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public Tags checkTag(final String appGroupCode, final String category, final String word, final String articleId){
        Query query = new Query();

        query.addCriteria(Criteria.where("appGroupCode").is(appGroupCode).and("category").is(category).and("word").is(word).and("articleId").is(articleId));

        return mongoTemplate.findOne(query, Tags.class);
    }

    public void insertTags(final String appGroupCode, final String category, final String word, final String articleId){
        Tags tags = new Tags();
        tags.setAppGroupCode(appGroupCode);
        tags.setCategory(category);
        tags.setWord(word);
        tags.setArticleId(articleId);
        tags.setCrtDate(System.currentTimeMillis() / 1000);
        tags.setConsonantNvowel(TextDivider.toConsonantNvowel(word));
        tags.setInitialConsonant(TextDivider.toInitialConsonant(word));

        mongoTemplate.insert(tags, "tags");
    }

    public List<String> findTagsByArticleId(final String appGroupCode, final String category, final String articleId){
        Query query = new Query();
        query.addCriteria(Criteria.where("appGroupCode").is(appGroupCode).and("category").is(category).and("articleId").is(articleId));
        query.fields().include("word");

        return mongoTemplate.find(query, String.class);
    }

    public long deleteTag(final String appGroupCode, final String category, final String word, final String articleId){
        Query query = new Query();
        query.addCriteria(Criteria.where("appGroupCode").is(appGroupCode).and("category").is(category).and("word").is(word).and("articleId").is(articleId));

        DeleteResult result = mongoTemplate.remove(query, Integer.class);

        return result.getDeletedCount();
    }

    public long deleteArticle(final String appGroupCode, final String category, final String articleId){
        Query query = new Query();
        query.addCriteria(Criteria.where("appGroupCode").is(appGroupCode).and("category").is(category).and("articleId").is(articleId));

        DeleteResult result = mongoTemplate.remove(query, Integer.class);

        return result.getDeletedCount();
    }

    public Recommendation checkRecommendation(final String appGroupCode, final String category, final String word){
        Query query = new Query();

        query.addCriteria(Criteria.where("appGroupCode").is(appGroupCode).and("category").is(category).and("word").is(word));

        return mongoTemplate.findOne(query, Recommendation.class);
    }

    public void insertRecommendation(final String appGroupCode, final String category, final String word){
        List<String> recmmdWords = getRecommendationWords();

        Recommendation recommendation = new Recommendation();
        recommendation.setAppGroupCode(appGroupCode);
        recommendation.setCategory(category);
        recommendation.setVersion(System.currentTimeMillis() / 1000);
        recommendation.setWord(word);
        recommendation.setWords((String[])recmmdWords.toArray());
        recommendation.setConsonantNvowel(TextDivider.toConsonantNvowel(word));
        recommendation.setInitialConsonant(TextDivider.toInitialConsonant(word));

        mongoTemplate.insert(recommendation, "recommendation");
    }

    public void updateRecommendation(final String appGroupCode, final String category, final String word){
        List<String> recmmdWords = getRecommendationWords();

        Query query = new Query();

        query.addCriteria(Criteria.where("appGroupCode").is(appGroupCode).and("category").is(category).and("word").is(word));

        Update update = new Update();
        update.set("version", System.currentTimeMillis() / 1000);
        update.set("words", recmmdWords);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Recommendation.class);
    }

    public List<String> getRecommendationWords(){
        List<String> list = Arrays.asList("마단","수단", "싸울", "무관의장검", "9데블");
        return list;
    }
}
