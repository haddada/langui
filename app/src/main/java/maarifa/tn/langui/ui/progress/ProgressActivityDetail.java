package maarifa.tn.langui.ui.progress;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.parceler.Parcels;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.ui.BaseActivity;
import maarifa.tn.langui.ui.MainActivity;
import maarifa.tn.langui.ui.shooseLanguage.MyLanguagesFragment;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by seif on 28/05/2016.
 */
public class ProgressActivityDetail extends BaseActivity {

    Language language;
    String languageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_detail);

        language = (Language) Parcels.unwrap(getIntent().getParcelableExtra("language"));
        mEncodedEmail=(String)getIntent().getStringExtra(Constants.KEY_ENCODED_EMAIL);
        languageId=(String)getIntent().getStringExtra(Constants.LANGUAGE_KEY);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container, ProgressDetailFragment.newInstance(mEncodedEmail,languageId), "MY_LANGUAGES").
                    commit();
        }



    }
}
