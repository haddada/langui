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
import android.widget.TextView;

import com.firebase.client.Query;

import java.util.ArrayList;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Grammar;
import maarifa.tn.langui.model.Vocablary;
import maarifa.tn.langui.utils.Constants;
import maarifa.tn.langui.utils.FirebaseRecyclerAdapter;

/**
 * Created by hamza on 24/05/2016.
 */
public class GrammarAdapter extends FirebaseRecyclerAdapter<GrammarAdapter.ViewHolder, Grammar> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        public ViewHolder(View view) {
            super(view);
            title=(TextView)view.findViewById(R.id.title);
            description=(TextView)view.findViewById(R.id.description);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public GrammarAdapter(Query query, Class<Grammar> itemClass, @Nullable ArrayList<Grammar> items,
                          @Nullable ArrayList<String> keys, Context context) {
        super(query, itemClass, items, keys);

    }

    @Override public GrammarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grammar_card, parent, false);

        return new ViewHolder(view);
    }
    @Override public void onBindViewHolder(GrammarAdapter.ViewHolder holder, int position) {
        Grammar item = getItem(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());

    }
    @Override protected void itemAdded(Grammar item, String key, int position) {
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override protected void itemChanged(Grammar oldItem, Grammar newItem, String key, int position) {
        Log.d("MyAdapter", "Changed an item.");
    }

    @Override protected void itemRemoved(Grammar item, String key, int position) {
        Log.d("MyAdapter", "Removed an item from the adapter.");
    }

    @Override protected void itemMoved(Grammar item, String key, int oldPosition, int newPosition) {
        Log.d("MyAdapter", "Moved an item.");
    }
}
