package com.timec.tag.app.service;

import com.timec.tag.app.dao.TagsDao;
import com.timec.tag.app.util.TextDivider;
import com.timec.tag.domain.Recommendation;
import com.timec.tag.domain.Tags;
import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.Eojeol;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by timec on 2018-05-02.
 */
@Service
public class TagService {
    @Autowired
    TagsDao tagsDao;

    public void saveTags(final Tags tags){
        List<String> wordlist = divideTag(tags.getMessage());
        for(String word: wordlist) {
            if(tagsDao.checkTag(tags.getAppGroupCode(), tags.getCategory(), word, tags.getArticleId()) == null){
                tagsDao.insertTags(tags.getAppGroupCode(), tags.getCategory(), word, tags.getArticleId());
            }

            saveRecommendation(tags.getAppGroupCode(), tags.getCategory(), word);
        }
    }

    public void updateTags(final Tags tags){
        List<String> wordlist = divideTag(tags.getMessage());
        List<String> taglist = tagsDao.findTagsByArticleId(tags.getAppGroupCode(), tags.getCategory(), tags.getArticleId());

        //tag가 없어졌으면 삭제.
        for(String tag : taglist){
            if(!wordlist.contains(tag)){
                //없어졌으면 삭제.
                tagsDao.deleteTag(tags.getAppGroupCode(), tags.getCategory(), tag, tags.getArticleId());
            }
        }

        //tag가 생겼으면 등록.
        for(String word : wordlist){
            if(!taglist.contains(word)){
                //생겼으면 등록.
                tagsDao.insertTags(tags.getAppGroupCode(), tags.getCategory(), word, tags.getArticleId());

                //태그가 생겼으면 해당태그 추천태그 업데이트
                saveRecommendation(tags.getAppGroupCode(), tags.getCategory(), word);
            }
        }
    }

    public void deleteArticle(final Tags tags){
       tagsDao.deleteArticle(tags.getAppGroupCode(), tags.getCategory(), tags.getArticleId());
    }

    public void saveRecommendation(final String appGroupCode, final String category, final String word){
        if(tagsDao.checkRecommendation(appGroupCode, category, word) != null){
            tagsDao.updateRecommendation(appGroupCode, category, word);
        }else{
            tagsDao.insertRecommendation(appGroupCode, category, word);
        }
    }

    public List<String> divideTag(final String content){
//        String text = "#맛집#맥주 맛있는데 #맛#집#장난아님 정말 짱인거 가틈 #ㅎㅎ #HIHI #가자GOGO #GOOD스멜 #그림_판 #그림-판 #吉九 #あぃ";

        Pattern pattern = Pattern.compile("[#＃]([Ａ-Ｚａ-ｚA-Za-z一-\u9FC60-9０-９ぁ-ヶｦ-ﾟーㄱ-ㅎㅏ-ㅣ가-힣_])+");
        Matcher m = pattern.matcher(content);

        List<String> nounList = new ArrayList<>();

        String tag;
        while(m.find()){
            tag = content.subSequence(m.start(), m.end()).toString();

            nounList.add(tag);
//            nounList.addAll(analyzeMorpheme(tag.replace("#", "")));
        }
        return nounList;
    }

    public List<String> analyzeMorpheme(final String content){
//        String text = "데포1썹에6검이얼마나되나여?";

        List<String> nounList = new ArrayList<>();

        Analyzer.setUserDict(Arrays.asList("데포1섭","데포1썹", "데포1", "데포로쥬1", "데포1서버", "데포로쥬1썹", "데포로쥬1섭", "1강", "2강", "1검", "6검").iterator());
        for(Eojeol node : Analyzer.parseEojeolJava(content)){
            for(LNode ele : node.nodesJava()){
                if(ele.morpheme().copy$default$5().contains("NNG")){
                    nounList.add(ele.morpheme().surface());
                }
            }
        }
        return nounList;
    }
}
