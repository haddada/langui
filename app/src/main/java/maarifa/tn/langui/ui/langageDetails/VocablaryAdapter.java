package maarifa.tn.langui.ui.langageDetails;

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

import com.firebase.client.Query;

import java.util.ArrayList;

import maarifa.tn.langui.LanguiApplication;
import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.model.Vocablary;
import maarifa.tn.langui.utils.Constants;
import maarifa.tn.langui.utils.FirebaseRecyclerAdapter;

/**
 * Created by hamza on 23/05/2016.
 */
public class VocablaryAdapter extends FirebaseRecyclerAdapter<VocablaryAdapter.ViewHolder, Vocablary> {



    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView theWord;
        TextView syn1;
        TextView syn2;
        TextView syn3;
        TextView context;
        TextView sentence;
        TextView tag;

        public ViewHolder(View view) {
            super(view);

            theWord = (TextView) view.findViewById(R.id.the_word);
            syn1=(TextView) view.findViewById(R.id.syn1);
            syn2=(TextView) view.findViewById(R.id.syn2);
            syn3=(TextView) view.findViewById(R.id.syn3);
            context=(TextView) view.findViewById(R.id.context);
            sentence=(TextView)view.findViewById(R.id.own_words);
            tag=(TextView)view.findViewById(R.id.tag);
        }



    }

    public VocablaryAdapter(Query query, Class<Vocablary> itemClass, @Nullable ArrayList<Vocablary> items,
                                     @Nullable ArrayList<String> keys,Context context) {
        super(query, itemClass, items, keys);

    }

    @Override public VocablaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vocablary_card, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(VocablaryAdapter.ViewHolder holder, int position) {
        Vocablary item = getItem(position);
        holder.theWord.setText(item.getTheWord());
        if(item.getSynonyms().size()>0)
            holder.syn1.setText(item.getSynonyms().get(0));
        if(item.getSynonyms().size()>1)
            holder.syn2.setText(item.getSynonyms().get(1));
        if(item.getSynonyms().size()>2)
            holder.syn3.setText(item.getSynonyms().get(2));
        if(item.getContext()!=null){
            holder.context.setText(item.getContext());
            holder.context.setVisibility(View.VISIBLE);
        }

        if(item.getSentences().size()>0){
            holder.sentence.setText(item.getSentences().get(0));
            holder.sentence.setVisibility(View.VISIBLE);
        }
        if(item.getTag()!=null){
            holder.tag.setVisibility(View.VISIBLE);
            holder.tag.setText(item.getTag());
        }

    }

    @Override
    public int getItemCount() {

      return super.getItemCount();
    }

    @Override protected void itemAdded(Vocablary item, String key, int position) {
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override protected void itemChanged(Vocablary oldItem, Vocablary newItem, String key, int position) {
        Log.d("MyAdapter", "Changed an item.");
    }

    @Override protected void itemRemoved(Vocablary item, String key, int position) {
        Log.d("MyAdapter", "Removed an item from the adapter.");
    }

    @Override protected void itemMoved(Vocablary item, String key, int oldPosition, int newPosition) {
        Log.d("MyAdapter", "Moved an item.");
    }
}