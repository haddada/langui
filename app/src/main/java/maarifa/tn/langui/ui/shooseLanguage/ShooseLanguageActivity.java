package maarifa.tn.langui.ui.shooseLanguage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fasterxml.jackson.databind.deser.Deserializers;

import org.parceler.Parcels;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.ui.BaseActivity;
import maarifa.tn.langui.ui.MainActivity;
import maarifa.tn.langui.ui.progress.ProgressActivityDetail;
import maarifa.tn.langui.ui.progress.ProgressFragment;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by seif on 22/04/2016.
 */
public class ShooseLanguageActivity extends MainActivity implements MyLanguagesFragment.Callback ,ProgressFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoose_language);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
        super.onCreateDrawer();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container, MyLanguagesFragment.newInstance(mEncodedEmail), "MY_LANGUAGES").
                    commit();
        }

        setTitle(mTitles[0]);

    }

    @Override
    public void onItemSelected(Language language,String languageId) {

        Intent intent = new Intent(this, LanguageDetailActivity.class);
        intent.putExtra("language", Parcels.wrap(language));
        intent.putExtra(Constants.LANGUAGE_KEY, languageId);
        intent.putExtra(Constants.KEY_ENCODED_EMAIL,mEncodedEmail);
        startActivity(intent);
    }

    @Override
    public void onProgressItemSelected(Language language, String languageId) {
        Intent intent =new Intent(this, ProgressActivityDetail.class);
        intent.putExtra("language", Parcels.wrap(language));
        intent.putExtra(Constants.LANGUAGE_KEY, languageId);
        intent.putExtra(Constants.KEY_ENCODED_EMAIL,mEncodedEmail);
        startActivity(intent);
    }
}
