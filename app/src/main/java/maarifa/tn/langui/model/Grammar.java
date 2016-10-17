package maarifa.tn.langui.model;

import org.parceler.Parcel;

/**
 * Created by seif on 24/05/2016.
 */
@Parcel
public class Grammar {

    private String title;
    private String description;
    private String tag;

    public Grammar(){

    }

    public Grammar(String title,String description){
        this.title=title;
        this.description=description;
    }

    public Grammar(String title,String description,String tag){
        this.title=title;
        this.description=description;
        this.tag=tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
