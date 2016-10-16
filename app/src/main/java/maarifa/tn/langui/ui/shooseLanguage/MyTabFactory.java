package maarifa.tn.langui.ui.shooseLanguage;

import android.content.Context;
import android.view.View;
import android.widget.TabHost;

/**
 * Created by hamza on 23/05/2016.
 */
public class MyTabFactory implements TabHost.TabContentFactory {

    private final Context mContext;

    public MyTabFactory(Context context) {
        mContext = context;
    }

    public View createTabContent(String tag) {
        View v = new View(mContext);
        v.setMinimumWidth(0);
        v.setMinimumHeight(0);
        return v;
    }
}
