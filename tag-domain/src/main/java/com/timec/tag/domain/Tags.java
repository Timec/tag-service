package com.timec.tag.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by timec on 2018-05-04.
 */
@Document(collection = "tags")
public class Tags implements Serializable {
    @Id
    private ObjectId id;
    private String appGroupCode;
    private String category;
    private String word;
    private String initialConsonant;
    private String consonantNvowel;
    private String articleId;
    private long crtDate;

    @Transient
    private String message;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAppGroupCode() {
        return appGroupCode;
    }

    public void setAppGroupCode(String appGroupCode) {
        this.appGroupCode = appGroupCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getInitialConsonant() {
        return initialConsonant;
    }

    public void setInitialConsonant(String initialConsonant) {
        this.initialConsonant = initialConsonant;
    }

    public String getConsonantNvowel() {
        return consonantNvowel;
    }

    public void setConsonantNvowel(String consonantNvowel) {
        this.consonantNvowel = consonantNvowel;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public long getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(long crtDate) {
        this.crtDate = crtDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", appGroupCode='" + appGroupCode + '\'' +
                ", category='" + category + '\'' +
                ", word='" + word + '\'' +
                ", initialConsonant='" + initialConsonant + '\'' +
                ", consonantNvowel='" + consonantNvowel + '\'' +
                ", articleId='" + articleId + '\'' +
                ", crtDate=" + crtDate +
                ", message='" + message + '\'' +
                '}';
    }
}
