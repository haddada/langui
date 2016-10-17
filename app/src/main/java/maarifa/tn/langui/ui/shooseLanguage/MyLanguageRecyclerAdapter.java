package maarifa.tn.langui.ui.shooseLanguage;

/**
 * Created by seif on 23/05/2016.
 */
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.firebase.client.Query;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import maarifa.tn.langui.LanguiApplication;
import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.utils.Constants;
import maarifa.tn.langui.utils.FirebaseRecyclerAdapter;
import maarifa.tn.langui.utils.Utils;

/**
 * Created by Matteo on 24/08/2015.
 */
public class MyLanguageRecyclerAdapter extends FirebaseRecyclerAdapter<MyLanguageRecyclerAdapter.ViewHolder, Language> {

    Context context;
    Application app;
    private SharedPreferences mSharedPref;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView dateTextView;
        ImageView thumbnailView;
        TextView vocabNumber;
        TextView grammarNumber;


        public ViewHolder(View view) {
            super(view);
            textViewName = (TextView) view.findViewById(R.id.title);
            thumbnailView=(ImageView) view.findViewById(R.id.thumbnail);
            dateTextView=(TextView)view.findViewById(R.id.date);
            vocabNumber=(TextView)view.findViewById(R.id.word_vocab);
            grammarNumber=(TextView)view.findViewById(R.id.grammar_rule);
        }



    }

    public MyLanguageRecyclerAdapter(Query query, Class<Language> itemClass, @Nullable ArrayList<Language> items,
                     @Nullable ArrayList<String> keys,Application app) {
        super(query, itemClass, items, keys);
        this.app=app;
    }

    @Override public MyLanguageRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_language_card_item, parent, false);
        context=parent.getContext();
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(MyLanguageRecyclerAdapter.ViewHolder holder, int position) {
        Language item = getItem(position);
        holder.textViewName.setText(item.getName());
        holder.dateTextView.setText(Utils.getFriendlyDayString(context, Utils.dateFromLong(item.getTimestampLastChanged())));
        holder.vocabNumber.setText(getFormattedNbrVocab(item));
        holder.grammarNumber.setText(getFormattedNbrGrammar(item));
        switch (item.getName().toUpperCase()){
            case "ENGLISH":
                Glide.with(context).load(R.drawable.shakespeare_bust).fitCenter().into(holder.thumbnailView);
                //holder.thumbnailView.setImageResource(R.drawable.shakespeare_bust);
                break;
            case "FRENCH":
                Glide.with(context).load(R.drawable.french).fitCenter().into(holder.thumbnailView);
                holder.thumbnailView.setImageResource(R.drawable.french);
                break;
        }
    }

    private String getFormattedNbrVocab(Language item){

        int nbrVocab =item.getVocabNumb();
        int formatId = R.string.progress_vocab;
        return String.format(context.getString(
                formatId,
                nbrVocab));
    }
    private String getFormattedNbrGrammar(Language item){

        int nbrVocab =item.getGramNumb();
        int formatId = R.string.progress_gram;
        return String.format(context.getString(
                formatId,
                nbrVocab));
    }



    @Override protected void itemAdded(Language item, String key, int position) {
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override protected void itemChanged(Language oldItem, Language newItem, String key, int position) {
        Log.d("MyAdapter", "Changed an item.");
    }

    @Override protected void itemRemoved(Language item, String key, int position) {
        Log.d("MyAdapter", "Removed an item from the adapter.");
    }

    @Override protected void itemMoved(Language item, String key, int oldPosition, int newPosition) {
        Log.d("MyAdapter", "Moved an item.");
    }
}