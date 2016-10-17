package maarifa.tn.langui.ui.shooseLanguage;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;

/**
 * Created by seif on 25/04/2016.
 */
public class MyLanguageAdapter extends FirebaseListAdapter<Language> {


    ViewHolder viewHolder;
    int mResource;
    List<Language> shoosenLanguageList=new ArrayList<Language>();
    Activity activity;
    private static class ViewHolder {
        private TextView name;
        private ImageView flag;
    }
    public MyLanguageAdapter(Activity activity, Class<Language> modelClass, int resource , Query ref) {
        super(activity, modelClass,resource,ref);
        this.activity=activity;
        mResource=resource;
    }


    @Override
    protected void populateView(View view, Language language) {

        viewHolder = new ViewHolder();
        viewHolder.name = (TextView) view.findViewById(R.id.language_name);
        viewHolder.flag=(ImageView) view.findViewById(R.id.language_flag);
        viewHolder.name.setText(language.getName());
        Glide.with(activity).load(language.getFlag()).fitCenter().into(viewHolder.flag);
        if(shoosenLanguageList.indexOf(language)==-1) {
            shoosenLanguageList.add(language);
        }
    }

    public List<Language> getShoosenLanguage(){
        return  shoosenLanguageList;
    }

}
