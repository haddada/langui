package maarifa.tn.langui.model;

import android.database.Cursor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.ServerValue;

import org.parceler.Parcel;

import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import maarifa.tn.langui.model.data.LanguiContract;
import maarifa.tn.langui.utils.Constants;
import maarifa.tn.langui.utils.Utils;


/**
 * Created by seif on 22/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Parcel
public class Language {
    String name;
    int flag;
    String learner;
    private Long timestampLastChanged;
    private HashMap<String, Object> timestampCreated;
    private int vocabNumb;
    private int gramNumb;


    public Language(){

    }
    public Language(String name, int flag) {
        this.name = name;
        this.flag = flag;
        setTimestampLastChangedToNow();
    }
    public Language(String name, int flag,int vocabNumb,int gramRule) {
        this.name = name;
        this.flag = flag;
        this.vocabNumb=vocabNumb;
        this.gramNumb=gramRule;
        setTimestampLastChangedToNow();
    }

    public Language(String name,String learner){
        this.name=name;
        this.learner=learner;

    }

    public Language(Map<String,Object> map){
        this((String)map.get("name"), Utils.safeLongToInt((Long)map.get("flag")) );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getLearner() {
        return learner;
    }

    public void setLearner(String learner) {
        this.learner = learner;
    }

    public Long getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    public int getVocabNumb() {
        return vocabNumb;
    }

    public void setVocabNumb(int vocabNumb) {
        this.vocabNumb = vocabNumb;
    }

    public int getGramNumb() {
        return gramNumb;
    }

    public void setGramNumb(int gramNumb) {
        this.gramNumb = gramNumb;
    }

    public void setTimestampLastChangedToNow() {
        Date date=new Date();
        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged =date.getTime();
    }



    public static Language languageFromCursor(Cursor cursor){
        Language language=new Language();
        language.setName(cursor.getString(Constants.COL_LANGUAGE_NAME));
        language.setFlag(cursor.getInt(Constants.COL_LAGUAGE_FLAG));
        return language;
    }
}
