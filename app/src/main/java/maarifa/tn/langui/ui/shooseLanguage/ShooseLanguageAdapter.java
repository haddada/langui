package maarifa.tn.langui.ui.shooseLanguage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.List;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;

/**
 * Created by hamza on 22/04/2016.
 */
public class ShooseLanguageAdapter extends ArrayAdapter<Language> {

    ViewHolder viewHolder;
    int mResource;
    Context mContext;

    private static class ViewHolder {
        private TextView name;
        private ImageView flag;
    }
    public ShooseLanguageAdapter(Context context, int resource ,List<Language> items) {
        super(context, resource,items);

        mContext=context;
        mResource=resource;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(mResource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.language_name);
            viewHolder.flag=(ImageView) convertView.findViewById(R.id.language_flag);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Language item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long

            viewHolder.name.setText(item.getName());
            Glide.with(mContext).load(item.getFlag()).fitCenter().into(viewHolder.flag);

        }
        return convertView;
    }
}
