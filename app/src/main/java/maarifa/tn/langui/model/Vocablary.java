package maarifa.tn.langui.model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamza on 23/05/2016.
 */
@Parcel
public class Vocablary {
    String theWord;
    List<String> synonyms;
    List<String> sentences;
    String context;
    String tag;

    public Vocablary(){

    }
    public Vocablary(String theWord,List<String> synonyms,List<String>sentences,String context){
        this.theWord=theWord;
        this.synonyms=new ArrayList<String>(synonyms);
        this.sentences=new ArrayList<String>(sentences);
        this.context=context;
    }
    public Vocablary(String theWord,List<String> synonyms,List<String>sentences,String context,String tag){
        this.theWord=theWord;
        this.synonyms=new ArrayList<String>(synonyms);
        this.sentences=new ArrayList<String>(sentences);
        this.context=context;
        this.tag=tag;
    }

    public String getTheWord() {
        return theWord;
    }

    public void setTheWord(String theWord) {
        this.theWord = theWord;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
