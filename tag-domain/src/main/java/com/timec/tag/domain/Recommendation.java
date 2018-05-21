package com.timec.tag.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

/**
 * Created by timec on 2018-05-04.
 */
@Document(collection = "recommendation")
public class Recommendation {

    @Id
    private ObjectId id;
    private String appGroupCode;
    private String category;
    private String word;
    private String initialConsonant;
    private String consonantNvowel;
    private long version;
    private String[] words;

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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
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

    @Override
    public String toString() {
        return "Recommendation{" +
                "id='" + id + '\'' +
                ", appGroupCode='" + appGroupCode + '\'' +
                ", category='" + category + '\'' +
                ", word='" + word + '\'' +
                ", initialConsonant='" + initialConsonant + '\'' +
                ", consonantNvowel='" + consonantNvowel + '\'' +
                ", version=" + version +
                ", words=" + Arrays.toString(words) +
                '}';
    }
}
